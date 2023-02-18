package com.jukebox.service

import com.jukebox.dao.entity.Piece
import com.jukebox.dao.repository.PieceRepository
import com.jukebox.model.types.PieceInput
import com.jukebox.model.types.PieceResponse
import com.netflix.graphql.dgs.*
import reactor.core.publisher.Mono
import java.util.UUID

@DgsComponent
class PieceService(private val pieceRepository: PieceRepository) {

    @DgsQuery
    fun piece(@InputArgument id: String): Mono<PieceResponse> {
        return pieceRepository.findById(id).map { p -> PieceResponse(p.id, p.name, p.link, p.duration) }
    }

    @DgsData(parentType = "MutationResolver")
    fun piece(@InputArgument piece: PieceInput): Mono<String> {
        return Mono.just(UUID.randomUUID().toString())
            .flatMap { id -> pieceRepository.store(Piece(id, piece.name, piece.link, 0L)).thenReturn(id) }
    }
}