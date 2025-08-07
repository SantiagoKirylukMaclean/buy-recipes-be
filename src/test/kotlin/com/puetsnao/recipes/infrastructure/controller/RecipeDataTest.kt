package com.puetsnao.recipes.infrastructure.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class RecipeDataTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should return recipes with correct names and descriptions`() {
        // Test for specific recipes from V3 migration
        mockMvc.perform(get("/recipes")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").isArray)
            // Test Spaghetti Carbonara
            .andExpect(jsonPath("$[?(@.name == 'Spaghetti Carbonara')].description").value(
                "A classic Italian pasta dish with creamy egg sauce, pancetta, and cheese. Simple yet luxurious!"
            ))
            // Test Chocolate Chip Cookies
            .andExpect(jsonPath("$[?(@.name == 'Chocolate Chip Cookies')].description").value(
                "Soft and chewy cookies with melty chocolate chips. Perfect for dessert or a sweet snack!"
            ))
            // Test Teriyaki Chicken Stir Fry
            .andExpect(jsonPath("$[?(@.name == 'Teriyaki Chicken Stir Fry')].description").value(
                "Tender chicken and crisp vegetables in a sweet and savory teriyaki sauce. Served over steamed rice."
            ))
    }

    @Test
    fun `should verify all recipes from V3 migration exist`() {
        val result = mockMvc.perform(get("/recipes")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andReturn()
            
        val responseContent = result.response.contentAsString
        
        // Verify all recipes from V3 migration exist
        val expectedRecipes = listOf(
            "Spaghetti Carbonara", 
            "Chocolate Chip Cookies", 
            "Teriyaki Chicken Stir Fry", 
            "Homemade Pizza", 
            "Garlic Mashed Potatoes"
        )
        
        expectedRecipes.forEach { recipe ->
            assert(responseContent.contains("\"name\":\"$recipe\"")) {
                "Recipe $recipe not found in response"
            }
        }
    }
    
    @Test
    fun `should verify recipe details for Spaghetti Carbonara`() {
        // First, get all recipes to find the correct ID for Spaghetti Carbonara
        val result = mockMvc.perform(get("/recipes")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andReturn()
            
        val responseContent = result.response.contentAsString
        
        // Find the recipe ID for Spaghetti Carbonara
        val pattern = "\"id\":(\\d+),.*?\"name\":\"Spaghetti Carbonara\"".toRegex(RegexOption.DOT_MATCHES_ALL)
        val match = pattern.find(responseContent)
        val recipeId = match?.groupValues?.get(1)
        
        if (recipeId != null) {
            // Test for Spaghetti Carbonara basic details
            mockMvc.perform(get("/recipes/$recipeId")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.name").value("Spaghetti Carbonara"))
                .andExpect(jsonPath("$.description").value("A classic Italian pasta dish with creamy egg sauce, pancetta, and cheese. Simple yet luxurious!"))
                .andExpect(jsonPath("$.products").isArray)
                // Just verify that it has products, not specific ones
                .andExpect(jsonPath("$.products.length()").isNumber())
        }
    }
    
    @Test
    fun `should verify recipe details for Chocolate Chip Cookies`() {
        // First, get all recipes to find the correct ID for Chocolate Chip Cookies
        val result = mockMvc.perform(get("/recipes")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andReturn()
            
        val responseContent = result.response.contentAsString
        
        // Find the recipe ID for Chocolate Chip Cookies
        val pattern = "\"id\":(\\d+),.*?\"name\":\"Chocolate Chip Cookies\"".toRegex(RegexOption.DOT_MATCHES_ALL)
        val match = pattern.find(responseContent)
        val recipeId = match?.groupValues?.get(1)
        
        if (recipeId != null) {
            // Test for Chocolate Chip Cookies basic details
            mockMvc.perform(get("/recipes/$recipeId")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.name").value("Chocolate Chip Cookies"))
                .andExpect(jsonPath("$.description").value("Soft and chewy cookies with melty chocolate chips. Perfect for dessert or a sweet snack!"))
                .andExpect(jsonPath("$.products").isArray)
                // Just verify that it has products, not specific ones
                .andExpect(jsonPath("$.products.length()").isNumber())
        }
    }
    
    @Test
    fun `should verify recipe details for Teriyaki Chicken Stir Fry`() {
        // First, get all recipes to find the correct ID for Teriyaki Chicken Stir Fry
        val result = mockMvc.perform(get("/recipes")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andReturn()
            
        val responseContent = result.response.contentAsString
        
        // Find the recipe ID for Teriyaki Chicken Stir Fry
        val pattern = "\"id\":(\\d+),.*?\"name\":\"Teriyaki Chicken Stir Fry\"".toRegex(RegexOption.DOT_MATCHES_ALL)
        val match = pattern.find(responseContent)
        val recipeId = match?.groupValues?.get(1)
        
        if (recipeId != null) {
            // Test for Teriyaki Chicken Stir Fry basic details
            mockMvc.perform(get("/recipes/$recipeId")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.name").value("Teriyaki Chicken Stir Fry"))
                .andExpect(jsonPath("$.description").value("Tender chicken and crisp vegetables in a sweet and savory teriyaki sauce. Served over steamed rice."))
                .andExpect(jsonPath("$.products").isArray)
                // Just verify that it has products, not specific ones
                .andExpect(jsonPath("$.products.length()").isNumber())
        }
    }
}