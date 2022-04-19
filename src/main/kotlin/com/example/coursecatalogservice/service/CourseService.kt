package com.example.coursecatalogservice.service

import com.example.coursecatalogservice.dto.CourseDto
import com.example.coursecatalogservice.entity.Course
import com.example.coursecatalogservice.exception.CourseNotFoundException
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

    fun updateCourse(courseId: Int, courseDto: CourseDto): CourseDto {
        val existingCourse = courseRepository.findById(courseId)
        return if (existingCourse.isPresent) {
            existingCourse.get()
                    .let {
                        it.name = courseDto.name
                        it.category = courseDto.category
                        courseRepository.save(it)
                        CourseDto(it.id, it.name, it.category)
                    }
        } else {
            throw CourseNotFoundException("No course found for the passed in Id : $courseId")
        }
    }

    fun deleteCourse(courseId: Int) {
        val existingCourse = courseRepository.findById(courseId)
        if (existingCourse.isPresent) {
            existingCourse.get()
                    .let {
                        courseRepository.deleteById(courseId)
                    }
        } else {
            throw CourseNotFoundException("No course found for the passed in Id : $courseId")
        }
    }
}
