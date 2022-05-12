package com.example.coursecatalogservice.util

import com.example.coursecatalogservice.dto.CourseDto
import com.example.coursecatalogservice.entity.Course

fun courseEntityList() = listOf(
    Course(
        null,
        "Hippo development",
        "Development"
    ),
    Course(
        null,
        "Hippo love theory",
        "Psychology"
    ),
    Course(
        null,
        "Hippo for Java developers",
        "Development"
    )
)

fun courseDto(
    id: Int? = null,
    name: String = "Hippo love theory",
    category: String = "Hippo ",
//    instructorId: Int? = 1
) = CourseDto(
    id,
    name,
    category,
//    instructorId
)
