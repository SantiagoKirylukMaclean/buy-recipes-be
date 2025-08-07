package com.puetsnao.recipes.infrastructure.mapper

import com.puetsnao.recipes.domain.model.Cart
import com.puetsnao.recipes.infrastructure.entity.CartEntity
import org.springframework.stereotype.Component

@Component
class CartMapper(
    private val cartItemMapper: CartItemMapper,
    private val cartRecipeMapper: CartRecipeMapper
) {
    fun toEntity(cart: Cart): CartEntity {
        val entity = CartEntity(
            totalInCents = cart.totalInCents
        )

        // Copy the base entity properties
        entity.id = cart.id
        entity.createdAt = cart.createdAt
        entity.updatedAt = cart.updatedAt

        // Add items and recipes
        entity.items.addAll(cartItemMapper.toEntityList(cart.items))
        entity.recipes.addAll(cartRecipeMapper.toEntityList(cart.recipes))

        return entity
    }

    fun toDomain(cartEntity: CartEntity): Cart {
        val cart = Cart(
            totalInCents = cartEntity.totalInCents
        )

        // Copy the base entity properties
        cart.id = cartEntity.id
        cart.createdAt = cartEntity.createdAt
        cart.updatedAt = cartEntity.updatedAt

        // Add items and recipes
        cart.items.addAll(cartItemMapper.toDomainList(cartEntity.items))
        cart.recipes.addAll(cartRecipeMapper.toDomainList(cartEntity.recipes))

        return cart
    }

    fun toEntityList(carts: List<Cart>): List<CartEntity> = carts.map { toEntity(it) }

    fun toDomainList(cartEntities: List<CartEntity>): List<Cart> = cartEntities.map { toDomain(it) }
}