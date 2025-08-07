package com.puetsnao.recipes.infrastructure.repository

import com.puetsnao.recipes.domain.model.Cart
import com.puetsnao.recipes.domain.model.Recipe
import com.puetsnao.recipes.domain.repository.CartRepository
import com.puetsnao.recipes.infrastructure.entity.CartRecipeEntity
import com.puetsnao.recipes.infrastructure.mapper.CartMapper
import com.puetsnao.recipes.infrastructure.mapper.RecipeMapper
import com.puetsnao.recipes.infrastructure.repository.jpa.JpaCartRepository
import org.springframework.stereotype.Component

@Component
class DefaultCartRepository(
    private val jpaCartRepository: JpaCartRepository,
    private val cartMapper: CartMapper,
    private val recipeMapper: RecipeMapper
) : CartRepository {

    override fun findAll(): List<Cart> = 
        jpaCartRepository.findAll().map { cartMapper.toDomain(it) }

    override fun findById(id: Long): Cart? = 
        jpaCartRepository.findById(id)
            .map { cartMapper.toDomain(it) }
            .orElse(null)

    override fun save(cart: Cart): Cart = 
        cartMapper.toDomain(jpaCartRepository.save(cartMapper.toEntity(cart)))

    override fun addRecipeToCart(cart: Cart, recipe: Recipe): Cart {
        // First, find the cart entity directly from the repository to ensure we have the latest version
        val cartId = cart.id ?: throw IllegalArgumentException("Cart ID cannot be null")
        val cartEntityOptional = jpaCartRepository.findById(cartId)
        if (!cartEntityOptional.isPresent) {
            throw IllegalArgumentException("Cart not found with ID: $cartId")
        }
        
        val cartEntity = cartEntityOptional.get()
        val recipeEntity = recipeMapper.toEntity(recipe)

        // Create a new CartRecipeEntity with the cart and recipe
        val cartRecipeEntity = CartRecipeEntity(
            cart = cartEntity,
            recipe = recipeEntity
        )

        // Add the CartRecipeEntity to the cart's recipes list
        cartEntity.recipes.add(cartRecipeEntity)

        // Save and return the updated cart
        val savedCartEntity = jpaCartRepository.save(cartEntity)
        return cartMapper.toDomain(savedCartEntity)
    }
    
    override fun removeRecipeFromCart(cart: Cart, recipeId: Long): Cart? {
        // First, find the cart entity directly from the repository to ensure we have the latest version
        val cartId = cart.id ?: throw IllegalArgumentException("Cart ID cannot be null")
        val cartEntityOptional = jpaCartRepository.findById(cartId)
        if (!cartEntityOptional.isPresent) {
            throw IllegalArgumentException("Cart not found with ID: $cartId")
        }
        
        val cartEntity = cartEntityOptional.get()
        
        // Find the CartRecipeEntity to remove
        val cartRecipeToRemove = cartEntity.recipes.find { it.recipe?.id == recipeId }
            ?: return null
            
        // Remove the CartRecipeEntity from the cart's recipes list
        cartEntity.recipes.remove(cartRecipeToRemove)
        
        // Save and return the updated cart
        val savedCartEntity = jpaCartRepository.save(cartEntity)
        return cartMapper.toDomain(savedCartEntity)
    }
}
