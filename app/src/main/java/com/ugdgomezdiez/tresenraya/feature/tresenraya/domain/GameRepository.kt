package com.ugdgomezdiez.tresenraya.feature.tresenraya.domain

interface GameRepository {
    fun getGame(): Array<Array<Piece>>

    fun setPiece(piece:Piece): Boolean

    fun getTurn(): Turn

    fun cleanBoard()
}