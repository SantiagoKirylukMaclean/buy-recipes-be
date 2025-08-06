package com.puetsnao.recipes.domain.service

import com.puetsnao.recipes.domain.model.Product

interface ProductService {
    fun getAllProducts(): List<Product>
    fun getProductById(id: Long): Product?
}