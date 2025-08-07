package com.puetsnao.recipes.domain.model

class RecipeProduct(
    val recipe: Recipe? = null,
    val product: Product? = null,
    val quantity: Int = 1
) : BaseEntity() {
    fun getSubtotalInCents(): Int = (product?.priceInCents ?: 0) * quantity
}