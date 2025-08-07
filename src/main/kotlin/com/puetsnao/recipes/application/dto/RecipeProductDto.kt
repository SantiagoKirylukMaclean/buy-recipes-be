package com.puetsnao.recipes.application.dto

import com.puetsnao.recipes.domain.model.RecipeProduct

data class RecipeProductDto(
    val recipeProductId: Long?,
    val product: ProductDto,
    val quantity: Int,
    val notes: String?
) {
    companion object {
        fun fromDomain(recipeProduct: RecipeProduct): RecipeProductDto = RecipeProductDto(
            recipeProductId = recipeProduct.id,
            product = recipeProduct.product?.let { 
                ProductDto.fromDomain(it) 
            } ?: ProductDto(null, "", 0),
            quantity = recipeProduct.quantity,
            notes = null
        )
    }
}
