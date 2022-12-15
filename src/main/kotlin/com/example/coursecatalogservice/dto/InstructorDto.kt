package com.example.coursecatalogservice.dto

import jakarta.validation.constraints.NotBlank


data class InstructorDto(
    val id: Int?,
    @get:NotBlank(message = "instructorDto.name must not be blank")
    val name: String
)

