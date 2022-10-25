package ch02

import java.io.BufferedReader
import java.io.StringReader

object MainException {

    @JvmStatic
    fun main(args: Array<String>) {
        val reader = BufferedReader(StringReader("312"))
        readNumber(reader)

    }

    private fun readNumber(reader: BufferedReader) {
        val number = try {
            Integer.parseInt(reader.readLine())
        } catch (e: NumberFormatException) {
            return
        }

        println(number)
    }
}