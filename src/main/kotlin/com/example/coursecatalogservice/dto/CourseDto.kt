package com.example.coursecatalogservice.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull


data class CourseDto(
    val id: Int?,
    @get:NotBlank(message = "courseDto.name must not be blank")
    val name: String,
    @get:NotBlank(message = "courseDto.category must not be blank")
    val category: String,
    @get:NotNull(message = "courseDto.instructorId must not be null")
    val instructorId: Int? = null
)
