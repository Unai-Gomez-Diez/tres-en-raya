package com.ugdgomezdiez.tresenraya.feature.tresenraya.data

import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece

interface GameLocalDataSource {

    fun getGame(): Array<Array<Piece?>>
    fun setGameTurn(trun: Array<Array<Piece?>>)


}