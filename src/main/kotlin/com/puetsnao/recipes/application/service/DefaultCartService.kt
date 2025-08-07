package com.puetsnao.recipes.application.service

import com.puetsnao.recipes.domain.model.Cart
import com.puetsnao.recipes.domain.repository.CartRepository
import com.puetsnao.recipes.domain.service.CartService
import org.springframework.stereotype.Service

@Service
class DefaultCartService(
    private val cartRepository: CartRepository
) : CartService {
    
    override fun getAllCarts(): List<Cart> = cartRepository.findAll()
    
    override fun getCartById(id: Long): Cart? = cartRepository.findById(id)
}