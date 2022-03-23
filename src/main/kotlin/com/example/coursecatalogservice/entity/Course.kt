package com.example.coursecatalogservice.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Courses")
data class Course(
    @Id
    val id: Int?,
    val name: String,
    val category: String
)
