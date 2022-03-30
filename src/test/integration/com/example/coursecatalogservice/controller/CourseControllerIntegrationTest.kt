package com.example.coursecatalogservice.controller

import com.example.coursecatalogservice.CourseCatalogServiceApplication
import com.example.coursecatalogservice.dto.CourseDto
import com.example.coursecatalogservice.repository.CourseRepository
import com.example.coursecatalogservice.service.CourseService
import com.example.coursecatalogservice.util.courseEntityList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
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

        val responseBody = webTestClient.post()
            .uri("/v1/courses")
            .bodyValue(courseDto)
            .exchange()
            .expectStatus().isCreated
            .expectBody(CourseDto::class.java)
            .returnResult()
            .responseBody

        assertTrue {
            responseBody!!.id != null
        }
    }

    @Test
    fun shouldRetrieveAllCourses() {
        val responseBody = webTestClient.get()
            .uri("/v1/courses")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(CourseDto::class.java)
            .returnResult()
            .responseBody

        assertEquals(3, responseBody?.size)
    }
}
