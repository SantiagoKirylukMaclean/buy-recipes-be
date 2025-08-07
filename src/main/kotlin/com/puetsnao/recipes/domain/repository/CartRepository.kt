package com.puetsnao.recipes.domain.repository

import com.puetsnao.recipes.domain.model.Cart

interface CartRepository {
    fun findAll(): List<Cart>
    fun findById(id: Long): Cart?
    fun save(cart: Cart): Cart
}