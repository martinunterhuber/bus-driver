package at.unterhuber.bus_driver.game

import at.unterhuber.bus_driver.cards.Card

class Player (val name: String) {
    val cards: ArrayList<Card> = ArrayList()

    fun getSumOfRanks(): Int {
        return cards.fold(0, {a, b -> a + b.rank.value})
    }
}