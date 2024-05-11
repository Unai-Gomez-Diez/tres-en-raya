package com.ugdgomezdiez.tresenraya.feature.tresenraya.domain

interface GameRepository {
    fun getGame(): Array<Array<Piece?>>

    fun setGameTurn(turn:Piece): Boolean
}