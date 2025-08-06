package com.puetsnao.recipes.infrastructure.mapper

import com.puetsnao.recipes.domain.model.Product
import com.puetsnao.recipes.infrastructure.entity.ProductEntity
import org.springframework.stereotype.Component

@Component
class ProductMapper {
    fun toEntity(product: Product): ProductEntity {
        val entity = ProductEntity(
            name = product.name,
            priceInCents = product.priceInCents
        )

        // Copy the base entity properties
        entity.id = product.id
        entity.createdAt = product.createdAt
        entity.updatedAt = product.updatedAt

        return entity
    }

    fun toDomain(productEntity: ProductEntity): Product {
        val product = Product(
            name = productEntity.name,
            priceInCents = productEntity.priceInCents
        )

        // Copy the base entity properties
        product.id = productEntity.id
        product.createdAt = productEntity.createdAt
        product.updatedAt = productEntity.updatedAt

        return product
    }

    fun toEntityList(products: List<Product>): List<ProductEntity> = products.map { toEntity(it) }

    fun toDomainList(productEntities: List<ProductEntity>): List<Product> = productEntities.map { toDomain(it) }
}
