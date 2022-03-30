package com.example.coursecatalogservice.service

import com.example.coursecatalogservice.dto.CourseDto
import com.example.coursecatalogservice.entity.Course
import com.example.coursecatalogservice.repository.CourseRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CourseService(val courseRepository: CourseRepository) {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    fun addCourse(courseDto: CourseDto): CourseDto {
        val courseEntity = courseDto.let {
            Course(null, it.name, it.category)
        }

        courseRepository.save(courseEntity)
        log.info("Saved course is : $courseEntity")
        courseEntity.let {
            return CourseDto(it.id, it.name, it.category)
        }
    }

    fun retrieveAllCourses(): List<CourseDto> {
        return courseRepository.findAll()
            .map {
                CourseDto(it.id, it.name, it.category)
            }
    }
}
