package com.jukebox.dao.repository

import com.jukebox.dao.entity.Piece
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PieceRepository : JpaRepository<Piece, Long>