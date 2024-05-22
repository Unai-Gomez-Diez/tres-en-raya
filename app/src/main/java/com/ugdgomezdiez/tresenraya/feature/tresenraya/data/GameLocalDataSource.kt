package com.ugdgomezdiez.tresenraya.feature.tresenraya.data

import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Turn

interface GameLocalDataSource {

    fun getGame(): Array<Array<Piece>>
    fun setPiece(board: Array<Array<Piece>>)

    fun setTurn(turn: Turn)

    fun getTurn(): Turn?

    fun cleanBoard()


}