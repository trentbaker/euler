package euler.problems

object Problem28 {
    class NumberSpiral(
        private val dimension: Int,
    ) {
        init {
            require(dimension % 2 == 1) { "don't know how to spiral on an even grid" }
        }

        private val possibleNumbers = (2..dimension * dimension)

        val diagonalNumbers by lazy {
            var chunkSize = 1
            var skipped = 0
            var savedAtSize = 0

            val output = mutableListOf<Int>()

            possibleNumbers.forEach {
                val skipping = skipped < chunkSize
                if (skipping) {
                    skipped++
                } else {
                    output += it
                    savedAtSize++
                    skipped = 0
                    if (savedAtSize >= 4) {
                        savedAtSize = 0
                        chunkSize += 2
                    }
                }

            }

            listOf(1) + output
        }
    }
}