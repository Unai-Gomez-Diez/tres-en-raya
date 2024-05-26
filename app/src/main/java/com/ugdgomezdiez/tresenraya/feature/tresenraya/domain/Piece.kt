package com.ugdgomezdiez.tresenraya.feature.tresenraya.domain

data class Piece(
    val valueX: Int,
    val valueY: Int,
    var selectPiece: Int
) {
    // Constructor vacío requerido por Firebase
    constructor() : this(0, 0, 0)
}