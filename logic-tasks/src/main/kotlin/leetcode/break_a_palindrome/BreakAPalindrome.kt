package leetcode.break_a_palindrome

import java.lang.StringBuilder

//https://leetcode.com/problems/break-a-palindrome/
fun main() {
    println(BreakAPalindrome.breakPalindrome("")) // ""
    println(BreakAPalindrome.breakPalindrome("a")) // ""
    println(BreakAPalindrome.breakPalindrome("aba")) // abb
    println(BreakAPalindrome.breakPalindrome("abccba")) // aaccba
    println(BreakAPalindrome.breakPalindrome("aaaaaa")) // aaaaab
    println(BreakAPalindrome.breakPalindrome("aaaaaaa")) // aaaaaab
}

class BreakAPalindrome {
    companion object {

        //Runtime: 219 ms, faster than 75.00% of Kotlin online submissions for Break a Palindrome.
        //Memory Usage: 34.1 MB, less than 75.00% of Kotlin online submissions for Break a Palindrome.
        fun breakPalindrome(palindrome: String): String {
            if (palindrome.length <= 1) return ""
            for (i in 0 until palindrome.length / 2)
                 if (palindrome[i] != 'a') return palindrome.substring(0, i) + 'a' + palindrome.substring(i + 1, palindrome.length)
            return palindrome.substring(0, palindrome.length - 1) + 'b'
        }

        //Runtime: 300 ms, faster than 25.00% of Kotlin online submissions for Break a Palindrome.
        //Memory Usage: 34.5 MB, less than 25.00% of Kotlin online submissions for Break a Palindrome.
        fun breakPalindrome0(palindrome: String): String {
            if (palindrome.length <= 1) return ""
            val res = StringBuilder()
            for (i in 0..palindrome.length / 2) {
                if (i == palindrome.length / 2 - 1 && palindrome[i] == 'a') {
                    res.append("a".repeat(palindrome.length - 1 - i))
                    res.append('b')
                    return res.toString()
                }
                if (palindrome[i] != 'a') {
                    res.append('a')
                    res.append(palindrome.substring(i + 1))
                    return res.toString()
                } else res.append(palindrome[i])
            }
            return res.toString()
        }
    }
}