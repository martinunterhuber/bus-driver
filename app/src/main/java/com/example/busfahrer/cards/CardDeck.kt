package com.example.busfahrer.cards

class CardDeck {
    private var cards : ArrayList<Card> = ArrayList()

    init {
        for (rank in Rank.values()) {
            for (suite in Suite.values()) {
                cards.add(Card(rank, suite))
            }
        }
    }

    fun shuffle() {
        cards.shuffle()
    }

    fun getNextCard() : Card {
        return cards.removeLast()
    }
}