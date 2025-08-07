package com.puetsnao.recipes.infrastructure.repository

import com.puetsnao.recipes.domain.model.Cart
import com.puetsnao.recipes.domain.repository.CartRepository
import com.puetsnao.recipes.infrastructure.mapper.CartMapper
import com.puetsnao.recipes.infrastructure.repository.jpa.JpaCartRepository
import org.springframework.stereotype.Component

@Component
class DefaultCartRepository(
    private val jpaCartRepository: JpaCartRepository,
    private val cartMapper: CartMapper
) : CartRepository {

    override fun findAll(): List<Cart> = 
        jpaCartRepository.findAll().map { cartMapper.toDomain(it) }

    override fun findById(id: Long): Cart? = 
        jpaCartRepository.findById(id)
            .map { cartMapper.toDomain(it) }
            .orElse(null)

    override fun save(cart: Cart): Cart = 
        cartMapper.toDomain(jpaCartRepository.save(cartMapper.toEntity(cart)))
}