package leetcode.roman_to_int

//https://leetcode.com/problems/roman-to-integer/
fun main() {
    RomanToInt.romanToInt0("III")
}

class RomanToInt {

    companion object {
        val literasMap = mapOf(
            'I' to 1,
            'V' to 5,
            'X' to 10,
            'L' to 50,
            'C' to 100,
            'D' to 500,
            'M' to 1000,
        )

        fun romanToInt1(s: String): Int {
            var res = 0
            var i = s.length - 1
            while (i >= 0) {
                when (s[i]) {
                    'I' -> { res += 1; i-- }
                    'V' -> if (i != 0 && s[i - 1] == 'I') {
                        res += 4
                        i -= 2
                    } else {
                        res += 5
                        i--
                    }
                    'X' -> if (i != 0 && s[i - 1] == 'I') {
                        res += 9
                        i -= 2
                    } else {
                        res += 10
                        i--
                    }
                    'L' -> if (i != 0 && s[i - 1] == 'X') {
                        res += 40
                        i -= 2
                    } else {
                        res += 50
                        i--
                    }
                    'C' -> if (i != 0 && s[i - 1] == 'X') {
                        res += 90
                        i -= 2
                    } else {
                        res += 100
                        i--
                    }
                    'D' -> if (i != 0 && s[i - 1] == 'C') {
                        res += 400
                        i -= 2
                    } else {
                        res += 500
                        i--
                    }
                    'M' -> if (i != 0 && s[i - 1] == 'C') {
                        res += 900
                        i -= 2
                    } else {
                        res += 1000
                        i--
                    }
                }
            }
            return res
        }

        fun romanToInt0(s: String): Int {
            var res = 0
            var i = 0
            while (i < s.length) {
                when (s[i]) {
                    'I' -> if (i + 1 < s.length && (s[i + 1] == 'V' || s[i + 1] == 'X')) {
                        if (s[i + 1] == 'V')
                            res += 4
                        else
                            res += 9
                        i += 2
                    } else {
                        res += 1
                        i++
                    }
                    'X' -> if (i + 1 < s.length && (s[i + 1] == 'L' || s[i + 1] == 'C')) {
                        if (s[i + 1] == 'L')
                            res += 40
                        else
                            res += 90
                        i += 2
                    } else {
                        res += 10
                        i++
                    }
                    'C' -> if (i + 1 < s.length && (s[i + 1] == 'D' || s[i + 1] == 'M')) {
                        if (s[i + 1] == 'D')
                            res += 400
                        else
                            res += 900
                        i += 2
                    } else {
                        res += 100
                        i++
                    }
                    'V' -> { res += 5; i++ }
                    'L' -> {res += 50; i++ }
                    'D' -> {res += 500; i++ }
                    'M' -> {res += 1000; i++ }
                }
            }
            return res
        }
    }

}