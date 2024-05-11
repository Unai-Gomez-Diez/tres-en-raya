package com.ugdgomezdiez.tresenraya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.gson.Gson
import com.ugdgomezdiez.tresenraya.feature.tresenraya.data.GameDataRepository
import com.ugdgomezdiez.tresenraya.feature.tresenraya.data.xml.GameXmlLocalDataSource
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAplication()
    }

    private fun initAplication(){
        val gameRepository = GameDataRepository(
            GameXmlLocalDataSource(this, Gson())
        )

        val piece = Piece(
            0,0,true
        )


        val vista1 = findViewById<ImageView>(R.id.initinit)
        vista1.setOnClickListener {
            piece.valueX = 1
            piece.valueY = 0
            gameRepository.setGameTurn(piece)
        }


    }


}