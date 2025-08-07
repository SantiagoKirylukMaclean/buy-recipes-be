package com.puetsnao.recipes.domain.model

class Recipe(
    val name: String = "",
    val description: String = "",
    val products: MutableList<RecipeProduct> = mutableListOf()
) : BaseEntity() {
    fun getTotalPriceInCents(): Int = products.sumOf { it.getSubtotalInCents() }
}
