package com.puetsnao.recipes.infrastructure.mapper

import com.puetsnao.recipes.domain.model.RecipeProduct
import com.puetsnao.recipes.infrastructure.entity.RecipeProductEntity
import org.springframework.stereotype.Component

@Component
class RecipeProductMapper(
    private val productMapper: ProductMapper
) {
    fun toEntity(recipeProduct: RecipeProduct): RecipeProductEntity {
        val entity = RecipeProductEntity(
            quantity = recipeProduct.quantity
        )

        // Copy the base entity properties
        entity.id = recipeProduct.id
        entity.createdAt = recipeProduct.createdAt
        entity.updatedAt = recipeProduct.updatedAt

        return entity
    }

    fun toDomain(recipeProductEntity: RecipeProductEntity): RecipeProduct {
        val recipeProduct = RecipeProduct(
            product = recipeProductEntity.product?.let { productMapper.toDomain(it) },
            quantity = recipeProductEntity.quantity
        )

        // Copy the base entity properties
        recipeProduct.id = recipeProductEntity.id
        recipeProduct.createdAt = recipeProductEntity.createdAt
        recipeProduct.updatedAt = recipeProductEntity.updatedAt

        return recipeProduct
    }

    fun toEntityList(recipeProducts: List<RecipeProduct>): List<RecipeProductEntity> = recipeProducts.map { toEntity(it) }

    fun toDomainList(recipeProductEntities: List<RecipeProductEntity>): List<RecipeProduct> = recipeProductEntities.map { toDomain(it) }
}