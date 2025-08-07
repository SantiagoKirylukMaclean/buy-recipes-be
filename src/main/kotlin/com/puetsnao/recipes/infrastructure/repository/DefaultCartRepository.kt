package com.puetsnao.recipes.infrastructure.repository

import com.puetsnao.recipes.domain.model.Cart
import com.puetsnao.recipes.domain.model.Recipe
import com.puetsnao.recipes.domain.repository.CartRepository
import com.puetsnao.recipes.infrastructure.entity.CartRecipeEntity
import com.puetsnao.recipes.infrastructure.mapper.CartMapper
import com.puetsnao.recipes.infrastructure.mapper.RecipeMapper
import com.puetsnao.recipes.infrastructure.repository.jpa.JpaCartRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class DefaultCartRepository(
    private val jpaCartRepository: JpaCartRepository,
    private val cartMapper: CartMapper,
    private val recipeMapper: RecipeMapper
) : CartRepository {
    private val logger = LoggerFactory.getLogger(DefaultCartRepository::class.java)

    override fun findAll(): List<Cart> = 
        jpaCartRepository.findAll().map { cartMapper.toDomain(it) }

    override fun findById(id: Long): Cart? = 
        jpaCartRepository.findById(id)
            .map { cartMapper.toDomain(it) }
            .orElse(null)

    override fun save(cart: Cart): Cart = 
        cartMapper.toDomain(jpaCartRepository.save(cartMapper.toEntity(cart)))

    override fun addRecipeToCart(cart: Cart, recipe: Recipe): Cart {
        try {
            // First, find the cart entity directly from the repository to ensure we have the latest version
            val cartId = cart.id ?: throw IllegalArgumentException("Cart ID cannot be null")
            val cartEntityOptional = jpaCartRepository.findById(cartId)
            if (!cartEntityOptional.isPresent) {
                logger.error("Failed to add recipe to cart: Cart not found with ID: {}", cartId)
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
        } catch (e: Exception) {
            logger.error("Error adding recipe to cart: {}", e.message, e)
            throw e
        }
    }
    
    override fun removeRecipeFromCart(cart: Cart, recipeId: Long): Cart? {
        try {
            // First, find the cart entity directly from the repository to ensure we have the latest version
            val cartId = cart.id ?: throw IllegalArgumentException("Cart ID cannot be null")
            val cartEntityOptional = jpaCartRepository.findById(cartId)
            if (!cartEntityOptional.isPresent) {
                logger.error("Failed to remove recipe from cart: Cart not found with ID: {}", cartId)
                throw IllegalArgumentException("Cart not found with ID: $cartId")
            }
            
            val cartEntity = cartEntityOptional.get()
            
            // Find the CartRecipeEntity to remove
            val cartRecipeToRemove = cartEntity.recipes.find { it.recipe?.id == recipeId }
            if (cartRecipeToRemove == null) {
                logger.error("Recipe with ID: {} not found in cart with ID: {}", recipeId, cartId)
                return null
            }
                
            // Remove the CartRecipeEntity from the cart's recipes list
            cartEntity.recipes.remove(cartRecipeToRemove)
            
            // Save and return the updated cart
            val savedCartEntity = jpaCartRepository.save(cartEntity)
            return cartMapper.toDomain(savedCartEntity)
        } catch (e: Exception) {
            logger.error("Error removing recipe from cart: {}", e.message, e)
            throw e
        }
    }
}
