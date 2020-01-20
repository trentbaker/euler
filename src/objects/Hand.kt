package objects

class Hand(val cards: List<Card>) : Comparable<Hand> {
    init {
        require(cards.size == 5)
    }

    constructor(input: String) : this(input.split(' ').map { Card(it) })

    private val ranks = cards.map { it.rank }
    private val suits = cards.map { it.suit }
    private val rankCounts: List<Pair<Card, Int>> = cards.distinctBy { it.rank }.map { card -> card to ranks.count { it == card.rank } }.sortedBy { it.second }
    // NOTE the suit of the cards in rankCounts did appear in the hand, but should not be used
    // the retained suit of repeated ranks is arbitrary and unchecked

    private val isRoyal = ranks.sorted() == listOf('A', 'J', 'K', 'Q', 'T')
    private val isFlush = suits.distinct().size == 1
    private val isStraight = cards.sorted().mapIndexedNotNull { index, card ->
        cards.sorted().getOrNull(index + 1)?.rankInt?.equals(card.rankInt + 1)
    }.all { it } || cards.sorted().map { it.rank } == listOf('2', '3', '4', '5', 'A')
    private val fourOfKind = rankCounts.find { it.second == 4 } != null
    private val fullHouse = rankCounts.map { it.second } == listOf(2, 3)
    private val threeOfAKind = rankCounts.map { it.second }.contains(3)
    private val twoPair = rankCounts.map { it.second }.count { it >= 2 } == 2
    private val pair = rankCounts.map { it.second }.contains(2)

    private val score = when {
        isRoyal && isFlush -> 10
        isStraight && isFlush -> 9
        fourOfKind -> 8
        fullHouse -> 7
        isFlush -> 6
        isStraight -> 5
        threeOfAKind -> 4
        twoPair -> 3
        pair -> 2
        else -> 1 // high card goes here
    }

    private val highestRelevantCard = when (score) {
        10, 6, 1 -> cards.max()?.rankInt
        9, 5 -> if (ranks.contains('A')) 5 else cards.max()?.rankInt // special case for ace low straight
        4, 7 -> rankCounts.find { it.second == 3 }?.first?.rankInt // this will be fullHouseMajor
        8 -> rankCounts.find { it.second == 4 }?.first?.rankInt
        3 -> rankCounts.filter { it.second == 2 }.maxBy { it.first }?.first?.rankInt // this will be twoPairMajor
        2 -> rankCounts.find { it.second == 2 }?.first?.rankInt
        else -> null
    } ?: 0

    private val fullHouseMinor = (rankCounts.find { it.second == 2 }?.first?.rankInt).takeIf { score == 7 } ?: 0
    private val twoPairMinor = rankCounts.filter { it.second == 2 }.minBy { it.first }?.first?.rankInt ?: 0

    private fun tiebreaker(other: Hand) = cards.indices.let {
        val thisCards = cards.sortedDescending()
        val otherCards = other.cards.sortedDescending()
        it.map { thisCards[it].rankInt - otherCards[it].rankInt }
    }.firstOrNull { it != 0 }

    override fun compareTo(other: Hand): Int = (score - other.score).takeUnless { it == 0 }
            ?: (highestRelevantCard - other.highestRelevantCard).takeUnless { it == 0 }
            ?: (fullHouseMinor - other.fullHouseMinor).takeIf { score == 7 && other.score == 7 }?.takeUnless { it == 0 }
            ?: (twoPairMinor - other.twoPairMinor).takeIf { score == 3 && other.score == 3 }?.takeUnless { it == 0 }
            ?: tiebreaker(other)?.takeUnless { it == 0 }
            ?: throw Exception("need more detailed comparison")

    override fun toString() =
            when (score) {
                10 -> "royal flush in ${suits.first()}"
                9 -> "straight flush in ${suits.first()} $highestRelevantCard high"
                8 -> "four of a kind ${highestRelevantCard}s"
                7 -> "$highestRelevantCard over $fullHouseMinor full house"
                6 -> "flush in ${suits.first()}"
                5 -> "straight $highestRelevantCard high"
                4 -> "three of a kind ${highestRelevantCard}s"
                3 -> "two pair high $highestRelevantCard"
                2 -> "pair of ${highestRelevantCard}s"
                1 -> "$highestRelevantCard high"
                else -> "wack shit"
            }.padEnd(28) + cards
}