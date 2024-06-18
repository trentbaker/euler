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
        val startTime = System.currentTimeMillis()
        return buildString {
            appendLine("----- $name -----")
            exampleProblem().let { solution ->
                if (solution.isNotBlank()) appendLine("Example: $solution")
            }
            realProblem().let { solution ->
                if (solution.isNotBlank()) appendLine("Real: $solution")
            }
            appendLine("----- ${System.currentTimeMillis() - startTime}ms -----")
        }
    }
}

fun BigInteger(input: Int): BigInteger = BigInteger.valueOf(input.toLong())
