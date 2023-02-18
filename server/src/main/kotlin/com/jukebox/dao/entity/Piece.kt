package com.jukebox.dao.entity

import com.jukebox.dao.repository.RedisKey
import java.net.URL

data class Piece (
    val id: String,
    val name: String,
    val link: URL,
    val duration: Long
) : RedisKey {
    override fun getRedisKeySuffix() = id
}

