package com.ugdgomezdiez.tresenraya.feature.tresenraya.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ugdgomezdiez.tresenraya.R
import com.ugdgomezdiez.tresenraya.databinding.ItemPieceBinding
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece

class GameViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    private lateinit var binding: ItemPieceBinding

    fun bind(model: Piece, onClick: (Piece) -> Unit) {
        binding = ItemPieceBinding.bind(view)
        binding.apply {
            if (model.selectPiece == 1) {
                piece.setImageResource(R.drawable.ic_x)

            } else if(model.selectPiece == 2) {
                piece.setImageResource(R.drawable.ic_circle)
            } else if(model.selectPiece == 0){
                piece.setImageResource(R.drawable.ic_check)
            }
            view.setOnClickListener {
                onClick.invoke(model)
            }
        }
    }
}