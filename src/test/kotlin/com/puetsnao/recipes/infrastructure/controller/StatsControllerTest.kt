package com.puetsnao.recipes.infrastructure.controller

import com.puetsnao.recipes.domain.service.CartService
import com.puetsnao.recipes.domain.service.ProductService
import com.puetsnao.recipes.domain.service.RecipeService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(StatsController::class)
class StatsControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var productService: ProductService

    @MockBean
    private lateinit var recipeService: RecipeService

    @MockBean
    private lateinit var cartService: CartService

    @Test
    fun `should return stats with correct counts`() {
        // Given
        `when`(productService.count()).thenReturn(10L)
        `when`(recipeService.count()).thenReturn(5L)
        `when`(cartService.count()).thenReturn(3L)

        // When/Then
        mockMvc.perform(get("/stats"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.totalProducts").value(10))
            .andExpect(jsonPath("$.totalRecipes").value(5))
            .andExpect(jsonPath("$.totalCarts").value(3))
    }
}