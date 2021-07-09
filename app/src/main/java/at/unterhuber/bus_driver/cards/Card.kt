package at.unterhuber.bus_driver.cards

data class Card (val rank: Rank, val suite: Suite) {

    operator fun compareTo(card : Card) : Int {
        return rank - card.rank
    }

    fun isTree() : Boolean {
        return suite in listOf(Suite.ACORNS, Suite.LEAVES)
    }

    fun inside(card1 : Card, card2 : Card) : Boolean {
        return (card1 < this && this < card2) || (card2 < this && this < card1)
    }

    fun outside(card1 : Card, card2 : Card) : Boolean {
        return (this < card1 && this < card2) || (this > card1 && this > card2)
    }

    fun hasDifferentSuiteThan(vararg cards: Card) : Boolean {
        return cards.all { this.suite != it.suite }
    }

    fun getDrawableName() : String {
        return "${suite.name.lowercase()}_${rank.name.lowercase()}"
    }
}