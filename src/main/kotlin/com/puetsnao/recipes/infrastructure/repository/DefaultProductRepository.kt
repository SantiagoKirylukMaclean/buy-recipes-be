package com.puetsnao.recipes.infrastructure.repository

import com.puetsnao.recipes.domain.model.Product
import com.puetsnao.recipes.domain.repository.ProductRepository
import com.puetsnao.recipes.infrastructure.repository.jpa.JpaProductRepository
import org.springframework.stereotype.Component

@Component
class DefaultProductRepository(
    private val jpaProductRepository: JpaProductRepository
) : ProductRepository {
    
    override fun findAll(): List<Product> = jpaProductRepository.findAll()
    
    override fun findById(id: Long): Product? = jpaProductRepository.findById(id).orElse(null)
    
    override fun save(product: Product): Product = jpaProductRepository.save(product)
}