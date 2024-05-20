package com.ugdgomezdiez.tresenraya.feature.tresenraya.data.room

import com.ugdgomezdiez.tresenraya.feature.tresenraya.data.GameLocalDataSource
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Turn

class GameDbLocalDataSource(private val gameDao: GameDao,
    private val turnDao: TurnDao)
    :GameLocalDataSource{
    override fun getGame(): Array<Array<Piece>>? {
        val gameEntities = gameDao.getGame()
        return if (gameEntities.isNullOrEmpty()) {
            emptyArray()
        } else {
            val rows = 3 // Ajusta el número de filas según tu tablero
            val columns = 3 // Ajusta el número de columnas según tu tablero
            val gameBoard = Array(rows) { arrayOfNulls<Piece>(columns) }
            for (entity in gameEntities) {
                gameBoard[entity.valueY][entity.valueX] = entity.toDomain()
            }
            gameBoard as Array<Array<Piece>>
        }
    }

    override fun setPiece(board: Array<Array<Piece>>) {
        val gameEntities = board.flatMapIndexed { rowIndex, row ->
            row.mapIndexed { columnIndex, piece ->
                piece.toEntity().apply {
                    this.valueX = columnIndex
                    this.valueY = rowIndex
                }
            }
        }
        gameDao.setGame(gameEntities)
    }

    override fun setTurn(turn: Turn) {
        turnDao.setTurn(turn.toEntity())
    }

    override fun getTurn(): Turn? {
        return turnDao.getTurn()?.toDomain()
    }

    override fun cleanBoard() {
        gameDao.deleteGame()
    }
}