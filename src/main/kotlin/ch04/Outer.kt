package ch04

class Outer {

    val a = 0

    inner class Inner {
        fun getOuterReference() : Int = this@Outer.a
    }
}