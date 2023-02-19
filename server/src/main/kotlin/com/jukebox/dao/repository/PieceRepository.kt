package com.jukebox.dao.repository

import com.jukebox.RedisConfiguration
import com.jukebox.dao.entity.Piece
import com.jukebox.model.types.Source
import org.springframework.stereotype.Service
import java.net.URL

@Service
class PieceRepository(redisConfiguration: RedisConfiguration) : ReactiveRedisRepository<Piece>(
    "piece",
    redisConfiguration.redisOperationsForValue(Piece::class)
) {
    //dummy data to test with
    init {
        store(Piece("1", "soundcloud", URL("https://soundcloud.com/frolov-ua/ream-daranoi-fai-yen-frolov-edit"), 0L, Source.SOUNDCLOUD)).block()
        store(Piece("2", "soundcloud", URL("https://www.youtube.com/watch?v=V7yKT6tkpV4"), 0L, Source.YOUTUBE)).block()
    }
}