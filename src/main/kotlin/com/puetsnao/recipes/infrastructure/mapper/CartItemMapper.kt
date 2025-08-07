package com.puetsnao.recipes.infrastructure.mapper

import com.puetsnao.recipes.domain.model.CartItem
import com.puetsnao.recipes.infrastructure.entity.CartItemEntity
import org.springframework.stereotype.Component

@Component
class CartItemMapper(
    private val productMapper: ProductMapper
) {
    fun toEntity(cartItem: CartItem): CartItemEntity {
        val entity = CartItemEntity(
            product = cartItem.product?.let { productMapper.toEntity(it) }
        )

        // Copy the base entity properties
        entity.id = cartItem.id
        entity.createdAt = cartItem.createdAt
        entity.updatedAt = cartItem.updatedAt

        return entity
    }

    fun toDomain(cartItemEntity: CartItemEntity): CartItem {
        val cartItem = CartItem(
            product = cartItemEntity.product?.let { productMapper.toDomain(it) }
        )

        // Copy the base entity properties
        cartItem.id = cartItemEntity.id
        cartItem.createdAt = cartItemEntity.createdAt
        cartItem.updatedAt = cartItemEntity.updatedAt

        return cartItem
    }

    fun toEntityList(cartItems: List<CartItem>): List<CartItemEntity> = cartItems.map { toEntity(it) }

    fun toDomainList(cartItemEntities: List<CartItemEntity>): List<CartItem> = cartItemEntities.map { toDomain(it) }
}