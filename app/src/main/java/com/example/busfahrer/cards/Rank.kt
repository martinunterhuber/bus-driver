package com.example.busfahrer.cards

enum class Rank(private val value: Int) {
    ACE (14), KING(13), QUEEN(12), JACK(11), TEN(10), NINE(9), EIGHT(8), SEVEN(7), SIX(6);

    operator fun minus(rank: Rank): Int {
        return value - rank.value
    }
}