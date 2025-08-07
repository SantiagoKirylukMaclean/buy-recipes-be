package com.puetsnao.recipes.application.dto

import com.puetsnao.recipes.domain.model.CartRecipe

data class CartRecipeDto(
    val id: Long?,
    val recipe: RecipeResponseDto
) {
    companion object {
        fun fromDomain(cartRecipe: CartRecipe): CartRecipeDto = CartRecipeDto(
            id = cartRecipe.id,
            recipe = cartRecipe.recipe?.let { RecipeResponseDto.fromDomain(it) } ?: throw IllegalStateException("CartRecipe must have a recipe")
        )
    }
}