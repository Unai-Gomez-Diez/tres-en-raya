package com.ugdgomezdiez.tresenraya.feature.tresenraya.data

import com.ugdgomezdiez.tresenraya.feature.tresenraya.data.room.GameDbLocalDataSource
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.GameRepository
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Turn

class GameDataRepository(
    private val gameDbLocalDataSource: GameDbLocalDataSource
): GameRepository {
    override fun getGame(): Array<Array<Piece>> {

        val value = gameDbLocalDataSource.getGame()
        var cont = 0
        return if(value.isNullOrEmpty()){
            val piecesArray = Array(3) { i ->
                Array(3) { j ->
                    cont += 1
                    Piece(cont,i, j, 0)

                }
            }
            gameDbLocalDataSource.setPiece(piecesArray)
            piecesArray
        }else{
            value
        }
    }

    override fun setPiece(piece: Piece): Boolean {
        val board = gameDbLocalDataSource.getGame()
        var turn = getTurn()

        return if (board != null) {
            if(board[piece.valueY][piece.valueX].selectPiece == 0){
                if (turn.turn%2 == 0){
                    piece.selectPiece = 1
                }else{
                    piece.selectPiece = 2
                }
                turn.turn +=1
                gameDbLocalDataSource.setTurn(turn)
                board[piece.valueY][piece.valueX] = piece
                gameDbLocalDataSource.setPiece(board)
                true
            }else{
                false
            }
        } else {
            false
        }
    }

    override fun getTurn(): Turn {
        val turn = gameDbLocalDataSource.getTurn()
        return if (turn == null){
            gameDbLocalDataSource.setTurn(Turn(1,0))
            Turn(1,0)
        }else{
            turn
        }
    }

    override fun cleanBoard(){
        gameDbLocalDataSource.cleanBoard()

    }
}