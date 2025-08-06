package com.puetsnao.recipes.application.dto

import com.puetsnao.recipes.domain.model.Product

data class ProductDto(
    val id: Long?,
    val name: String,
    val priceInCents: Int
) {
    companion object {
        fun fromDomain(product: Product): ProductDto = ProductDto(
            id = product.id,
            name = product.name,
            priceInCents = product.priceInCents
        )
    }
}