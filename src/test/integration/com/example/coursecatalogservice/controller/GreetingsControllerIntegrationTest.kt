package com.example.coursecatalogservice.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebClient
class GreetingsControllerIntegrationTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun shouldReturnHello() {
        val name = "Hippo"

        webTestClient.get()
            .uri("/v1/greetings/{name}", name)
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(String::class.java)
            .isEqualTo("Hello Hippo")
    }

}
