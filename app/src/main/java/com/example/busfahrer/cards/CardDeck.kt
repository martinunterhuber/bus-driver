package com.example.busfahrer.cards

class CardDeck {
    var cards : ArrayList<Card> = ArrayList()

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
}