package com.puetsnao.recipes.domain.model

class CartItem(
    val cart: Cart? = null,
    val product: Product? = null
) : BaseEntity()