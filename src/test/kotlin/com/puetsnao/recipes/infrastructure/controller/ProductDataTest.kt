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
class ProductDataTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should return products with correct names and prices`() {
        // Test for specific products from V3 migration
        mockMvc.perform(get("/products")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").isArray)
            // Test Spaghetti
            .andExpect(jsonPath("$[?(@.name == 'Spaghetti')].priceInCents").value(350))
            // Test Ground Beef
            .andExpect(jsonPath("$[?(@.name == 'Ground Beef')].priceInCents").value(850))
            // Test Chicken Breast (higher price item)
            .andExpect(jsonPath("$[?(@.name == 'Chicken Breast')].priceInCents").value(950))
            // Test Salt (lower price item)
            .andExpect(jsonPath("$[?(@.name == 'Salt')].priceInCents").value(100))
    }

    @Test
    fun `should return specific product details by id`() {
        // First, get all products to find the correct IDs
        val result = mockMvc.perform(get("/products")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andReturn()
            
        val responseContent = result.response.contentAsString
        
        // Find a product ID for Olive Oil
        val oliveOilPattern = "\\{\"id\":(\\d+),\"name\":\"Olive Oil\",\"priceInCents\":650}".toRegex()
        val oliveOilMatch = oliveOilPattern.find(responseContent)
        val oliveOilId = oliveOilMatch?.groupValues?.get(1)
        
        // Find a product ID for Chocolate Chips
        val chocolateChipsPattern = "\\{\"id\":(\\d+),\"name\":\"Chocolate Chips\",\"priceInCents\":450}".toRegex()
        val chocolateChipsMatch = chocolateChipsPattern.find(responseContent)
        val chocolateChipsId = chocolateChipsMatch?.groupValues?.get(1)
        
        // Test for Olive Oil with dynamic ID
        if (oliveOilId != null) {
            mockMvc.perform(get("/products/$oliveOilId")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.name").value("Olive Oil"))
                .andExpect(jsonPath("$.priceInCents").value(650))
        }
        
        // Test for Chocolate Chips with dynamic ID
        if (chocolateChipsId != null) {
            mockMvc.perform(get("/products/$chocolateChipsId")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.name").value("Chocolate Chips"))
                .andExpect(jsonPath("$.priceInCents").value(450))
        }
    }
    
    @Test
    fun `should verify all products from V3 migration exist`() {
        val result = mockMvc.perform(get("/products")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andReturn()
            
        val responseContent = result.response.contentAsString
        
        // Verify all products from V3 migration exist
        val expectedProducts = listOf(
            "Spaghetti", "Ground Beef", "Tomato Sauce", "Parmesan Cheese", 
            "Garlic", "Olive Oil", "Eggs", "Bacon", "All-Purpose Flour", 
            "Sugar", "Chocolate Chips", "Butter", "Vanilla Extract", 
            "Baking Soda", "Salt", "Chicken Breast", "Rice", "Broccoli", 
            "Carrots", "Onions", "Bell Peppers", "Soy Sauce", "Honey", 
            "Lemon", "Potatoes"
        )
        
        expectedProducts.forEach { product ->
            assert(responseContent.contains("\"name\":\"$product\"")) {
                "Product $product not found in response"
            }
        }
    }
}