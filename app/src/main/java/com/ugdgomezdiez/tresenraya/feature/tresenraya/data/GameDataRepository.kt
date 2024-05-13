package com.ugdgomezdiez.tresenraya.feature.tresenraya.data

import com.ugdgomezdiez.tresenraya.feature.tresenraya.data.xml.GameXmlLocalDataSource
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.GameRepository
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece

class GameDataRepository(
    private val gameXmlLocalDataSource: GameXmlLocalDataSource
): GameRepository {
    override fun getGame(): Array<Array<Piece>> {
        val value = gameXmlLocalDataSource.getGame()
        return if(value == null){
            val piecesArray = Array(3) { i ->
                Array(3) { j ->
                    Piece(i, j, null)
                }
            }
            gameXmlLocalDataSource.setPiece(piecesArray)
            piecesArray
        }else{
            value
        }
    }

    override fun setPiece(piece: Piece): Boolean {
        val board = gameXmlLocalDataSource.getGame()
        var turn = getTurn()
        return if (board != null) {
            if(board[piece.valueX][piece.valueY].selectPiece == null){
                if (turn%2 == 0){
                    piece.selectPiece = true
                }else{
                    piece.selectPiece = false
                }
                turn +=1
                gameXmlLocalDataSource.setTurn(turn)
                board[piece.valueX][piece.valueY] = piece
                gameXmlLocalDataSource.setPiece(board)
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
}