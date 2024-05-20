package com.ugdgomezdiez.tresenraya.feature.tresenraya.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TurnDao {
    @Query("SELECT * FROM turn LIMIT 1")
    fun getTurn(): TurnEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setTurn(turn: TurnEntity)

    @Query("DELETE FROM turn")
    fun deleteTurn()
}