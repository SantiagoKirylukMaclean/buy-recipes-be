package com.puetsnao.recipes.application.service

import com.puetsnao.recipes.domain.exception.RecipeNotFoundException
import com.puetsnao.recipes.domain.model.Cart
import com.puetsnao.recipes.domain.repository.CartRepository
import com.puetsnao.recipes.domain.repository.RecipeRepository
import com.puetsnao.recipes.domain.service.CartService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DefaultCartService(
    private val cartRepository: CartRepository,
    private val recipeRepository: RecipeRepository
) : CartService {
    private val logger = LoggerFactory.getLogger(DefaultCartService::class.java)

    override fun getAllCarts(): List<Cart> = cartRepository.findAll()

    override fun getCartById(id: Long): Cart? = cartRepository.findById(id)

    override fun addRecipeToCart(cartId: Long, recipeId: Long): Cart? {
        // Find the cart and recipe
        val cart = cartRepository.findById(cartId)
        if (cart == null) {
            logger.error("Cart not found with id: {}", cartId)
            return null
        }
        
        val recipe = recipeRepository.findById(recipeId)
        if (recipe == null) {
            logger.error("Recipe not found with id: {}", recipeId)
            throw RecipeNotFoundException(recipeId)
        }

        // Check if the recipe is already in the cart
        val recipeAlreadyInCart = cart.recipes.any { it.recipe?.id == recipe.id }
        if (recipeAlreadyInCart) {
            // Recipe already in cart, just return the cart
            return cart
        }

        // Add the recipe to the cart using the repository method
        return cartRepository.addRecipeToCart(cart, recipe)
    }
    
    override fun removeRecipeFromCart(cartId: Long, recipeId: Long): Cart? {
        // Find the cart
        val cart = cartRepository.findById(cartId)
        if (cart == null) {
            logger.error("Cart not found with id: {}", cartId)
            return null
        }
        
        // Check if the recipe exists
        val recipeExists = recipeRepository.findById(recipeId) != null
        if (!recipeExists) {
            logger.error("Recipe not found with id: {}", recipeId)
            throw RecipeNotFoundException(recipeId)
        }
        
        // Check if the recipe is in the cart
        val recipeInCart = cart.recipes.any { it.recipe?.id == recipeId }
        if (!recipeInCart) {
            // Recipe not in cart, return null to indicate not found
            logger.error("Recipe with id: {} not found in cart with id: {}", recipeId, cartId)
            return null
        }
        
        // Remove the recipe from the cart using the repository method
        return cartRepository.removeRecipeFromCart(cart, recipeId)
    }
}
