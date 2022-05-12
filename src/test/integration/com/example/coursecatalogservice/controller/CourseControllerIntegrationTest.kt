package com.example.coursecatalogservice.controller

import com.example.coursecatalogservice.CourseCatalogServiceApplication
import com.example.coursecatalogservice.dto.CourseDto
import com.example.coursecatalogservice.repository.CourseRepository
import com.example.coursecatalogservice.service.CourseService
import com.example.coursecatalogservice.util.courseEntityList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(
        classes = [CourseCatalogServiceApplication::class, CourseController::class, CourseService::class],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")
@AutoConfigureWebClient
class CourseControllerIntegrationTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Autowired
    private lateinit var courseRepository: CourseRepository

    @BeforeEach
    fun setUp() {
        courseRepository.deleteAll()
        val courseEntityList = courseEntityList()
        courseRepository.saveAll(courseEntityList)
    }

    @Test
    fun shouldAddCourse() {
        val courseDto = CourseDto(null, "Hippo study", "Hippo")

        val savedCourseDto = webTestClient
            .post()
                .uri("/v1/courses")
                .bodyValue(courseDto)
                .exchange()
                .expectStatus().isCreated
                .expectBody(CourseDto::class.java)
                .returnResult()
                .responseBody

        assertTrue {
            savedCourseDto!!.id != null
        }
    }

    @Test
    fun shouldRetrieveAllCourses() {
        val courseDtos = webTestClient.get()
                .uri("/v1/courses")
                .exchange()
                .expectStatus().isOk
                .expectBodyList(CourseDto::class.java)
                .returnResult()
                .responseBody

        assertEquals(3, courseDtos?.size)
    }

    @Test
    fun shouldUpdateCourse() {

        val course = courseRepository.findAll()[0]
        val updatedCourseDto = CourseDto(
                null,
                "Hello Hippo",
                "General"
        )

        val updateCourseDto = webTestClient.put()
                .uri("/v1/courses/{courseId}", course.id)
                .bodyValue(updatedCourseDto)
                .exchange()
                .expectStatus().isOk
                .expectBody(CourseDto::class.java)
                .returnResult()
                .responseBody

        assertEquals("Hello Hippo", updateCourseDto!!.name)
        assertEquals("General", updateCourseDto.category)

    }

    @Test
    fun shouldDeleteCourse() {
        val course = courseRepository.findAll()[0]
        val courseSizeBeforeDeletion = courseRepository.findAll().size

        webTestClient.delete()
                .uri("/v1/courses/{courseId}", course.id)
                .exchange()
                .expectStatus().isNoContent

        val courseSizeAfterDeletion = courseRepository.findAll().size
        assertEquals(1, courseSizeBeforeDeletion - courseSizeAfterDeletion)
    }

}
