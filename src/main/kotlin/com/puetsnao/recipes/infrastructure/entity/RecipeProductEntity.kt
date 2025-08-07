package com.puetsnao.recipes.infrastructure.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.Min

@Entity
@Table(name = "recipe_products")
class RecipeProductEntity(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    val recipe: RecipeEntity? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    val product: ProductEntity? = null,

    @Min(1)
    @Column(name = "quantity", nullable = false)
    val quantity: Int = 1
) : BaseEntity()