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
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

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

//    @Test
//    fun shouldUpdateCourse() {
//
//        Mockito.`when`(courseRepository.findById())
//        val updatedCourseDto = CourseDto(
//            null,
//            "Hello Hippo",
//            "General"
//        )
//
//        val updateCourseDto = webTestClient.put()
//            .uri("/v1/courses/{courseId}", course.id)
//            .bodyValue(updatedCourseDto)
//            .exchange()
//            .expectStatus().isOk
//            .expectBody(CourseDto::class.java)
//            .returnResult()
//            .responseBody
//
//        Assertions.assertEquals("Hello Hippo", updateCourseDto!!.name)
//        Assertions.assertEquals("General", updateCourseDto.category)
//
//    }

}
