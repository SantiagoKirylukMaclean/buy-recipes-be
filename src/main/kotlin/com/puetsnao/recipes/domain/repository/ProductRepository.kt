package com.puetsnao.recipes.domain.repository

import com.puetsnao.recipes.domain.model.Product

interface ProductRepository {
    fun findAll(): List<Product>
    fun findById(id: Long): Product?
    fun save(product: Product): Product
    fun count(): Long
}