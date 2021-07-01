package com.example.busfahrer.game

import com.example.busfahrer.cards.Card

interface Guess {
    fun evaluate(cards: List<Card>, card: Card): Boolean
}