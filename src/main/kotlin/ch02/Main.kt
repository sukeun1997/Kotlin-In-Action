package ch02

import ch02.Expr.Num
import ch02.Expr.Sum
import java.util.*

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
//        println(eval(Sum(Num(1), Num(2))))
//        println(evalWithLogging(Sum(Num(3), Num(4))))

//        for (i in 1..100) {
//            print(fizzBuzz(i))
//        }

//        for (i in 100 downTo 1 step 2) {
//            println(fizzBuzz(i))
//        }

        val binaryReps = TreeMap<Char, String>()

        for (c in 'A'..'F') {
            val binary = Integer.toBinaryString(c.code)
            binaryReps[c] = binary
        }

        for ((letter, binary) in binaryReps) {
            println("$letter = $binary")

        }

        println("Kotlin" in setOf("Java", "Scala"))

    }

    private fun fizzBuzz(i: Int) =
        when {
            i % 15 == 0 -> "FizzBuzz"
            i % 3 == 0 -> "Fizz"
            i % 5 == 0 -> "Buzz"
            else -> "$i"
        }

    private fun eval(e: Expr): Int =
        when (e) {
            is Num -> e.value
            is Sum -> eval(e.left) + eval(e.right)
            else -> throw IllegalArgumentException("Unknown Exception")

        }

    private fun evalWithLogging(e: Expr): Int =
        when (e) {
            is Num -> {
                println("num : ${e.value}")
                e.value
            }

            is Sum -> {
                val left = evalWithLogging(e.left)
                val right = evalWithLogging(e.right)
                println("sum : $left + $right")
                left + right
            }

            else ->
                throw IllegalArgumentException("Unknown")

        }


}