package leetcode.reverse_words_in_string

import org.junit.jupiter.api.Assertions.assertEquals

//https://leetcode.com/problems/reverse-words-in-a-string/description/
fun main() {
    assertEquals("example good a", reverseWords("a good   example"))
    assertEquals("123bka rtgrkb9875764 blue124 is sky the", reverseWords("the sky is blue124  rtgrkb9875764  123bka   "))
}

//Runtime 236 ms Beats 81.48%
//Memory 36.6 MB Beats 86.11%
fun reverseWords(s: String): String {
    // trim() can be deleted, left for optimal memory usage and performance
    return s.trim().split(" ").reversed().filter { it.isNotBlank() }.joinToString(separator = " ")
}