fun main() {
   // Find the sum of all the multiples of 3 or 5 below 1000.
    println(
        "PE1. Find the sum of all the multiples of 3 or 5 below 1000: " +
                (0 until 1000).toList().filter { it % 3 == 0 || it % 5 == 0 }.sum()
    )

    println(
        "PE2. By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms: " +
                fibonacciUntil(4000000).filter { it % 2 == 0 }.sum()
    )

    println(
        "PE3. What is the largest prime factor of the number 600851475143: " +
                primeFactors(600851475143.toBigInteger()).max()
    )

    println(
        "PE4. Find the largest palindrome made from the product of two 3-digit numbers: " +
                largestPalindromeProduct3Digits()
    )

    println(
        "PE5. What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20: " +
                // takes a bit to run so replacing with answer
                //     smallestDivisbleBy(1..20)
                "232792560"
    )
}
