package com.ugdgomezdiez.tresenraya.feature.tresenraya.data.remote

import com.google.firebase.database.FirebaseDatabase
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece
import kotlinx.coroutines.tasks.await

class GameDbRemoteDataSource(
    private val firebase: FirebaseDatabase
): GameRemoteDataSource {
    override suspend fun getGame(): Array<Array<Piece>>? {
        val datos = firebase.getReference("board")
            .get()
            .await()
            .children.map {
                it.getValue(GameDbRemoteModel::class.java)!!.toDomain()
            }.toTypedArray()
        val gameBoard = arrayOf(datos)
        return gameBoard
    }

    override fun setPiece(board: Array<Array<Piece>>) {
        firebase.getReference("board")
            .setValue(board)
    }

}