package com.example.coursecatalogservice.controller

import com.example.coursecatalogservice.service.GreetingsService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/greetings")
class GreetingsController(
    val greetingsService: GreetingsService
) {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @GetMapping("/{name}")
    fun greet(@PathVariable name: String): String {
        log.info("name = {}", name)
        return greetingsService.greet(name)
    }
}
