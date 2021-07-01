package com.example.busfahrer.game

import com.example.busfahrer.cards.CardDeck

class Game(private val players: List<Player>) {
    private val cardDeck = CardDeck()
    private val currentPlayerIndex = 0

    fun shuffleDeck() {
        cardDeck.shuffle()
    }

    fun getCurrentPlayer(): Player {
        return players[currentPlayerIndex]
    }
}