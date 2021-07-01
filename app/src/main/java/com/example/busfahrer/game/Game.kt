package com.example.busfahrer.game

import com.example.busfahrer.cards.CardDeck
import com.example.busfahrer.choices.Choice

class Game(private val players: List<Player>) {
    private val cardDeck = CardDeck()
    private var currentPlayerIndex = 0
    var currentRound = 0

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
            Choice.OUTSIDE -> !nextCard.inside(getCurrentPlayer().cards[0], getCurrentPlayer().cards[1])
            Choice.HAVE -> !nextCard.hasDifferentSuiteThan(*getCurrentPlayer().cards.toTypedArray())
            Choice.NOT_HAVE -> nextCard.hasDifferentSuiteThan(*getCurrentPlayer().cards.toTypedArray())
        }
        getCurrentPlayer().cards.add(nextCard)
        return isCorrect
    }
}