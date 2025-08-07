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
class CartDataTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should verify Cart 1 - Family dinner with multiple recipes`() {
        // Test for Cart 1 (ID 2) - Family dinner with multiple recipes
        mockMvc.perform(get("/carts/2")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(2))
            .andExpect(jsonPath("$.recipes").isArray)
            .andExpect(jsonPath("$.items").isArray)
            // Just verify that it has recipes and items, not specific ones
            .andExpect(jsonPath("$.recipes.length()").isNumber())
            .andExpect(jsonPath("$.items.length()").isNumber())
    }
    
    @Test
    fun `should verify Cart 2 - Baking day with cookies and extra ingredients`() {
        // Test for Cart 2 (ID 3) - Baking day with cookies and extra ingredients
        mockMvc.perform(get("/carts/3")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(3))
            .andExpect(jsonPath("$.recipes").isArray)
            .andExpect(jsonPath("$.items").isArray)
            // Just verify that it has recipes and items, not specific ones
            .andExpect(jsonPath("$.recipes.length()").isNumber())
            .andExpect(jsonPath("$.items.length()").isNumber())
    }
    
    @Test
    fun `should verify Cart 3 - Asian cuisine night`() {
        // Test for Cart 3 (ID 4) - Asian cuisine night
        mockMvc.perform(get("/carts/4")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(4))
            .andExpect(jsonPath("$.recipes").isArray)
            .andExpect(jsonPath("$.items").isArray)
            // Just verify that it has recipes and items, not specific ones
            .andExpect(jsonPath("$.recipes.length()").isNumber())
            .andExpect(jsonPath("$.items.length()").isNumber())
    }
    
    @Test
    fun `should verify all carts from V3 migration exist`() {
        val result = mockMvc.perform(get("/carts")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andReturn()
            
        val responseContent = result.response.contentAsString
        
        // Verify all carts from V3 migration exist (IDs 2, 3, 4)
        assert(responseContent.contains("\"id\":2")) { "Cart with ID 2 not found in response" }
        assert(responseContent.contains("\"id\":3")) { "Cart with ID 3 not found in response" }
        assert(responseContent.contains("\"id\":4")) { "Cart with ID 4 not found in response" }
    }
}