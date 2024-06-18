package euler.problems

import euler.EulerProblem
import java.io.File

object Problem54 : EulerProblem() {
    override val name = "Poker Hands"

    class Hand(input: List<String>) : Comparable<Hand> {
        private val cards = input.map { Card(it) }
        init {
            require(cards.size == 5)
        }

        private val ranks = cards.map { it.rank }
        private val suits = cards.map { it.suit }
        private val rankCounts: List<Pair<Card, Int>> =
            cards.distinctBy { it.rank }.map { card -> card to ranks.count { it == card.rank } }.sortedBy { it.second }
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
            10, 6, 1 -> cards.maxOrNull()?.rankInt
            9, 5 -> if (ranks.contains('A')) 5 else cards.maxOrNull()?.rankInt // special case for ace low straight
            4, 7 -> rankCounts.find { it.second == 3 }?.first?.rankInt // this will be fullHouseMajor
            8 -> rankCounts.find { it.second == 4 }?.first?.rankInt
            3 -> rankCounts.filter { it.second == 2 }
                .maxByOrNull { it.first }?.first?.rankInt // this will be twoPairMajor
            2 -> rankCounts.find { it.second == 2 }?.first?.rankInt
            else -> null
        } ?: 0

        private val fullHouseMinor = (rankCounts.find { it.second == 2 }?.first?.rankInt).takeIf { score == 7 } ?: 0
        private val twoPairMinor = rankCounts.filter { it.second == 2 }.minByOrNull { it.first }?.first?.rankInt ?: 0

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
            else -> rank.code - 48
        }

        override fun toString() = code
        override fun compareTo(other: Card): Int = rankInt - other.rankInt
    }

    // sequence of pairs where first is player 1 and second is player 2
    private val POKER_HANDS = File("app/src/main/resources/0054_poker.txt")
        .readLines()
        .asSequence()
        .map {
            val cards = it.split(' ')
            val player1 = cards.slice(0 until 5)
            val player2 = cards.slice(5 until 10)
            Hand(player1) to Hand(player2)
        }

    private val testHands = listOf(
        "AH JH KH QH TH", // royal flush
        "3S 4S 5S 6S 7S", // straight flush
        "4D 4S 4C 4H 7S", // four of a kind
        "4D 4S 4C 7H 7S", // full house
        "4S 4S 8S 7S 7S", // flush
        "8S 7C 9S TD JH", // straight
        "3S 3C 3S 6D 7H", // three of a kind
        "3S 3C 6S 6D 7H", // two pair
        "3S 3C 8S 6D 7H", // pair
        "3S 2C 6S 9D 7H", // high card
        "AS 2H 3H 4D 5C", // ace low straight
        "AS 2S 3S 4S 5S" // ace low straight flush
    ).map { Hand(it.split(' ')) }

    override fun exampleProblem(): String = buildString {
        appendLine("Score each of the test hands to make sure things are working: ")
        testHands.onEach { appendLine(it) }
    }

    override fun realProblem(): String = buildString {
        append("Find the number of poker hands where player 1 wins: ")
        val result = POKER_HANDS.count { (a, b) -> a > b }
        append(result)
    }
}

fun main() {
    println(Problem54.solve())
}