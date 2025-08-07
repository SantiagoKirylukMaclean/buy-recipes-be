package com.puetsnao.recipes.application.dto

import com.puetsnao.recipes.domain.model.Recipe

data class RecipeDto(
    val id: Long?,
    val name: String,
    val description: String,
    val totalPriceInCents: Int,
    val products: List<RecipeProductDto>
) {
    companion object {
        fun fromDomain(recipe: Recipe): RecipeDto = RecipeDto(
            id = recipe.id,
            name = recipe.name,
            description = recipe.description,
            totalPriceInCents = recipe.getTotalPriceInCents(),
            products = recipe.products.map { RecipeProductDto.fromDomain(it) }
        )
    }
}