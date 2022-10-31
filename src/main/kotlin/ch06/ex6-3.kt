package ch06

import java.lang.IllegalArgumentException
import java.time.Period

class Address(val streetAddress: String, val zipcode: Int, val city: String, val country: String)

class Company(val name: String, val address: Address?)

class Person(val name: String, val company: Company?)

fun Person.countryName() = this.company?.address?.country ?: "Unknown"

fun printShippingLabel(person: Person) {
    val address = person.company?.address ?: throw IllegalArgumentException("No Address")

    with(address) {

        println(streetAddress)
        println("$zipcode $city, $country")

    }
}

fun main() {

    val person = Person("Dmitry", null)
    println(person.countryName())


    val address = Address("Elsestr. 47", 80637, "munich", "germany")
    val jetbrains = Company("JetBrains", address)
    val person2 = Person("Dmitry", jetbrains)

    printShippingLabel(person2)
    printShippingLabel(person)

}
