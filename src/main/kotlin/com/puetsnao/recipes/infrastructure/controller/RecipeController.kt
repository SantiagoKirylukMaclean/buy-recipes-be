package com.puetsnao.recipes.infrastructure.controller

import com.puetsnao.recipes.application.dto.RecipeResponseDto
import com.puetsnao.recipes.domain.service.RecipeService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/recipes")
@Tag(name = "Recipes", description = "Recipe management endpoints")
class RecipeController(
    private val recipeService: RecipeService
) {

    @GetMapping
    @Operation(summary = "Get all recipes", description = "Returns a list of all available recipes with their details")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Successfully retrieved recipes"),
        ApiResponse(responseCode = "500", description = "Internal server error")
    )
    fun getAllRecipes(): ResponseEntity<List<RecipeResponseDto>> {
        val recipes = recipeService.getAllRecipes()
        return ResponseEntity.ok(recipes.map { RecipeResponseDto.fromDomain(it) })
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get recipe by ID", description = "Returns a recipe by its ID")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Successfully retrieved recipe"),
        ApiResponse(responseCode = "404", description = "Recipe not found"),
        ApiResponse(responseCode = "500", description = "Internal server error")
    )
    fun getRecipeById(@PathVariable id: Long): ResponseEntity<RecipeResponseDto> {
        val recipe = recipeService.getRecipeById(id)
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(RecipeResponseDto.fromDomain(recipe))
    }
}
