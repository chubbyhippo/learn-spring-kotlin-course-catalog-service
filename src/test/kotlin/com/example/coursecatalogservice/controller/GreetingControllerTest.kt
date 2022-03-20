package com.example.coursecatalogservice.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest
internal class GreetingControllerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun shouldReturnHello() {
        webTestClient.get()
            .uri("/v1/greetings/Hippo")
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(String::class.java)
            .isEqualTo("Hello Hippo")

    }

}

