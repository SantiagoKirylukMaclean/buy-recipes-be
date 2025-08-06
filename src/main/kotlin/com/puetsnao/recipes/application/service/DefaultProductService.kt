package com.puetsnao.recipes.application.service

import com.puetsnao.recipes.domain.model.Product
import com.puetsnao.recipes.domain.repository.ProductRepository
import com.puetsnao.recipes.domain.service.ProductService
import org.springframework.stereotype.Service

@Service
class DefaultProductService(
    private val productRepository: ProductRepository
) : ProductService {
    
    override fun getAllProducts(): List<Product> = productRepository.findAll()
    
    override fun getProductById(id: Long): Product? = productRepository.findById(id)
}