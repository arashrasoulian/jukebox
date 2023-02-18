package com.jukebox.dao.entity

import com.jukebox.dao.repository.RedisKey
import com.jukebox.model.types.Source
import java.net.URL

data class Piece (
    val id: String,
    val name: String,
    val link: URL,
    val duration: Long,
    val source: Source
) : RedisKey {
    override fun getRedisKeySuffix() = id
}

