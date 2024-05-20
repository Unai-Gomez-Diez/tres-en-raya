package com.ugdgomezdiez.tresenraya.feature.tresenraya.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "turn")
data class TurnEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "turn") val turn: Int
)