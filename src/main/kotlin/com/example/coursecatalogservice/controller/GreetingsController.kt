package com.example.coursecatalogservice.controller

import com.example.coursecatalogservice.service.GreetingsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/greetings")
class GreetingsController(
    val greetingsService: GreetingsService
) {


    @GetMapping("/{name}")
    fun greet(@PathVariable name: String): String {
        return greetingsService.greet(name)
    }
}