package com.example.coursecatalogservice.repository

import com.example.coursecatalogservice.util.courseEntityList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class CourseRepositoryIntegrationTest {

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
}