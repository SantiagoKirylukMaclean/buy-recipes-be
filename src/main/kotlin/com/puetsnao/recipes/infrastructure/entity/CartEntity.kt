package com.puetsnao.recipes.infrastructure.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.PositiveOrZero

@Entity
@Table(name = "carts")
class CartEntity(
    @PositiveOrZero
    @Column(name = "total_in_cents", nullable = false)
    val totalInCents: Int = 0,

    @OneToMany(mappedBy = "cart", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    val items: MutableList<CartItemEntity> = mutableListOf(),

    @OneToMany(mappedBy = "cart", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    val recipes: MutableList<CartRecipeEntity> = mutableListOf()
) : BaseEntity()
