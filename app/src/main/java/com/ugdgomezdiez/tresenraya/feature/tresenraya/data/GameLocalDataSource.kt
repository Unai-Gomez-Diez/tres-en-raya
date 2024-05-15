package com.ugdgomezdiez.tresenraya.feature.tresenraya.data

import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece

interface GameLocalDataSource {

    fun getGame(): Array<Array<Piece>>?
    fun setPiece(board: Array<Array<Piece>>)

    fun setTurn(turn: Int)

    fun getTurn(): Int

    fun cleanBoard()


}