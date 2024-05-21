package com.ugdgomezdiez.tresenraya.feature.tresenraya.data.remote

import com.google.firebase.database.PropertyName

data class GameDbRemoteModel(
    @get:PropertyName("valueX") @set:PropertyName("valueX") var valueX: Int = 0,
    @get:PropertyName("valueY") @set:PropertyName("valueY") var valueY: Int = 0,
    @get:PropertyName("selectPiece") @set:PropertyName("selectPiece") var selectPiece: Int = 0,

)