package com.ugdgomezdiez.tresenraya.feature.tresenraya.data.remote

import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece

interface GameRemoteDataSource {
    suspend fun getGame():  Array<Array<Piece>>?
    fun setPiece(board: Array<Array<Piece>>)
}