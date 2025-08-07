package com.puetsnao.recipes.application.service

import com.puetsnao.recipes.domain.model.Recipe
import com.puetsnao.recipes.domain.repository.RecipeRepository
import com.puetsnao.recipes.domain.service.RecipeService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DefaultRecipeService(
    private val recipeRepository: RecipeRepository
) : RecipeService {
    private val logger = LoggerFactory.getLogger(DefaultRecipeService::class.java)
    
    override fun getAllRecipes(): List<Recipe> = recipeRepository.findAll()
    
    override fun getRecipeById(id: Long): Recipe? {
        val recipe = recipeRepository.findById(id)
        if (recipe == null) {
            logger.error("Recipe not found with id: {}", id)
        }
        return recipe
    }
}