package ch04

class User(val name: String) {

    var address: String = "Undefined"
        get() = "A"
        set(value) {
            println("""
                Address was changed for $name : "$field -> "$value".
            """.trimIndent())
            field = value
        }
}


fun main() {
    val user = User("Alice")
    user.address = "New Address"
}