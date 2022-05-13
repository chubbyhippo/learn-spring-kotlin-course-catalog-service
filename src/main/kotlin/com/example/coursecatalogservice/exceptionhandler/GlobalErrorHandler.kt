package com.example.coursecatalogservice.exceptionhandler

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GlobalErrorHandler : ResponseEntityExceptionHandler() {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        log.error("MethodArgumentNotValidException observed : {}", ex.message)
        val errors = ex.bindingResult.allErrors
            .map { objectError -> objectError.defaultMessage!! }
            .sorted()
        log.info("errors : $errors")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(errors.joinToString(", ") { it })
    }
}
