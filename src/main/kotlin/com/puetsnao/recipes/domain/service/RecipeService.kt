package com.puetsnao.recipes.domain.service

import com.puetsnao.recipes.domain.model.Recipe

interface RecipeService {
    fun getAllRecipes(): List<Recipe>
    fun getRecipeById(id: Long): Recipe?
}