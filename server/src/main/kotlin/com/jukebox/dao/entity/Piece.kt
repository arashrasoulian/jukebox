package com.jukebox.dao.entity

import java.net.URI
import java.net.URL
import javax.persistence.*

@Entity
@Table(name= "piece")
data class Piece (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    val id: Long? = null,
    val name: String? = null,
    val link: URL? = null,
    val duration: Long? = null
) {


}

