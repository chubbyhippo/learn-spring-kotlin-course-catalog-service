package com.example.coursecatalogservice.service

import com.example.coursecatalogservice.dto.CourseDto
import com.example.coursecatalogservice.entity.Course
import com.example.coursecatalogservice.exception.CourseNotFoundException
import com.example.coursecatalogservice.repository.CourseRepository
import com.example.coursecatalogservice.util.courseEntityList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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

        assertNotNull(addedCourse.id)

    }

    @Test
    fun shouldRetrieveAllCourses() {

        `when`(courseRepository.findAll())
            .thenReturn(courseEntityList())

        val courseDtos = courseService.retrieveAllCourses()

        assertEquals(3, courseDtos.size)
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

        assertEquals(1, id)
        assertEquals("Hello Hippo", name)
        assertEquals("General", category)

    }

    @Test
    fun shouldUpdateCourseThrowException() {

        val courseDto = CourseDto(
            null,
            "Hello Hippo",
            "General"
        )
        val anyInt = anyInt()
        `when`(courseRepository.findById(anyInt))
            .thenReturn(Optional.ofNullable(null))

        assertThrows<CourseNotFoundException>{
            courseService.updateCourse(99, courseDto)
        }

    }

}
