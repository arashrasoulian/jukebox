package com.jukebox.dao.repository

import reactor.core.publisher.Mono

interface PersistingRepository<T : RedisKey> {
    fun findById(id: String): Mono<T>
    fun store(entity: T): Mono<Boolean>
    fun delete(entity: T): Mono<Boolean>
}