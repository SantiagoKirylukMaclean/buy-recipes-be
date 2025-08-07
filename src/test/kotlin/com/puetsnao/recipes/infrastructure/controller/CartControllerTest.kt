package com.puetsnao.recipes.infrastructure.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class CartControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should return all carts`() {
        mockMvc.perform(get("/carts")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").isArray)
    }

    @Test
    fun `should return cart by id`() {
        mockMvc.perform(get("/carts/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.totalInCents").exists())
            .andExpect(jsonPath("$.items").isArray)
            .andExpect(jsonPath("$.recipes").isArray)
    }

    @Test
    fun `should return 404 when cart not found`() {
        mockMvc.perform(get("/carts/999")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound)
    }

    @Test
    fun `should add recipe to cart`() {
        val requestBody = """
            {
                "recipeId": 1
            }
        """.trimIndent()

        mockMvc.perform(post("/carts/1/add_recipe")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.totalInCents").exists())
            .andExpect(jsonPath("$.recipes").isArray)
            .andExpect(jsonPath("$.recipes.length()").value(1))
    }

    @Test
    fun `should return 400 when recipe id is missing`() {
        val requestBody = """
            {
                "recipeId": null
            }
        """.trimIndent()

        mockMvc.perform(post("/carts/1/add_recipe")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `should return 404 when cart not found for add recipe`() {
        val requestBody = """
            {
                "recipeId": 1
            }
        """.trimIndent()

        mockMvc.perform(post("/carts/999/add_recipe")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isNotFound)
    }

    @Test
    fun `should return 404 when recipe not found`() {
        val requestBody = """
            {
                "recipeId": 999
            }
        """.trimIndent()

        mockMvc.perform(post("/carts/1/add_recipe")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isNotFound)
    }
}
