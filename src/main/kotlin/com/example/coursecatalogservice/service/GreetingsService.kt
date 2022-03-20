package com.example.coursecatalogservice.service

import org.springframework.stereotype.Service

@Service
class GreetingsService {
    fun greet(name: String): String = "Hello $name"
}