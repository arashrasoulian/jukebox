package com.jukebox.dao.repository

import org.springframework.data.redis.core.ReactiveValueOperations
import reactor.core.publisher.Mono

open class ReactiveRedisRepository<T : RedisKey>(
    private val keyPrefix: String,
    private val valueOps: ReactiveValueOperations<String, T>
) : PersistingRepository<T> {

    override fun findById(id: String): Mono<T> =
        valueOps.get(toKey(id))

    override fun store(entity: T): Mono<Boolean> =
        valueOps.set(toKey(entity), entity)

    override fun delete(entity: T): Mono<Boolean> =
        valueOps.delete(toKey(entity))

    private fun toKey(entity: RedisKey): String =
        toKey(entity.getRedisKeySuffix())

    private fun toKey(id: String): String =
        "${keyPrefix}_$id"
}