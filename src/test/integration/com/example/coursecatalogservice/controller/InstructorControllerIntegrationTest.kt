package com.example.coursecatalogservice.controller

import com.example.coursecatalogservice.CourseCatalogServiceApplication
import com.example.coursecatalogservice.dto.InstructorDto
import com.example.coursecatalogservice.repository.InstructorRepository
import com.example.coursecatalogservice.service.InstructorService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(
    classes = [CourseCatalogServiceApplication::class,
        InstructorController::class,
        InstructorService::class,
        InstructorRepository::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")
@AutoConfigureWebClient
class InstructorControllerIntegrationTest {
    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Autowired
    private lateinit var instructorRepository: InstructorRepository

    @BeforeEach
    fun setUp() {
        instructorRepository.deleteAll()
    }

    @Test
    fun shouldAddInstructor() {
        val instructorDto = InstructorDto(null, "Hippo")

        val savedInstructorDto = webTestClient
            .post()
            .uri("/v1/instructors")
            .bodyValue(instructorDto)
            .exchange()
            .expectStatus().isCreated
            .expectBody(InstructorDto::class.java)
            .returnResult()
            .responseBody

        Assertions.assertTrue {
            savedInstructorDto!!.id != null
        }
    }

}
