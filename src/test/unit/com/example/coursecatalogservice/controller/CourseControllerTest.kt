package com.example.coursecatalogservice.controller

import com.example.coursecatalogservice.dto.CourseDto
import com.example.coursecatalogservice.service.CourseService
import com.example.coursecatalogservice.util.courseDto
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(controllers = [CourseController::class])
internal class CourseControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockBean
    lateinit var courseService: CourseService

    @Test
    fun shouldAddCourse() {
        val courseDto = CourseDto(null, "Hippo kotlin development", "Hippo")
        `when`(courseService.addCourse(courseDto)).thenReturn(courseDto(id = 1))

        val savedCourseDto = webTestClient
            .post()
            .uri("/v1/courses")
            .bodyValue(courseDto)
            .exchange()
            .expectStatus().isCreated
            .expectBody(CourseDto::class.java)
            .returnResult()
            .responseBody

        Assertions.assertTrue {
            savedCourseDto!!.id != null
        }
    }

    @Test
    fun retrieveAllCourses() {
        `when`(courseService.retrieveAllCourses())
            .thenReturn(
                listOf(
                    courseDto(id = 1),
                    courseDto(id = 2),
                    courseDto(id = 3)

                )
            )

        val courseDtos = webTestClient.get()
            .uri("/v1/courses")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(CourseDto::class.java)
            .returnResult()
            .responseBody

        Assertions.assertEquals(3, courseDtos?.size)
    }

    @Test
    fun shouldUpdateCourse() {

        val updatedCourseDto = CourseDto(
            null,
            "Hello Hippo",
            "General"
        )
        `when`(courseService.updateCourse(100, updatedCourseDto))
            .thenReturn(
                courseDto(
                    id = 100,
                    name = updatedCourseDto.name,
                    category = updatedCourseDto.category
                )
            )

        val updateCourseDto = webTestClient.put()
            .uri("/v1/courses/{courseId}", 100)
            .bodyValue(updatedCourseDto)
            .exchange()
            .expectStatus().isOk
            .expectBody(CourseDto::class.java)
            .returnResult()
            .responseBody

        Assertions.assertEquals("Hello Hippo", updateCourseDto!!.name)
        Assertions.assertEquals("General", updateCourseDto.category)
    }

    @Test
    fun shouldDeleteCourse() {

       val courseId = 1

        `when`(courseService.deleteCourse(courseId))

        webTestClient.delete()
            .uri("/v1/courses/{courseId}", courseId)
            .exchange()
            .expectStatus().isNoContent

    }

}
