package com.example.coursecatalogservice.controller

import com.example.coursecatalogservice.CourseCatalogServiceApplication
import com.example.coursecatalogservice.service.GreetingsService
import com.example.coursecatalogservice.util.TestContainersConfig
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(
    classes = [CourseCatalogServiceApplication::class, GreetingsController::class, GreetingsService::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")
@AutoConfigureWebClient
class GreetingsControllerIntegrationTest: TestContainersConfig() {

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
