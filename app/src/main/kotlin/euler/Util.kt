package euler

import java.math.BigInteger

fun <T> timed(name: String? = null, fn: () -> T): T = System.currentTimeMillis().let { start ->
    fn().also {
        println(
            buildString {
                if (name != null) append("$name ")
                append("took ${System.currentTimeMillis() - start}ms")
            }
        )
    }
}

abstract class EulerProblem {
    open fun exampleProblem(): String = ""
    open fun realProblem(): String = ""
    open val name: String = this::class.simpleName ?: "???"
    fun solve(): String {
        val timeA = System.currentTimeMillis()
        val exampleResult = exampleProblem()
        val timeB = System.currentTimeMillis()
        val realResult = realProblem()
        val timeC = System.currentTimeMillis()

        return buildString {
            appendLine("----- $name -----\n")
            if (exampleResult.isNotBlank()) {
                appendLine(exampleResult)
                appendLine("----- example: ${timeB - timeA}ms -----\n")
            }
            if (realResult.isNotBlank()) {
                appendLine(realResult)
                appendLine("----- real: ${timeC - timeB}ms -----\n")
            }
        }
    }
}

fun BigInteger(input: Int): BigInteger = BigInteger.valueOf(input.toLong())
