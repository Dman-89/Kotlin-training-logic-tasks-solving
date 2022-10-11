package codewars.parts_of_a_list

import java.lang.StringBuilder

//https://www.codewars.com/kata/56f3a1e899b386da78000732
fun main() {
    PartsOfAList.partlist(arrayOf("cdIw", "tzIy", "xDu", "rThG")).forEach { println(it.contentToString()) }
}

class PartsOfAList {
    companion object {
        fun partlist(arr: Array<String>): Array<Array<String>> {
            val res = Array<Array<String>>(arr.size - 1) { Array<String>(2) {""} }
            for (i in 0..arr.size - 2) {
                val s1 = StringBuilder()
                val s2 = StringBuilder()
                for (j in 0..i) if (j < i) s1.append(arr[j] + " ") else s1.append(arr[j])
                for (k in i + 1 until arr.size) if (k < arr.size - 1) s2.append(arr[k] + " ") else s2.append(arr[k])
                res[i][0] = s1.toString()
                res[i][1] = s2.toString()
            }
            return res
        }
    }
}