package multiples_of_3_or_5

import org.junit.jupiter.api.Assertions.assertEquals

fun main() {
    assertEquals(23, solution(10))
    assertEquals(78, solution(20))
    assertEquals(9168, solution(200))

}

fun solution(number: Int): Int {
    if (number < 4) return 0
    return arithmeticProgression(3, number - 1) +
            arithmeticProgression(5, number - 1) -
            arithmeticProgression(15, number - 1)
}

fun arithmeticProgression(first: Int, n: Int): Int {
    return ((n / first) * (first + n - (n % first))) / 2
}
