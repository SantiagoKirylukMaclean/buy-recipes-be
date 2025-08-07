package com.puetsnao.recipes.infrastructure.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class RecipeControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should return all recipes with correct structure`() {
        val result = mockMvc.perform(get("/recipes")
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk)
            .andReturn()

        val responseContent = result.response.contentAsString
        println("[DEBUG_LOG] Response: " + responseContent)

        // Check if the response contains the expected fields
        assert(responseContent.contains("\"id\":"))
        assert(responseContent.contains("\"name\":"))
        assert(responseContent.contains("\"description\":"))
        assert(responseContent.contains("\"totalPriceInCents\":"))
        assert(responseContent.contains("\"products\":"))
        assert(responseContent.contains("\"recipeProductId\":"))
        assert(responseContent.contains("\"product\":"))
        assert(responseContent.contains("\"quantity\":"))
        assert(responseContent.contains("\"notes\":"))
    }

    @Test
    fun `should return recipe by id with correct structure`() {
        val result = mockMvc.perform(get("/recipes/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk)
            .andReturn()

        val responseContent = result.response.contentAsString
        println("[DEBUG_LOG] Response: " + responseContent)

        // Check if the response contains the expected fields
        assert(responseContent.contains("\"id\":"))
        assert(responseContent.contains("\"name\":"))
        assert(responseContent.contains("\"description\":"))
        assert(responseContent.contains("\"totalPriceInCents\":"))
        assert(responseContent.contains("\"products\":"))
        assert(responseContent.contains("\"recipeProductId\":"))
        assert(responseContent.contains("\"product\":"))
        assert(responseContent.contains("\"quantity\":"))
        assert(responseContent.contains("\"notes\":"))
    }

    @Test
    fun `should return 404 when recipe not found`() {
        mockMvc.perform(get("/recipes/999")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound)
    }
}
