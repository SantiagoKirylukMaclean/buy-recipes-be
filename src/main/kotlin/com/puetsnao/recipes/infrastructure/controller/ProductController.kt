package com.puetsnao.recipes.infrastructure.controller

import com.puetsnao.recipes.application.dto.ProductDto
import com.puetsnao.recipes.domain.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService
) {
    
    @GetMapping
    fun getAllProducts(): ResponseEntity<List<ProductDto>> {
        val products = productService.getAllProducts()
        return ResponseEntity.ok(products.map { ProductDto.fromDomain(it) })
    }
    
    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<ProductDto> {
        val product = productService.getProductById(id)
            ?: return ResponseEntity.notFound().build()
        
        return ResponseEntity.ok(ProductDto.fromDomain(product))
    }
}