package com.ugdgomezdiez.tresenraya.feature.tresenraya.data.xml

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import com.ugdgomezdiez.tresenraya.feature.tresenraya.data.GameLocalDataSource
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece

class GameXmlLocalDataSource(val context: Context, val gson: Gson)
    :GameLocalDataSource {

        val sharedPref = context.getSharedPreferences("Game", MODE_PRIVATE)
    override fun getGame(): Array<Array<Piece>>? {
        val gameJson = sharedPref.getString("game", null)

        return gson.fromJson(gameJson, Array<Array<Piece>>::class.java)

    }

    override fun setPiece(trun: Array<Array<Piece>>) {
        sharedPref.edit().putString("game", gson.toJson(trun)).apply()
    }

    override fun setTurn(turn: Int) {
        sharedPref.edit().putInt("turn", turn).apply()
    }

    override fun getTurn(): Int {
        return sharedPref.getInt("turn", 0)
    }
}