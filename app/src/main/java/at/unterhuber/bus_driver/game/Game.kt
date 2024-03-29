package at.unterhuber.bus_driver.game

import at.unterhuber.bus_driver.cards.Card
import at.unterhuber.bus_driver.cards.CardDeck
import at.unterhuber.bus_driver.cards.Rank
import at.unterhuber.bus_driver.cards.Suite
import at.unterhuber.bus_driver.choices.Choice

class Game {
    lateinit var players: List<Player>
    private var cardDeck = CardDeck()
    private var currentPlayerIndex = 0
    var currentRound = 0
    var busDriverCardIndex = 0
    var busDriverCards = ArrayList<Card>()
    var rounds: Int = 4
    var pyramidHeight: Int = 4

    companion object {
        val instance = Game()
    }

    fun init(players: List<Player>, rounds: Int, pyramidHeight: Int) {
        this.players = players
        this.rounds = rounds
        this.pyramidHeight = pyramidHeight
        cardDeck = CardDeck()
        currentPlayerIndex = 0
        currentRound = 0
        busDriverCardIndex = 0
        busDriverCards = ArrayList()
    }

    fun shuffleDeck() {
        cardDeck.shuffle()
    }

    fun getCurrentPlayer(): Player {
        return players[currentPlayerIndex]
    }

    fun setCurrentPlayer(player: Player) {
        currentPlayerIndex = players.indexOf(player)
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
            Choice.EQUALS -> nextCard.isEqualToAtLeastOne(*getCurrentPlayer().cards.toTypedArray())
        }
        getCurrentPlayer().cards.add(nextCard)
        return isCorrect
    }

    fun isLastRound(): Boolean {
        return currentRound == rounds
    }

    fun drawCard(): Card {
        return cardDeck.getNextCard()
    }

    fun removePlayersSameRankCards(rank: Rank) {
        for (player in players) {
            player.cards.removeIf { it.rank == rank }
        }
    }

    fun getPlayersSameRankCardCount(rank: Rank): ArrayList<Result> {
        val results = ArrayList<Result>()
        for (player in players) {
            results.add(Result(player.name, player.cards.count { it.rank == rank }))
        }
        return results
    }

    private fun shuffleNewDeck() {
        cardDeck = CardDeck()
        shuffleDeck()
    }

    fun getLosingPlayer(): Player {
        var losingPlayer = players[0]
        for (player in players) {
            if (player.cards.size > losingPlayer.cards.size) {
                losingPlayer = player
            } else if (
                player.cards.size == losingPlayer.cards.size &&
                player.getSumOfRanks() < losingPlayer.getSumOfRanks()) {
                losingPlayer = player
            }
        }
        return losingPlayer
    }

    fun initBusDriver() {
        shuffleNewDeck()
        setCurrentPlayer(getLosingPlayer())
        for (i in 1..5) {
            busDriverCards.add(cardDeck.getNextCard())
        }
        busDriverCardIndex = 0
    }

    fun busDriverChoice(choice: Choice): Boolean {
        if (cardDeck.isEmpty()) {
            shuffleNewDeck()
            cardDeck.remove(busDriverCards)
        }

        val nextCard = cardDeck.getNextCard()
        val isCorrect = when(choice) {
            Choice.ABOVE -> nextCard > busDriverCards[busDriverCardIndex]
            Choice.BELOW -> nextCard < busDriverCards[busDriverCardIndex]
            Choice.EQUALS -> nextCard.rank == busDriverCards[busDriverCardIndex].rank
            else -> false
        }

        busDriverCards[busDriverCardIndex] = nextCard

        if (isCorrect) {
            busDriverCardIndex++
        } else {
            busDriverCardIndex = 0
        }
        return isCorrect
    }

    fun busDriverHasFinished(): Boolean {
        return busDriverCardIndex == 5
    }

    fun busDriverIsAtLastCard(): Boolean {
        return busDriverCardIndex == 4
    }
}