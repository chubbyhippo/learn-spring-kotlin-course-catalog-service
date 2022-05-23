package com.example.coursecatalogservice.controller

import com.example.coursecatalogservice.dto.InstructorDto
import com.example.coursecatalogservice.service.InstructorService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(controllers = [InstructorController::class])
class InstructorControllerTest {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockBean
    lateinit var instructorService: InstructorService

    @Test
    fun shouldAddInstructor() {
        val instructorDto = InstructorDto(null, "Hippo")
        val instructorDtoWithId = InstructorDto(1, "Hippo")
        `when`(instructorService.createInstructor(instructorDto)).thenReturn(instructorDtoWithId)

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
