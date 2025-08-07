package com.puetsnao.recipes.infrastructure.controller

import com.puetsnao.recipes.application.dto.StatsDto
import com.puetsnao.recipes.domain.service.CartService
import com.puetsnao.recipes.domain.service.ProductService
import com.puetsnao.recipes.domain.service.RecipeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/stats")
class StatsController(
    private val productService: ProductService,
    private val recipeService: RecipeService,
    private val cartService: CartService
) {
    
    @GetMapping
    fun getStats(): ResponseEntity<StatsDto> {
        val stats = StatsDto(
            totalProducts = productService.count(),
            totalRecipes = recipeService.count(),
            totalCarts = cartService.count()
        )
        return ResponseEntity.ok(stats)
    }
}