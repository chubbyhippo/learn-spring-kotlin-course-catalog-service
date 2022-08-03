package com.example.coursecatalogservice.service

import com.example.coursecatalogservice.dto.InstructorDto
import com.example.coursecatalogservice.entity.Instructor
import com.example.coursecatalogservice.repository.InstructorRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class InstructorService(val instructorRepository: InstructorRepository) {
    fun createInstructor(instructorDto: InstructorDto): InstructorDto {
        val instructor = instructorDto.let {
            Instructor(it.id, it.name)
        }
        val save = instructorRepository.save(instructor)
        return save.let {
            InstructorDto(it.id, it.name)
        }
    }

}
