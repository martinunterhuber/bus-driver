package com.example.busfahrer.cards

data class Card (val rank: Rank, val suite: Suite) {
    var drawableId: Int = 0

    operator fun compareTo(card : Card) : Int {
        return rank - card.rank
    }

    fun isTree() : Boolean {
        return suite in listOf(Suite.ACORNS, Suite.LEAVES)
    }

    fun inside(card1 : Card, card2 : Card) : Boolean {
        return rank in card1.rank..card2.rank
    }
}