package strings


fun <T> joinToString(
    collections: Collection<T>,
    separator: String = ",",
    prefix: String = "",
    postfix: String = ""
): String {
    val stringBuilder = StringBuilder(prefix)

    for ((index, element) in collections.withIndex()) {
        if (index > 0) stringBuilder.append(separator)
        stringBuilder.append(element)
    }

    stringBuilder.append(postfix)

    return stringBuilder.toString()

}

fun String.lastChar(): Char = this[this.length - 1]
