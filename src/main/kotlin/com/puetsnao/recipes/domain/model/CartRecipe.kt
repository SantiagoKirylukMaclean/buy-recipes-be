package com.puetsnao.recipes.domain.model

class CartRecipe(
    val cart: Cart? = null,
    val recipe: Recipe? = null
) : BaseEntity()