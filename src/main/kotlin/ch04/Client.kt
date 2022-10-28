package ch04

data class Client(val name: String, val postalCode: Int) {

}

fun main() {
    val client1 = Client("Hi", 30)
    val client2 = Client("Hi", 30)

    println(client1.toString())
    println(client1 == client2)


    val copy = client1.copy(name = "HiCopy", postalCode = 50)

    println(copy)

}