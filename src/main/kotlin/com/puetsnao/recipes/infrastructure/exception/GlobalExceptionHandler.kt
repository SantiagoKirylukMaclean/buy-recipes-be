package com.puetsnao.recipes.infrastructure.exception

import com.puetsnao.recipes.domain.exception.RecipeNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(RecipeNotFoundException::class)
    fun handleRecipeNotFoundException(ex: RecipeNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            message = ex.message ?: "Recipe not found",
            error = "Recipe not found"
        )
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    data class ErrorResponse(
        val status: Int,
        val message: String,
        val error: String
    )
}