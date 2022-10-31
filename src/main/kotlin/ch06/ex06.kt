package ch06

class ex06 {

    fun strLenSafe(s: String?): Int = s?.length ?: 0
    fun printAllCaps(s: String?) {
        val allCaps: String? = s?.toUpperCase()
        println(allCaps)
    }
}

fun main() {

    val ex06 = ex06()

    println(ex06.strLenSafe(null))
    println(ex06.strLenSafe("asd®"))

    ex06.printAllCaps("abc")
    ex06.printAllCaps(null)

}
