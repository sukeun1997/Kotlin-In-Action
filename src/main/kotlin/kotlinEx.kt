import java.util.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class kotlinEx {

    fun containsEven(collection: Collection<Int>): Boolean =
        collection.any { collection.contains(it) }

    val set = setOf(listOf(1))
}

fun Shop.getSetOfCustomers(): Set<Customer> = customers.toSet()

data class Shop(val name: String, val customers: List<Customer>)

data class Customer(val name: String, val city: City, val orders: List<Order>)


class Order(val products: List<Product>, val isDelivered: Boolean)

class Product(val name: String, val price: Double)

class City(val name: String)


fun Shop.getCustomersSortedByOrders(): List<Customer> = customers.sortedByDescending { it.orders.size }

// Find all the different cities the customers are from
fun Shop.getCustomerCities(): Set<City> = customers.map { it.city }.toSet()

// Find the customers living in a given city
fun Shop.getCustomersFrom(city: City): List<Customer> = customers.filter { it.city == city }


// Return true if all customers are from a given city
fun Shop.checkAllCustomersAreFrom(city: City): Boolean = customers.all { it.city == city }

// Return true if there is at least one customer from a given city
fun Shop.hasCustomerFrom(city: City): Boolean = customers.any { it.city == city }

// Return the number of customers from a given city
fun Shop.countCustomersFrom(city: City): Int = customers.count { it.city == city }

// Return a customer who lives in a given city, or null if there is none
fun Shop.findCustomerFrom(city: City): Customer? = customers.firstOrNull { it.city == city }


// Build a map from the customer name to the customer
fun Shop.nameToCustomerMap(): Map<String, Customer> = customers.associateBy { it.name }

// Build a map from the customer to their city
fun Shop.customerToCityMap(): Map<Customer, City> = customers.associateWith { it.city }

// Build a map from the customer name to their city
fun Shop.customerNameToCityMap(): Map<String, City> = customers.associate { it.name to it.city }

// Build a map that stores the customers living in a given city
fun Shop.groupCustomersByCity(): Map<City, List<Customer>> = customers.groupBy { it.city }

// Return customers who have more undelivered orders than delivered
fun Shop.getCustomersWithMoreUndeliveredOrders(): Set<Customer> =
    customers.filter {
        val (fir, sec) = it.orders.partition { its -> its.isDelivered }
        fir.size > sec.size
    }
        .toSet()

// Return all products the given customer has ordered
//fun Customer.getOrderedProducts(): List<Product> = orders.flatMap { it.products }.toList()

// Return all products that were ordered by at least one customer
fun Shop.getOrderedProducts(): Set<Product> = customers.flatMap { it.orders }.flatMap { it.products }.toSet()


// Return a customer who has placed the maximum amount of orders
fun Shop.getCustomerWithMaxOrders(): Customer? = customers.maxByOrNull { it.orders.size }

// Return the most expensive product that has been ordered by the given customer
fun getMostExpensiveProductBy(customer: Customer): Product? =
    customer.orders.flatMap { it.products }.maxByOrNull { it.price }

// Return the sum of prices for all the products ordered by a given customer
fun moneySpentBy(customer: Customer): Double = customer.orders.flatMap { it.products }.sumOf { it.price }


//fun Customer.getOrderedProducts(): List<Product> = orders.flatMap { it.products }

// Return the set of products that were ordered by all customers
fun Shop.getProductsOrderedByAll(): Set<Product> {

    val set = customers.flatMap { it.getOrderedProducts() }.toSet()
    return customers.fold(set) { all, cus -> all.intersect(cus.getOrderedProducts().toSet()) }
}


/*
// Find the most expensive product among all the delivered products
// ordered by the customer. Use `Order.isDelivered` flag.
fun findMostExpensiveProductBy(customer: Customer): Product? {
    return customer.orders.filter { it.isDelivered }.flatMap { it.products }.maxByOrNull { it.price }
}

// Count the amount of times a product was ordered.
// Note that a customer may order the same product several times.
fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    return this.customers.flatMap { it.orders }.flatMap { it.products }.count { it == product }
}
*/

