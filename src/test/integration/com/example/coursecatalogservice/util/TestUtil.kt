package com.example.coursecatalogservice.util

import com.example.coursecatalogservice.dto.CourseDto
import com.example.coursecatalogservice.entity.Course
import com.example.coursecatalogservice.entity.Instructor

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

fun courseEntityList(instructor: Instructor? = null) = listOf(
    Course(null,
        "Hippo development", "Development",
        instructor),
    Course(null,
        "Hippo love theory", "Development"
        ,instructor
    ),
    Course(null,
        "Hippo for Java developers", "Development" ,
        instructor)
)
fun instructorEntity(name : String = "Hippo")
        = Instructor(null, name)

fun courseDto(
    id: Int? = null,
    name: String = "Hippo love theory",
    category: String = "Hippo ",
    instructorId: Int? = 1
) = CourseDto(
    id,
    name,
    category,
    instructorId
)
