package com.puetsnao.recipes.domain.repository

import com.puetsnao.recipes.domain.model.Cart
import com.puetsnao.recipes.domain.model.Recipe

interface CartRepository {
    fun findAll(): List<Cart>
    fun findById(id: Long): Cart?
    fun save(cart: Cart): Cart
    fun addRecipeToCart(cart: Cart, recipe: Recipe): Cart
    fun removeRecipeFromCart(cart: Cart, recipeId: Long): Cart?
    fun count(): Long
}
