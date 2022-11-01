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
        val (fir,sec) = it.orders.partition { its -> its.isDelivered }
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
fun getMostExpensiveProductBy(customer: Customer): Product? = customer.orders.flatMap { it.products }.maxByOrNull { it.price }

// Return the sum of prices for all the products ordered by a given customer
fun moneySpentBy(customer: Customer): Double = customer.orders.flatMap { it.products }.sumOf { it.price }


fun Customer.getOrderedProducts(): List<Product> = orders.flatMap { it.products }

// Return the set of products that were ordered by all customers
fun Shop.getProductsOrderedByAll(): Set<Product> {

    val set = customers.flatMap { it.getOrderedProducts() }.toSet()
    return customers.fold(set) { all, cus ->  all.intersect(cus.getOrderedProducts().toSet())}
}

