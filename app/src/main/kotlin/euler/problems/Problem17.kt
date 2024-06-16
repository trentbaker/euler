package euler.problems

import euler.EulerProblem

object Problem17 : EulerProblem() {
    override val name = "Number Letter Counts"

    private val singleDigitWords = mapOf(
        1 to "ONE",
        2 to "TWO",
        3 to "THREE",
        4 to "FOUR",
        5 to "FIVE",
        6 to "SIX",
        7 to "SEVEN",
        8 to "EIGHT",
        9 to "NINE"
    )

    private val teenWords = mapOf(
        10 to "TEN",
        11 to "ELEVEN",
        12 to "TWELVE",
        13 to "THIRTEEN",
        14 to "FOURTEEN",
        15 to "FIFTEEN",
        16 to "SIXTEEN",
        17 to "SEVENTEEN",
        18 to "EIGHTEEN",
        19 to "NINETEEN"
    )

    private val tenMultipleWords = mapOf(
        2 to "TWENTY",
        3 to "THIRTY",
        4 to "FORTY",
        5 to "FIFTY",
        6 to "SIXTY",
        7 to "SEVENTY",
        8 to "EIGHTY",
        9 to "NINETY"
    )

    private fun numberToWords(input: Int): String = teenWords[input]
        ?: input.toString().map { it.code - 48 }.let { digits ->
            when (digits.size) {
                1 -> singleDigitWords[digits[0]].toString()
                2 -> tenMultipleWords[digits[0]] + singleDigitWords.getOrElse(digits[1]) { "" }
                3 -> {
                    singleDigitWords[digits[0]] +
                            "HUNDRED" +
                            (if (tenMultipleWords[digits[1]] != null || singleDigitWords[digits[2]] != null || teenWords["${digits[1]}${digits[2]}".toInt()] != null) "AND" else "") +
                            teenWords.getOrElse("${digits[1]}${digits[2]}".toInt()) {
                                tenMultipleWords.getOrElse(digits[1]) { "" } +
                                        singleDigitWords.getOrElse(digits[2]) { "" }
                            }
                }

                4 -> {
                    singleDigitWords[digits[0]] +
                            "THOUSAND" +
                            singleDigitWords.getOrElse(digits[1]) { "" } +
                            (if (singleDigitWords[digits[1]] != null) "HUNDRED" else "") +
                            (if (tenMultipleWords[digits[2]] != null || singleDigitWords[digits[3]] != null || teenWords["${digits[2]}${digits[3]}".toInt()] != null) "AND" else "") +
                            teenWords.getOrElse("${digits[2]}${digits[3]}".toInt()) {
                                tenMultipleWords.getOrElse(digits[2]) { "" } +
                                        singleDigitWords.getOrElse(digits[3]) { "" }
                            }
                }

                else -> "oof"
            }
        }


    override fun exampleProblem(): String = buildString {
        append("If all the numbers from 1 to 5 inclusive were written out in words, how many letters would be used: ")
        val words = (1 ..5).map { numberToWords(it) }
        append(words.sumOf { it.length })
    }

    override fun realProblem(): String = buildString {
        append("If all the numbers from 1 to 1000 inclusive were written out in words, how many letters would be used: ")
        val words = (1 ..1000).map { numberToWords(it) }
        append(words.sumOf { it.length })
    }
}

fun main() {
    println(Problem17.solve())
}