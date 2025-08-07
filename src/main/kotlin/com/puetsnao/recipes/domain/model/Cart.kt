package com.puetsnao.recipes.domain.model

class Cart(
    val totalInCents: Int = 0,
    val items: MutableList<CartItem> = mutableListOf(),
    val recipes: MutableList<CartRecipe> = mutableListOf()
) : BaseEntity() {
    fun calculateTotalInCents(): Int {
        val itemsTotal = items.sumOf { it.product?.priceInCents ?: 0 }
        val recipesTotal = recipes.sumOf { it.recipe?.getTotalPriceInCents() ?: 0 }
        return itemsTotal + recipesTotal
    }
}
