package com.puetsnao.recipes.infrastructure.repository

import com.puetsnao.recipes.domain.model.Product
import com.puetsnao.recipes.domain.repository.ProductRepository
import com.puetsnao.recipes.infrastructure.mapper.ProductMapper
import com.puetsnao.recipes.infrastructure.repository.jpa.JpaProductRepository
import org.springframework.stereotype.Component

@Component
class DefaultProductRepository(
    private val jpaProductRepository: JpaProductRepository,
    private val productMapper: ProductMapper
) : ProductRepository {

    override fun findAll(): List<Product> = 
        jpaProductRepository.findAll().map { productMapper.toDomain(it) }

    override fun findById(id: Long): Product? = 
        jpaProductRepository.findById(id)
            .map { productMapper.toDomain(it) }
            .orElse(null)

    override fun save(product: Product): Product = 
        productMapper.toDomain(jpaProductRepository.save(productMapper.toEntity(product)))
        
    override fun count(): Long = jpaProductRepository.count()
}
