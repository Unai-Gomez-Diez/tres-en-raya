package com.ugdgomezdiez.tresenraya.feature.tresenraya.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ugdgomezdiez.tresenraya.R
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece
import com.ugdgomezdiez.tresenraya.feature.tresenraya.presentation.GameDiffUtil

class GameAdapter : ListAdapter<Piece, GameViewHolder>(GameDiffUtil()) {
    lateinit var onClick: (piece: Piece) -> Unit

    fun setEvent(onClick: (Piece) -> Unit) {
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_piece, parent, false)
        return GameViewHolder(view)
    }

    override fun getItemCount(): Int = currentList.size
    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(currentList[position], onClick)
    }
}