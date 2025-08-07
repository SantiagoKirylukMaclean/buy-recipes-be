package com.puetsnao.recipes.infrastructure.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "recipes")
class RecipeEntity(
    @NotBlank
    @Column(name = "name", nullable = false)
    val name: String = "",

    @Column(name = "description", nullable = false)
    val description: String = "",

    @OneToMany(mappedBy = "recipe", cascade = [CascadeType.ALL], fetch = FetchType.EAGER, orphanRemoval = true)
    val products: MutableList<RecipeProductEntity> = mutableListOf()
) : BaseEntity()
