package com.example.coursecatalogservice.repository

import com.example.coursecatalogservice.entity.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository : JpaRepository<Course, Int>
