package com.puetsnao.recipes.application.dto

import jakarta.validation.constraints.NotNull

data class AddRecipeToCartRequestDto(
    @field:NotNull(message = "Recipe ID is required")
    val recipeId: Long?
)