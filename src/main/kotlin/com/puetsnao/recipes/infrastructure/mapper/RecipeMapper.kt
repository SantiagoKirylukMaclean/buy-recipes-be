package com.puetsnao.recipes.infrastructure.mapper

import com.puetsnao.recipes.domain.model.Recipe
import com.puetsnao.recipes.infrastructure.entity.RecipeEntity
import org.springframework.stereotype.Component

@Component
class RecipeMapper(
    private val recipeProductMapper: RecipeProductMapper
) {
    fun toEntity(recipe: Recipe): RecipeEntity {
        val entity = RecipeEntity(
            name = recipe.name,
            description = recipe.description
        )

        // Copy the base entity properties
        entity.id = recipe.id
        entity.createdAt = recipe.createdAt
        entity.updatedAt = recipe.updatedAt

        // Add products
        entity.products.addAll(recipeProductMapper.toEntityList(recipe.products))

        return entity
    }

    fun toDomain(recipeEntity: RecipeEntity): Recipe {
        val recipe = Recipe(
            name = recipeEntity.name,
            description = recipeEntity.description
        )

        // Copy the base entity properties
        recipe.id = recipeEntity.id
        recipe.createdAt = recipeEntity.createdAt
        recipe.updatedAt = recipeEntity.updatedAt

        // Add products
        recipe.products.addAll(recipeProductMapper.toDomainList(recipeEntity.products))

        return recipe
    }

    fun toEntityList(recipes: List<Recipe>): List<RecipeEntity> = recipes.map { toEntity(it) }

    fun toDomainList(recipeEntities: List<RecipeEntity>): List<Recipe> = recipeEntities.map { toDomain(it) }
}
