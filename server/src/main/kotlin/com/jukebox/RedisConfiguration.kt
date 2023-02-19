package com.jukebox

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.ReactiveValueOperations
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer.UTF_8
import kotlin.reflect.KClass


@Configuration
open class RedisConfiguration(
    private val factory: ReactiveRedisConnectionFactory,
    private val objectMapper: ObjectMapper
) {
    private val keySerializer: RedisSerializer<String> = UTF_8

    fun <T : Any> redisOperationsForValue(clazz: KClass<T>): ReactiveValueOperations<String, T> =
        ReactiveRedisTemplate(
            factory,
            redisSerializationContext(
                redisSerializer(clazz)
            )
        ).opsForValue()

    private fun <T : Any> redisSerializationContext(valueSerializer: RedisSerializer<T>) =
        RedisSerializationContext
            .newSerializationContext<String, T>(keySerializer)
            .value(valueSerializer)
            .build()

    private fun <T : Any> redisSerializer(clazz: KClass<T>): RedisSerializer<T> {
        val jackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(clazz.java)
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper)
        return jackson2JsonRedisSerializer
    }
}