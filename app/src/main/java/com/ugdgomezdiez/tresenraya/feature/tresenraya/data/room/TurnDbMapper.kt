package com.ugdgomezdiez.tresenraya.feature.tresenraya.data.room

import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Turn

fun TurnEntity.toDomain(): Turn = Turn(
    this.id,
    this.turn
)
fun Turn.toEntity(): TurnEntity = TurnEntity(
    this.id,
    this.turn
)