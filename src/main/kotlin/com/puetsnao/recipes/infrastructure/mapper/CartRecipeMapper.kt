package com.puetsnao.recipes.infrastructure.mapper

import com.puetsnao.recipes.domain.model.CartRecipe
import com.puetsnao.recipes.infrastructure.entity.CartRecipeEntity
import org.springframework.stereotype.Component

@Component
class CartRecipeMapper(
    private val recipeMapper: RecipeMapper
) {
    fun toEntity(cartRecipe: CartRecipe): CartRecipeEntity {
        val entity = CartRecipeEntity(
            recipe = cartRecipe.recipe?.let { recipeMapper.toEntity(it) }
        )

        // Copy the base entity properties
        entity.id = cartRecipe.id
        entity.createdAt = cartRecipe.createdAt
        entity.updatedAt = cartRecipe.updatedAt

        return entity
    }

    fun toDomain(cartRecipeEntity: CartRecipeEntity): CartRecipe {
        val cartRecipe = CartRecipe(
            recipe = cartRecipeEntity.recipe?.let { recipeMapper.toDomain(it) }
        )

        // Copy the base entity properties
        cartRecipe.id = cartRecipeEntity.id
        cartRecipe.createdAt = cartRecipeEntity.createdAt
        cartRecipe.updatedAt = cartRecipe.updatedAt

        return cartRecipe
    }

    fun toEntityList(cartRecipes: List<CartRecipe>): List<CartRecipeEntity> = cartRecipes.map { toEntity(it) }

    fun toDomainList(cartRecipeEntities: List<CartRecipeEntity>): List<CartRecipe> = cartRecipeEntities.map { toDomain(it) }
}
