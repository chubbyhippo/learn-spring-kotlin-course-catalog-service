package com.example.coursecatalogservice.service

import com.example.coursecatalogservice.dto.CourseDto
import com.example.coursecatalogservice.entity.Course
import com.example.coursecatalogservice.repository.CourseRepository
import com.example.coursecatalogservice.util.courseEntityList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class CourseServiceTest {
    @Mock
    lateinit var courseRepository: CourseRepository

    @InjectMocks
    lateinit var courseService: CourseService

    @Test
    fun shouldAddCourse() {
        val courseDto = CourseDto(null, "Hippo kotlin development", "Hippo")
        val courseEntity = courseDto.let {
            Course(null, it.name, it.category)
        }
        `when`(courseRepository.save(any(Course::class.java)))
            .thenReturn(Course(1, courseEntity.name, courseEntity.category))

        val addedCourse = courseService.addCourse(courseDto)
        println(addedCourse)

        Assertions.assertNotNull(addedCourse.id)

    }

    @Test
    fun shouldRetrieveAllCourses() {

        `when`(courseRepository.findAll())
            .thenReturn(courseEntityList())

        val courseDtos = courseService.retrieveAllCourses()

        Assertions.assertEquals(3, courseDtos.size)
    }

    @Test
    fun shouldUpdateCourse() {

        val courseDto = CourseDto(
            null,
            "Hello Hippo",
            "General"
        )
        `when`(courseRepository.findById(anyInt())).thenReturn(
            Optional.of(
                Course(
                    1,
                    courseDto.name,
                    courseDto.category
                )
            )
        )

        val (id, name, category) = courseService.updateCourse(1, courseDto)

        Assertions.assertEquals(1, id)
        Assertions.assertEquals("Hello Hippo", name)
        Assertions.assertEquals("General", category)

    }

}
