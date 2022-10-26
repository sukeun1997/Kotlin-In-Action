package ch03

import strings.joinToString
import strings.lastChar as last

fun View.showOff() = println("View")
fun Button.showOff() = println("View")
var StringBuilder.lastChar : Char
    get() = get(length - 1)
    set(value: Char) = this.setCharAt(length - 1, value)


object ExCollection {

    @JvmStatic
    fun main(args: Array<String>) {
        val set = hashSetOf(1, 2, 3)
        val list = arrayOf(1, 2, 4)

        println(joinToString(set))
        println("Kotlin".last())

        val view: View = Button()
        view.click()

        view.showOff()

        val sb = StringBuilder("Kotlin")
        sb.lastChar = '!'
        println(sb.toString())

        println(list.max())

        val listClone = listOf(5, *list)

        println("list = ${listClone}")

    }

}

