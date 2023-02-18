package com.jukebox.service

import com.jukebox.dao.repository.PieceRepository
import com.jukebox.model.types.PieceResponse
import com.netflix.graphql.dgs.*
import reactor.core.publisher.Mono

@DgsComponent
class PieceService(private val pieceRepository: PieceRepository) {

    @DgsQuery
    fun piece(
        @InputArgument id: Long,
        dfe: DgsDataFetchingEnvironment
    ): Mono<PieceResponse> {
        val piece = pieceRepository.getReferenceById(id)
        return Mono.just(PieceResponse(piece.id!!.toString(), piece.name, piece.link, piece.duration))
    }
}