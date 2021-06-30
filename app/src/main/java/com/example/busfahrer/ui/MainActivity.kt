package com.example.busfahrer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.busfahrer.R
import com.example.busfahrer.cards.CardDeck

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cardDeck = CardDeck()
        cardDeck.shuffle()
    }
}