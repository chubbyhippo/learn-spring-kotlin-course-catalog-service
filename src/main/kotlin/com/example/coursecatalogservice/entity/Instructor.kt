package com.example.coursecatalogservice.entity

import jakarta.persistence.*

@Entity
@Table(name = "Instructors")
data class Instructor(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    var name: String,
    @OneToMany(
        mappedBy = "instructor",
        cascade = [CascadeType.ALL]
    )
    var courses: List<Course> = mutableListOf()
)

