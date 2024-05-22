package com.ugdgomezdiez.tresenraya.feature.tresenraya.data.room

import com.ugdgomezdiez.tresenraya.feature.tresenraya.data.GameLocalDataSource
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Turn

class GameDbLocalDataSource(
    private val gameDao: GameDao,
    private val turnDao: TurnDao
) : GameLocalDataSource {
    override fun getGame(): Array<Array<Piece>> {
        val gameEntities = gameDao.getGame()
        return if (gameEntities.isNullOrEmpty()) {
            emptyArray()
        }else{
            Array(3) { row ->
                Array(3) { col ->
                    gameEntities.find { it.valueY == row && it.valueX == col }!!.toDomain()
                }
            }
        }

    }

    override fun setPiece(board: Array<Array<Piece>>) {
        val gameEntities = board.flatMapIndexed { rowIndex, row ->
            row.mapIndexed { columnIndex, piece ->
                piece.toEntity().apply {
                    valueX = columnIndex
                    valueY = rowIndex
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