package com.jukebox.service

import com.jukebox.dao.entity.Piece
import com.jukebox.dao.repository.PieceRepository
import com.jukebox.model.types.PieceInput
import com.jukebox.model.types.PieceResponse
import com.jukebox.model.types.Source
import com.netflix.graphql.dgs.*
import reactor.core.publisher.Mono
import java.net.URL
import java.util.UUID

@DgsComponent
class PieceService(private val pieceRepository: PieceRepository) {

    @DgsQuery
    fun piece(@InputArgument id: String): Mono<PieceResponse> {
        return pieceRepository.findById(id).map { p -> PieceResponse(p.id, p.name, p.link, p.duration, p.source) }
    }

    @DgsData(parentType = "MutationResolver")
    fun piece(@InputArgument piece: PieceInput): Mono<String> {
        return Mono.just(UUID.randomUUID().toString())
            .flatMap { id -> pieceRepository.store(Piece(id, piece.name, piece.link, 0L, getType(piece.link))).thenReturn(id) }
    }

    private fun getType(link: URL): Source {
        return when {
            Regex(".*youtube\\.com/.*").matches(link.host) -> return Source.YOUTUBE
            Regex(".*soundcloud\\.com/.*").matches(link.host) -> return Source.SOUNDCLOUD
            else -> Source.UPLOADED
        }
    }
}