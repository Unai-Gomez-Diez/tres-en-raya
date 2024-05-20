package com.ugdgomezdiez.tresenraya.feature.tresenraya.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "board")
data class GameEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "valueX") var valueX: Int,
    @ColumnInfo(name = "valueY") var valueY: Int,
    @ColumnInfo(name = "selectPiece") var selectPiece: Int
)