// Find the most expensive product among all the delivered products
// ordered by the customer. Use `Order.isDelivered` flag.
fun findMostExpensiveProductBy(customer: Customer): Product? {
    return customer.orders.asSequence()
        .filter { it.isDelivered }
        .flatMap { it.products }
        .maxByOrNull { it.price }
}

// Count the amount of times a product was ordered.
// Note that a customer may order the same product several times.
fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    return this.customers.flatMap { it.orders }.flatMap { it.products }.count { it == product }
}

fun Customer.getOrderedProducts(): Sequence<Product> = this.orders.flatMap { it.products }.asSequence()

fun doSomethingWithCollection(collection: Collection<String>): Collection<String>? {

    val groupsByLength = collection.groupBy { s -> s.length }

    val maximumSizeOfGroup = groupsByLength.values.maxOfOrNull { group -> group.size }

    return groupsByLength.values.firstOrNull { group -> group.size == maximumSizeOfGroup }
}

/**
 * Properties
 */

class PropertyExample() {
    var counter = 0
    var propertyWithCounter: Int? = null
        set(value) {
            counter++
            field = value
        }
}

/*class LazyProperty(val initializer: () -> Int) {

    var value : Int? = null
    val lazy: Int
        get() {
            if (value == null) {
                value = initializer()
            }

            return value!!
        }
}*/

class LazyProperty(val initializer: () -> Int) {
    val lazyValue: Int by lazy(initializer)
}

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

fun MyDate.toMiles(): Long {
    val c = Calendar.getInstance()
    c.set(year, month, dayOfMonth)
    return c.timeInMillis
}

fun Long.toDate(): MyDate {
    val c = Calendar.getInstance()
    c.timeInMillis = this
    return MyDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE))

}

class D {
    var date: MyDate by EffectiveDate()
}

class EffectiveDate<R> : ReadWriteProperty<R, MyDate> {

    var timeInMillis: Long? = null

    override fun getValue(thisRef: R, property: KProperty<*>): MyDate {
        return timeInMillis!!.toDate()
    }

    override fun setValue(thisRef: R, property: KProperty<*>, value: MyDate) {
        timeInMillis = value.toMiles()
    }
}

/**
 * Builders
 */

fun task(): List<Boolean> {
    val isEven: Int.() -> Boolean = { this % 2 == 0 }
    val isOdd: Int.() -> Boolean = { this % 2 == 1 }

    return listOf(42.isOdd(), 239.isOdd(), 294823098.isEven())
}


fun usage(): Map<Int, String> {
    return buildMutableMap {
        put(0, "0")
        for (i in 1..10) {
            put(i, "$i")
        }
    }
}

fun <K, V> buildMutableMap(build: HashMap<K, V>.() -> Unit): Map<K, V> {
    val map = HashMap<K, V>()
    map.build()

    return map

}


fun <T> T.myApply(f: T.() -> Unit): T {

    f()
    return this
}

fun createString(): String {
    return StringBuilder().myApply {
        append("Numbers: ")
        for (i in 1..10) {
            append(i)
        }
    }.toString()
}

fun createMap(): Map<Int, String> {
    return hashMapOf<Int, String>().myApply {
        put(0, "0")
        for (i in 1..10) {
            put(i, "$i")
        }
    }
}


fun <T, C : MutableCollection<T>> Collection<T>.partitionTo(
    first: C,
    second: C,
    predicate: (T) -> Boolean,
): Pair<C, C> {

    for (element in this) {
        if (predicate(element)) {
            first.add(element)
        } else {
            second.add(element)
        }
    }

    return Pair(first, second)

}


fun partitionWordsAndLines() {
    val (words, lines) = listOf("a", "a b", "c", "d e")
        .partitionTo(ArrayList(), ArrayList()) { s -> !s.contains(" ") }
    check(words == listOf("a", "c"))
    check(lines == listOf("a b", "d e"))
}

fun partitionLettersAndOtherSymbols() {
    val (letters, other) = setOf('a', '%', 'r', '}')
        .partitionTo(HashSet(), HashSet()) { c -> c in 'a'..'z' || c in 'A'..'Z' }
    check(letters == setOf('a', 'r'))
    check(other == setOf('%', '}'))
}
