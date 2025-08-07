package com.puetsnao.recipes.infrastructure.config

import org.springframework.boot.actuate.info.InfoContributor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InfoContributorConfig {

    @Bean
    fun applicationInfoContributor(): InfoContributor {
        return InfoContributor { builder ->
            builder.withDetail("application", mapOf(
                "name" to "Buy Recipes API",
                "description" to "API for managing recipes and shopping carts",
                "version" to "1.0.0",
                "developer" to "Puetsnao Team"
            ))
        }
    }
}