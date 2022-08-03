package com.example.coursecatalogservice.repository

import com.example.coursecatalogservice.util.TestContainersConfig
import com.example.coursecatalogservice.util.courseEntityList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import java.util.stream.Stream

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CourseRepositoryIntegrationTest : TestContainersConfig() {

    @Autowired
    private lateinit var courseRepository: CourseRepository

    @BeforeEach
    fun setUp() {
        courseRepository.deleteAll()
        val courseEntityList = courseEntityList()
        courseRepository.saveAll(courseEntityList)
    }

    @Test
    fun shouldFindByNameContaining() {
        val courses = courseRepository.findByNameContaining("Hippo")
        Assertions.assertEquals(3, courses.size)
    }

    @Test
    fun shouldFindByName() {
        val courses = courseRepository.findCourseByName("Hippo love theory")
        Assertions.assertEquals(1, courses.size)
    }

    @ParameterizedTest
    @MethodSource("courseAndSize")
    fun shouldFindByNameApproach2(name: String, expectedSize: Int) {
        val courses = courseRepository.findCourseByName(name)
        Assertions.assertEquals(expectedSize, courses.size)
    }

    companion object {
        @JvmStatic
        fun courseAndSize(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments("Hippo love theory", 1),
                Arguments.arguments("Hippo for Java developers", 1)
            )
        }
    }
}
