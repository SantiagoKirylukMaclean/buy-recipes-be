package com.puetsnao.recipes.infrastructure.repository

import com.puetsnao.recipes.domain.model.Recipe
import com.puetsnao.recipes.domain.repository.RecipeRepository
import com.puetsnao.recipes.infrastructure.mapper.RecipeMapper
import com.puetsnao.recipes.infrastructure.repository.jpa.JpaRecipeRepository
import org.springframework.stereotype.Component

@Component
class DefaultRecipeRepository(
    private val jpaRecipeRepository: JpaRecipeRepository,
    private val recipeMapper: RecipeMapper
) : RecipeRepository {

    override fun findAll(): List<Recipe> = 
        jpaRecipeRepository.findAll().map { recipeMapper.toDomain(it) }

    override fun findById(id: Long): Recipe? = 
        jpaRecipeRepository.findById(id)
            .map { recipeMapper.toDomain(it) }
            .orElse(null)

    override fun save(recipe: Recipe): Recipe = 
        recipeMapper.toDomain(jpaRecipeRepository.save(recipeMapper.toEntity(recipe)))
        
    override fun count(): Long = jpaRecipeRepository.count()
}