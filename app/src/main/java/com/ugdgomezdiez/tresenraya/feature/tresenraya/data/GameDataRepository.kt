package com.ugdgomezdiez.tresenraya.feature.tresenraya.data

import com.ugdgomezdiez.tresenraya.feature.tresenraya.data.remote.GameDbRemoteDataSource
import com.ugdgomezdiez.tresenraya.feature.tresenraya.data.xml.GameXmlLocalDataSource
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.GameRepository
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece

class GameDataRepository(
    private val gameXmlLocalDataSource: GameXmlLocalDataSource,
    private val gameDbRemoteDataSource: GameDbRemoteDataSource
): GameRepository {
    override suspend fun getGame(): Array<Array<Piece>> {
        val value = gameDbRemoteDataSource.getGame()
        return if(value == null){
            val piecesArray = Array(3) { i ->
                Array(3) { j ->
                    Piece(i, j, 0)
                }
            }
            gameDbRemoteDataSource.setPiece(piecesArray)
            piecesArray
        }else{
            value
        }
    }

    override suspend fun setPiece(piece: Piece): Boolean {
        val board = gameDbRemoteDataSource.getGame()
        var turn = getTurn()
        return if (board != null) {
            if(board[piece.valueX][piece.valueY].selectPiece == 0){
                if (turn%2 == 0){
                    piece.selectPiece = 1
                }else{
                    piece.selectPiece = 2
                }
                turn +=1
                gameXmlLocalDataSource.setTurn(turn)
                board[piece.valueX][piece.valueY] = piece
                gameDbRemoteDataSource.setPiece(board)
                true
            }else{
                false
            }
        } else {
            false
        }
    }

    override fun getTurn(): Int {
        val turn = gameXmlLocalDataSource.getTurn()
        return if (turn == null){
            gameXmlLocalDataSource.setTurn(0)
            0
        }else{
            turn
        }
    }

    override fun cleanBoard(){
        gameXmlLocalDataSource.cleanBoard()

    }
}