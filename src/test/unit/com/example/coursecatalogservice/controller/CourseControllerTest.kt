package com.example.coursecatalogservice.controller

import com.example.coursecatalogservice.service.CourseService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(controllers = [CourseController::class])
internal class CourseControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockBean
    lateinit var courseService: CourseService

    @Test
    fun addCourse() {
    }

    @Test
    fun retrieveAllCourses() {
    }

    @Test
    fun updateCourse() {
    }

    @Test
    fun deleteCourse() {
    }

    @Test
    fun getCourseService() {
    }
}
