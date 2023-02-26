package com.jukebox

import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration.ALL
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
open class CORSConfiguration : WebFluxConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry
            .addMapping("/graphql")
            .allowedOrigins(ALL)
            .allowedHeaders(ALL)
            .allowedHeaders(ALL)
    }
}