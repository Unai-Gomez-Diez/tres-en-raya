package com.ugdgomezdiez.tresenraya.feature.tresenraya.data.remote

import com.google.firebase.database.FirebaseDatabase
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece
import kotlinx.coroutines.tasks.await

class GameDbRemoteDataSource(
    private val firebase: FirebaseDatabase
): GameRemoteDataSource {
    override suspend fun getGame(): Array<Array<Piece>>? {
        val snapshot = firebase.getReference("board").get().await()
        val piecesList = snapshot.children.map { pieceSnapshot ->
            pieceSnapshot.getValue(Piece::class.java)!!
        }
        return if (snapshot.value == null){
            null
        }else{

            val gameBoard = Array(3) { Array(3) { Piece(0, 0, 0) } }
            piecesList.forEach { piece ->
                gameBoard[piece.valueY][piece.valueX] = piece
            }
            gameBoard
        }



    }

    override fun setPiece(board: Array<Array<Piece>>) {
        board.map {
            it.map {
                it.toRemoteModel()
            }
        }
        firebase.getReference("board")
            .setValue(board.flatten())
    }

    override fun clearGame() {
        firebase.getReference("board").removeValue()
    }


}