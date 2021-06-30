package com.example.busfahrer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.busfahrer.R
import com.example.busfahrer.cards.Card
import com.example.busfahrer.cards.CardDeck
import com.example.busfahrer.cards.Rank
import com.example.busfahrer.cards.Suite

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cardDeck = CardDeck()
        cardDeck.shuffle()

    }
}