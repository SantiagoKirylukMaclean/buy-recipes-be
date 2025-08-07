package com.puetsnao.recipes.application.dto

import com.puetsnao.recipes.domain.model.CartItem

data class CartItemDto(
    val id: Long?,
    val product: ProductDto
) {
    companion object {
        fun fromDomain(cartItem: CartItem): CartItemDto = CartItemDto(
            id = cartItem.id,
            product = cartItem.product?.let { ProductDto.fromDomain(it) } ?: throw IllegalStateException("CartItem must have a product")
        )
    }
}