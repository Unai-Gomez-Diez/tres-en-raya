package com.ugdgomezdiez.tresenraya.feature.tresenraya.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ugdgomezdiez.tresenraya.R
import com.ugdgomezdiez.tresenraya.databinding.ItemPieceBinding
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece

class GameViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private var binding: ItemPieceBinding = ItemPieceBinding.bind(view)

    fun bind(model: Piece?) {
        model?.let {
            binding.apply {
                if (it.turn == true) {
                    piece.setImageResource(R.drawable.ic_x)
                } else {
                    piece.setImageResource(R.drawable.ic_circle)
                }
            }
        }
    }
}