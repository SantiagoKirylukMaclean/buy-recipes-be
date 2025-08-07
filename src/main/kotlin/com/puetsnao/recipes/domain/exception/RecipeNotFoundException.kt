package com.puetsnao.recipes.domain.exception

class RecipeNotFoundException(recipeId: Long) : RuntimeException("Recipe with id $recipeId not found")