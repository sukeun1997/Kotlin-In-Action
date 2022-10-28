package ch04

import java.awt.Window
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

class A {

    private val age = 0

    private fun aFun() {

    }
    companion object {
        fun bar() {
            println("Companion Test")
        }
    }

    inner class B {

        fun access() {
            println("$age")
        }
    }
}

fun A.Companion.bar2() {
    println("Bar2")
}

fun main() {
    A.bar2()
}

fun countClick(window: Window) {
    var clickCount = 0

    window.addMouseListener(object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent?) {
            clickCount++

            super.mouseClicked(e)
        }
    })
}
