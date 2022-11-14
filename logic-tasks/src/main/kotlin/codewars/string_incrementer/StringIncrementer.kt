package codewars.string_incrementer

import org.junit.jupiter.api.Assertions.assertEquals

//https://www.codewars.com/kata/54a91a4883a7de5d7800009c/kotlin
fun main() {
    assertEquals("./7r.145Q;'M0_105284850&668248?9600000001", incrementString("./7r.145Q;'M0_105284850&668248?9600000000"))
    assertEquals("foobar001", incrementString("foobar000"))
    assertEquals("foobar100", incrementString("foobar99"))
    assertEquals("foobar1000", incrementString("foobar999"))
    assertEquals("foobar01000", incrementString("foobar00999"))
    assertEquals("foo1", incrementString("foo"))
    assertEquals("foobar002", incrementString("foobar001"))
    assertEquals("foobar2", incrementString("foobar1"))
    assertEquals("2", incrementString("1"))
    assertEquals("1", incrementString(""))
    assertEquals("010", incrementString("009"))
    assertEquals("foo0100102", incrementString("foo0100101"))
    assertEquals("foo0043", incrementString("foo0042"))
    assertEquals("foo10", incrementString("foo9"))
    assertEquals("foobar24", incrementString("foobar23"))
    assertEquals("#$%^^100", incrementString("#$%^^99"))
    assertEquals("#$%^^0100", incrementString("#$%^^0099"))
    assertEquals("#$%^^1", incrementString("#$%^^"))
}

fun incrementString(str: String) : String {
    if (str == "") return "1"
    val result = str.split(Regex("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)"))

    if (result[result.size - 1].matches(Regex("[0-9]+"))) {
        return result.take(result.size - 1).joinToString("") + manage9s(result[result.size - 1])
    } else if (result.size == 1)
        return result.take(1).joinToString("") + "1"

    return result[0] + manage9s(result[1])
}

private fun manage9s(str: String): String {
    val zeroNonZero = "([0]+)?([1-9]?[0-9]*)".toRegex().find(str)?.groupValues
    if (zeroNonZero?.get(2) == "")
        return str.substring(0, str.length - 1) + "1"
    if (zeroNonZero?.get(2)?.matches("9+".toRegex()) == true)
        return (if (zeroNonZero?.get(1) == "") "" else zeroNonZero?.get(1).substring(0, zeroNonZero?.get(1).length - 1)) +
                "1" + "0".repeat(zeroNonZero?.get(2).length)
    val num = zeroNonZero?.get(2)?.toLong()?.plus(1)
    return zeroNonZero?.get(1) + num.toString()
}