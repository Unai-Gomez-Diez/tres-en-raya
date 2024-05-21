package com.ugdgomezdiez.tresenraya.feature.tresenraya.domain

interface GameRepository {
     suspend fun getGame(): Array<Array<Piece>>

    suspend fun setPiece(piece:Piece): Boolean

    fun getTurn(): Int

    fun cleanBoard()
}