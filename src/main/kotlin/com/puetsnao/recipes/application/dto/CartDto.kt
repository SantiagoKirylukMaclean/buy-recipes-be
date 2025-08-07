package com.puetsnao.recipes.application.dto

import com.puetsnao.recipes.domain.model.Cart

data class CartDto(
    val id: Long?,
    val totalInCents: Int,
    val items: List<CartItemDto>,
    val recipes: List<CartRecipeDto>
) {
    companion object {
        fun fromDomain(cart: Cart): CartDto = CartDto(
            id = cart.id,
            totalInCents = cart.calculateTotalInCents(),
            items = cart.items.map { CartItemDto.fromDomain(it) },
            recipes = cart.recipes.map { CartRecipeDto.fromDomain(it) }
        )
    }
}