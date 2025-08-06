package com.puetsnao.recipes.infrastructure.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

@Entity
@Table(name = "products")
class ProductEntity(
    @NotBlank
    @Column(name = "name", nullable = false)
    val name: String = "",

    @Positive
    @Column(name = "price_in_cents", nullable = false)
    val priceInCents: Int = 0
) : BaseEntity()