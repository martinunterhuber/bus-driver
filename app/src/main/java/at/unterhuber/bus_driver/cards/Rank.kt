package at.unterhuber.bus_driver.cards

enum class Rank(private val value: Int) {
    ACE (14), KING(13), OBER(12), UNTER(11), TEN(10), NINE(9), EIGHT(8), SEVEN(7), SIX(6);

    operator fun minus(rank: Rank): Int {
        return value - rank.value
    }
}