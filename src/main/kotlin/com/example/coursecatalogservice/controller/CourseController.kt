package com.example.coursecatalogservice.controller

import com.example.coursecatalogservice.dto.CourseDto
import com.example.coursecatalogservice.service.CourseService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/courses")
@Validated
class CourseController(val courseService: CourseService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addCourse(@RequestBody @Valid courseDto: CourseDto): CourseDto {
        return courseService.addCourse(courseDto)
    }

    @GetMapping
    fun retrieveAllCourses(@RequestParam("courseName", required = false) courseName: String?): List<CourseDto> {
        return courseName?.let {
            courseService.retrieveCoursesByName(courseName)
        } ?: courseService.retrieveAllCourses()
    }

    @PutMapping("/{courseId}")
    fun updateCourse(
        @RequestBody courseDto: CourseDto,
        @PathVariable("courseId") courseId: Int
    ) = courseService.updateCourse(courseId, courseDto)

    @DeleteMapping("/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCourse(
        @PathVariable("courseId") courseId: Int
    ) = courseService.deleteCourse(courseId)
}
