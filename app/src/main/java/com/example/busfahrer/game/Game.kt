package com.example.busfahrer.game

import com.example.busfahrer.cards.CardDeck

class Game (private val players : ArrayList<Player>) {
    private val cardDeck = CardDeck()

    fun shuffleDeck() {
        cardDeck.shuffle()
    }
}