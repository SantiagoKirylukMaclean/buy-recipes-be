package com.puetsnao.recipes.application.dto

import com.puetsnao.recipes.domain.model.Recipe

data class RecipeResponseDto(
    val id: Long?,
    val name: String,
    val description: String,
    val totalPriceInCents: Int,
    val products: List<RecipeProductResponseDto>
) {
    companion object {
        fun fromDomain(recipe: Recipe): RecipeResponseDto = RecipeResponseDto(
            id = recipe.id,
            name = recipe.name,
            description = recipe.description,
            totalPriceInCents = recipe.getTotalPriceInCents(),
            products = recipe.products.map { RecipeProductResponseDto.fromDomain(it) }
        )
    }
}

data class RecipeProductResponseDto(
    val recipeProductId: Long?,
    val product: ProductDto,
    val quantity: Int,
    val notes: String?
) {
    companion object {
        fun fromDomain(recipeProduct: com.puetsnao.recipes.domain.model.RecipeProduct): RecipeProductResponseDto = RecipeProductResponseDto(
            recipeProductId = recipeProduct.id,
            product = recipeProduct.product?.let { 
                ProductDto.fromDomain(it) 
            } ?: ProductDto(null, "", 0),
            quantity = recipeProduct.quantity,
            notes = null
        )
    }
}