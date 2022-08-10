package euler.problems

object Problem31 {
    /*
     * In the United Kingdom the currency is made up of pound (£) and pence (p). There are eight coins in general circulation:
     *
     * 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p), and £2 (200p).
     * It is possible to make £2 in the following way:
     *
     * 1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
     * How many different ways can £2 be made using any number of coins?
     */
    fun coinSums(
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
}