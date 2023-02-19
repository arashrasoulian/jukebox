package com.jukebox.dao.repository

interface RedisKey {
    fun getRedisKeySuffix(): String
}