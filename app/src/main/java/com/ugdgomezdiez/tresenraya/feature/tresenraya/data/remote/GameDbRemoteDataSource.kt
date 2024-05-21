package com.ugdgomezdiez.tresenraya.feature.tresenraya.data.remote

import com.google.firebase.database.FirebaseDatabase
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece
import kotlinx.coroutines.tasks.await

class GameDbRemoteDataSource(
    private val firebase: FirebaseDatabase
): GameRemoteDataSource {
    override suspend fun getGame(): Array<Array<Piece>>? {
        return try {
            val gameBoard = firebase.getReference("board")
                .get().await()
                .children.map { dataSnapshot ->
                    dataSnapshot.children.mapNotNull { pieceDataSnapshot ->
                        pieceDataSnapshot.getValue(GameDbRemoteModel::class.java)?.toDomain()
                    }.toTypedArray()
                }.toTypedArray()

            gameBoard
        } catch (e: Exception) {
            // Maneja cualquier excepción aquí
            null
        }
    }

    override fun setPiece(board: Array<Array<Piece>>) {
        firebase.getReference("board")
            .setValue(board)
    }

}