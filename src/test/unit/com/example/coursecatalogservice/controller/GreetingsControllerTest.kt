package com.example.coursecatalogservice.controller

import com.example.coursecatalogservice.service.GreetingsService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest
internal class GreetingsControllerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockBean
    private lateinit var greetingsService: GreetingsService

    private val name = "Hippo"

    @BeforeEach
    fun setup() {
        `when`(greetingsService.greet(name))
            .thenReturn("Hello Hippo")
    }

    @Test
    fun shouldReturnHello() {

        webTestClient.get()
            .uri("/v1/greetings/{name}", name)
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(String::class.java)
            .isEqualTo("Hello Hippo")

        verify(greetingsService, times(1))
            .greet("Hippo")
    }

}

