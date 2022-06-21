package euler.problems

import java.math.BigInteger

object Problem30 {
    private fun BigInteger.digitPowerSum(power: Int) = toString().sumOf {
        BigInteger(it.digitToInt()).pow(power)
    }

    fun BigInteger.isDigitFourthPower() = this == this.digitPowerSum(4)
    fun BigInteger.isDigitFifthPower() = this == this.digitPowerSum(5)
}