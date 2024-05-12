package com.ugdgomezdiez.tresenraya.feature.tresenraya.presentation

import androidx.recyclerview.widget.DiffUtil
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece

class GameDiffUtil: DiffUtil.ItemCallback<Piece>() {
    override fun areItemsTheSame(oldItem: Piece, newItem: Piece): Boolean {
        return oldItem.valueX == newItem.valueX && oldItem.valueY == newItem.valueY
    }

    override fun areContentsTheSame(oldItem: Piece, newItem: Piece): Boolean {
        return oldItem == newItem
    }
}