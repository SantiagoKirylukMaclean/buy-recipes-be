package com.puetsnao.recipes.domain.service

import com.puetsnao.recipes.domain.model.Cart

interface CartService {
    fun getAllCarts(): List<Cart>
    fun getCartById(id: Long): Cart?
    fun addRecipeToCart(cartId: Long, recipeId: Long): Cart?
}
