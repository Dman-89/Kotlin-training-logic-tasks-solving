package leetcode.check_if_two_string_arrays_are_equivalent

//https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/
fun main() {
    println(CheckIfTwoStringArraysAreEquivalent.arrayStringsAreEqual(arrayOf("ab", "c"), arrayOf("a", "bc"))) // true
    println(CheckIfTwoStringArraysAreEquivalent.arrayStringsAreEqual(arrayOf("abcd", "c"), arrayOf("a", "bc"))) // false
    println(CheckIfTwoStringArraysAreEquivalent.arrayStringsAreEqual(arrayOf("abcd", "efg"), arrayOf("a", "bcdefg"))) // ture
}



class CheckIfTwoStringArraysAreEquivalent {
    companion object {
        //Runtime: 325 ms, faster than 25.27% of Kotlin online submissions for Check If Two String Arrays are Equivalent.
        //Memory Usage: 38.6 MB, less than 22.94% of Kotlin online submissions for Check If Two String Arrays are Equivalent.
        fun arrayStringsAreEqual(word1: Array<String>, word2: Array<String>): Boolean {
            val s1 = word1.joinToString(separator = "")
            val s2 = word2.joinToString(separator = "")
            if (s1.length != s2.length) return false
            s1.forEachIndexed {idx, char -> if (char != s2[idx]) return false }
            return true
        }
    }
}