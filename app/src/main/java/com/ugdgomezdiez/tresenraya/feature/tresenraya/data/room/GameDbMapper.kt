package com.ugdgomezdiez.tresenraya.feature.tresenraya.data.room

import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece

fun GameEntity.toDomain(): Piece=
    Piece(
        this.id,
        this.valueX,
        this.valueY,
        this.selectPiece
    )

fun Piece.toEntity(): GameEntity =
    GameEntity(
        this.id,
        this.valueX,
        this.valueY,
        this.selectPiece
    )