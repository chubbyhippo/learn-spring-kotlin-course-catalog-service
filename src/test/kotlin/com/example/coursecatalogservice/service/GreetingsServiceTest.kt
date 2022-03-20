package com.example.coursecatalogservice.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class GreetingsServiceTest {
    private val greetingsService = GreetingsService()

    @Test
    fun shouldGetHelloWithName() {
        val greet = greetingsService.greet("Hippo")
        Assertions.assertThat(greet).isEqualTo("Hello Hippo")
    }
}