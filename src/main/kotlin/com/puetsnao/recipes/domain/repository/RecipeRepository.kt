package com.puetsnao.recipes.domain.repository

import com.puetsnao.recipes.domain.model.Recipe

interface RecipeRepository {
    fun findAll(): List<Recipe>
    fun findById(id: Long): Recipe?
    fun save(recipe: Recipe): Recipe
    fun count(): Long
}