package com.puetsnao.recipes.domain.model

import java.time.LocalDateTime

abstract class BaseEntity(
    var id: Long? = null,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
)
