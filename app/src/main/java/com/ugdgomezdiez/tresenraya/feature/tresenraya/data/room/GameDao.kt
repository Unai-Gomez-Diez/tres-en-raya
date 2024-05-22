package com.ugdgomezdiez.tresenraya.feature.tresenraya.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece

@Dao
interface GameDao {
    @Query("SELECT * FROM board")
    fun getGame(): List<GameEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setGame(board: List<GameEntity>)

    @Query("DELETE FROM board")
    fun deleteGame()
}