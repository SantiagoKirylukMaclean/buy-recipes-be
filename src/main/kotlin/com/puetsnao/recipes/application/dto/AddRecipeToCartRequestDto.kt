package com.puetsnao.recipes.application.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class AddRecipeToCartRequestDto(
    @field:NotNull(message = "Recipe ID is required")
    @field:Positive(message = "Recipe ID must be a positive number")
    val recipeId: Long?
)