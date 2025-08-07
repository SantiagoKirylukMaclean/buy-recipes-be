package com.puetsnao.recipes.infrastructure.repository.jpa

import com.puetsnao.recipes.infrastructure.entity.RecipeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaRecipeRepository : JpaRepository<RecipeEntity, Long>