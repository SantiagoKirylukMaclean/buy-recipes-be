package com.puetsnao.recipes.infrastructure.controller

import com.puetsnao.recipes.application.dto.CartDto
import com.puetsnao.recipes.domain.service.CartService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/carts")
class CartController(
    private val cartService: CartService
) {
    
    @GetMapping
    fun getAllCarts(): ResponseEntity<List<CartDto>> {
        val carts = cartService.getAllCarts()
        return ResponseEntity.ok(carts.map { CartDto.fromDomain(it) })
    }
    
    @GetMapping("/{id}")
    fun getCartById(@PathVariable id: Long): ResponseEntity<CartDto> {
        val cart = cartService.getCartById(id)
            ?: return ResponseEntity.notFound().build()
        
        return ResponseEntity.ok(CartDto.fromDomain(cart))
    }
}