package com.example.coursecatalogservice.service

import com.example.coursecatalogservice.dto.CourseDto
import com.example.coursecatalogservice.entity.Course
import com.example.coursecatalogservice.entity.Instructor
import com.example.coursecatalogservice.exception.CourseNotFoundException
import com.example.coursecatalogservice.repository.CourseRepository
import com.example.coursecatalogservice.repository.InstructorRepository
import com.example.coursecatalogservice.util.courseEntityList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class CourseServiceTest {
    @Mock
    lateinit var courseRepository: CourseRepository
    @Mock
    lateinit var instructorRepository: InstructorRepository

    @InjectMocks
    lateinit var courseService: CourseService

    @Test
    fun shouldAddCourse() {
        val instructorEntity = Instructor(1, "Hippo")
        val courseDto = CourseDto(null, "Hippo kotlin development", "Hippo", 1)
        val courseEntity = courseDto.let {
            Course(null, it.name, it.category, instructorEntity)
        }
        `when`(instructorRepository.findById(anyInt()))
            .thenReturn(Optional.of(Instructor(1, "Hippo")))
        `when`(courseRepository.save(any(Course::class.java)))
            .thenReturn(Course(1, courseEntity.name, courseEntity.category, instructorEntity))

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

        assertThrows<CourseNotFoundException> {
            courseService.updateCourse(99, courseDto)
        }

    }

    @Test
    fun shouldDeleteCourse() {

        val courseId = 1
        val courseToDelete = Course(courseId, "", "")
        `when`(courseRepository.findById(courseId)).thenReturn(Optional.of(courseToDelete))

        doNothing().`when`(courseRepository).deleteById(courseId)

        courseService.deleteCourse(courseId)
        verify(courseRepository).deleteById(courseId)

    }

}
