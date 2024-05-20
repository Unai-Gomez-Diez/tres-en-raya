package com.ugdgomezdiez.tresenraya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ugdgomezdiez.tresenraya.feature.tresenraya.presentation.GameFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, GameFragment())
            .commit()
    }




}