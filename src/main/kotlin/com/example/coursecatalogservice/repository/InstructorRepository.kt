package com.example.coursecatalogservice.repository

import com.example.coursecatalogservice.entity.Instructor
import org.springframework.data.jpa.repository.JpaRepository

interface InstructorRepository : JpaRepository<Instructor, Int>
