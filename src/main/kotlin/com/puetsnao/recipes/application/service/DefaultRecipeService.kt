package com.puetsnao.recipes.application.service

import com.puetsnao.recipes.domain.model.Recipe
import com.puetsnao.recipes.domain.repository.RecipeRepository
import com.puetsnao.recipes.domain.service.RecipeService
import org.springframework.stereotype.Service

@Service
class DefaultRecipeService(
    private val recipeRepository: RecipeRepository
) : RecipeService {
    
    override fun getAllRecipes(): List<Recipe> = recipeRepository.findAll()
    
    override fun getRecipeById(id: Long): Recipe? = recipeRepository.findById(id)
}