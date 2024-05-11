package com.ugdgomezdiez.tresenraya.feature.tresenraya.data

import android.util.Log
import com.ugdgomezdiez.tresenraya.feature.tresenraya.data.xml.GameXmlLocalDataSource
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.GameRepository
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece

class GameDataRepository(
    private val gameXmlLocalDataSource: GameXmlLocalDataSource
): GameRepository {
    override fun getGame(): Array<Array<Piece?>> {
        val value = gameXmlLocalDataSource.getGame()
        return if(value == null){
            gameXmlLocalDataSource.setGameTurn(arrayOf(
                arrayOf(null, null, null),
                arrayOf(null, null, null),
                arrayOf(null, null, null)
            ))
            value
        }else{
            value
        }
    }

    override fun setGameTurn(turn: Piece): Boolean {
        val board = gameXmlLocalDataSource.getGame()
        return if(board[turn.valueY][turn.valueX] == null){
            board[turn.valueY][turn.valueX] = turn
            gameXmlLocalDataSource.setGameTurn(board)
            Log.d("@dev", "true")
            true
        }else{
            Log.d("@dev", "false")
            false

        }
    }
}