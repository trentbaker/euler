package objects

class Card(private val code: String) : Comparable<Card> {
    private val ranks = listOf('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A')
    private val suits = listOf('S', 'H', 'C', 'D')

    init {
        require(code.length == 2)
        require(ranks.contains(code.first()))
        require(suits.contains(code.last()))
    }

    val rank = ranks.first { it == code.first() }
    val suit = suits.first { it == code.last() }
    val rankInt = when (rank) {
        'T' -> 10
        'J' -> 11
        'Q' -> 12
        'K' -> 13
        'A' -> 14
        else -> rank.toInt() - 48
    }

    override fun toString() = code
    override fun compareTo(other: Card): Int = rankInt - other.rankInt
}