package com.ugdgomezdiez.tresenraya.feature.tresenraya.data.remote

import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece

fun GameDbRemoteModel.toDomain(): Piece = Piece(
    this.valueX, this.valueY, this.selectPiece
)

fun Piece.toRemoteModel(): GameDbRemoteModel = GameDbRemoteModel(
    this.valueX, this.valueY, this.selectPiece
)