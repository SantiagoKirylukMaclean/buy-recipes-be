package com.puetsnao.recipes.infrastructure.repository.jpa

import com.puetsnao.recipes.domain.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaProductRepository : JpaRepository<Product, Long>