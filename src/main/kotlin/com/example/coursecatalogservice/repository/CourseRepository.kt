package com.example.coursecatalogservice.repository

import com.example.coursecatalogservice.entity.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CourseRepository : JpaRepository<Course, Int> {
    fun findByNameContaining(courseName: String): List<Course>

    @Query(value = "select * from courses where name like ?1", nativeQuery = true)
    fun findCourseByName(courseName: String): List<Course>

}
