import java.math.BigInteger

fun main() {
    println("PE34. Find the sum of all numbers which are equal to the sum of the factorial of their digits: " +
            digitFactorialLoopFlags.filter { it.second.isEmpty() && it.first > 3.toBigInteger() }.fold(0) { acc, pair -> acc + pair.first.toInt() }
    )
}