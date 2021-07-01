package at.unterhuber.bus_driver.game

import at.unterhuber.bus_driver.cards.CardDeck
import at.unterhuber.bus_driver.choices.Choice

class Game {
    private lateinit var players: List<Player>
    private val cardDeck = CardDeck()
    private var currentPlayerIndex = 0
    var currentRound = 0

    companion object {
        val instance = Game()
    }

    fun init(players: List<Player>) {
        this.players = players
    }

    fun shuffleDeck() {
        cardDeck.shuffle()
    }

    fun getCurrentPlayer(): Player {
        return players[currentPlayerIndex]
    }

    fun updateCurrentPlayer() {
        currentPlayerIndex++
        if (currentPlayerIndex == players.size) {
            currentPlayerIndex %= players.size
            currentRound++
        }
    }

    fun isCorrectChoice(choice: Choice): Boolean {
        val nextCard = cardDeck.getNextCard()
        val isCorrect = when(choice) {
            Choice.TREE -> nextCard.isTree()
            Choice.NOT_TREE -> !nextCard.isTree()
            Choice.ABOVE -> nextCard > getCurrentPlayer().cards[0]
            Choice.BELOW -> nextCard < getCurrentPlayer().cards[0]
            Choice.IN_BETWEEN -> nextCard.inside(getCurrentPlayer().cards[0], getCurrentPlayer().cards[1])
            Choice.OUTSIDE -> nextCard.outside(getCurrentPlayer().cards[0], getCurrentPlayer().cards[1])
            Choice.HAVE -> !nextCard.hasDifferentSuiteThan(*getCurrentPlayer().cards.toTypedArray())
            Choice.NOT_HAVE -> nextCard.hasDifferentSuiteThan(*getCurrentPlayer().cards.toTypedArray())
        }
        getCurrentPlayer().cards.add(nextCard)
        return isCorrect
    }

    fun isLastRound(): Boolean {
        return currentRound == 4
    }
}