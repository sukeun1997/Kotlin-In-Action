package ch04

class CountingSet<T>(
    val innerSet: MutableCollection<T> = HashSet<T>()
) : MutableCollection<T> by innerSet {

    var objectsAdd = 0

    override fun add(element: T): Boolean {
        objectsAdd++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectsAdd += elements.size
        return innerSet.addAll(elements)
    }
}

fun main() {
    val cset = CountingSet<Int>()

    cset.addAll(listOf(1, 2, 3))

    println("${cset.objectsAdd} , ${cset.size}")
}