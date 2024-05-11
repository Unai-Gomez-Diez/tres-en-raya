package com.ugdgomezdiez.tresenraya.feature.tresenraya.data.xml

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import com.ugdgomezdiez.tresenraya.feature.tresenraya.data.GameLocalDataSource
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece

class GameXmlLocalDataSource(val context: Context, val gson: Gson)
    :GameLocalDataSource {

        val sharedPref = context.getSharedPreferences("Game", MODE_PRIVATE)
    override fun getGame(): Array<Array<Piece?>> {
        val gameJson = sharedPref.getString("game", null)
        return if (gameJson != null) {
            gson.fromJson(gameJson, Array<Array<Piece?>>::class.java)
        } else {
            // Si el JSON es nulo, inicializa un nuevo juego vacío
            Array(3) { Array(3) { null } }
        }
    }

    override fun setGameTurn(trun: Array<Array<Piece?>>) {
        sharedPref.edit().putString("game", gson.toJson(trun)).apply()
    }
}