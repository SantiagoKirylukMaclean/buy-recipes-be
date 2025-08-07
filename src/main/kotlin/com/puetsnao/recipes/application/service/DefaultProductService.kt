package com.puetsnao.recipes.application.service

import com.puetsnao.recipes.domain.model.Product
import com.puetsnao.recipes.domain.repository.ProductRepository
import com.puetsnao.recipes.domain.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DefaultProductService(
    private val productRepository: ProductRepository
) : ProductService {
    private val logger = LoggerFactory.getLogger(DefaultProductService::class.java)
    
    override fun getAllProducts(): List<Product> = productRepository.findAll()
    
    override fun getProductById(id: Long): Product? {
        val product = productRepository.findById(id)
        if (product == null) {
            logger.error("Product not found with id: {}", id)
        }
        return product
    }
    
    override fun count(): Long = productRepository.count()
}