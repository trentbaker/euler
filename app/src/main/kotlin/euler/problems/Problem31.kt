package euler.problems

import euler.EulerProblem

object Problem31 : EulerProblem() {
    override val name = "Coin Sums"

    private val simpleDenominations = listOf(1, 2)
    private val ukDenominations = listOf(1, 2, 5, 10, 20, 50, 100, 200)

    private fun coinSums(
        total: Int,
        coinSizes: List<Int>,
    ): Int {
        val ways = MutableList(total + 1) { if (it == 0) 1 else 0 }
        coinSizes.sorted().forEach { coinSize ->
            (coinSize..total).forEach { j ->
                ways[j] += ways[j - coinSize];
            }
        }
        return ways.last() // last is possible ways using all coin sizes
    }

    override fun exampleProblem(): String = buildString {
        append("Find how many different ways to make 5p using 1p and 2p coins: ")
        val ways = coinSums(5, simpleDenominations)
        append(ways)
    }

    override fun realProblem(): String = buildString {
        append("Find how many different ways to make £2 using any number of coins: ")
        val ways = coinSums(200, ukDenominations)
        append(ways)
    }
}

fun main() {
    println(Problem31.solve())
}