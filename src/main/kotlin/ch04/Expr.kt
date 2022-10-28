package ch04

sealed class Expr

class Num(val value: Int) : Expr()

class Sum(val left: Expr, val right: Expr) : Expr()

class Sum2(val left: Expr, val right: Expr) : Expr()

fun eval(e: Expr): Int =
    when (e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
        is Sum2 -> TODO()
    }