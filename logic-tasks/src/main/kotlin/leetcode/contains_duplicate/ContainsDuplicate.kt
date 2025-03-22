package leetcode.contains_duplicate

//https://leetcode.com/problems/contains-duplicate
fun main() {
    println(Solution.containsDuplicate(intArrayOf(1,2,3,1))) // true
    println(Solution.containsDuplicate(intArrayOf(1,2,3,4))) // false
    println(Solution.containsDuplicate(intArrayOf(1,1,1,3,3,4,3,2,4,2))) // ture
}



class Solution {
    companion object {
        // Runtime 17 ms Beats 84.11%
        // Memory 62.49 MB Beats 16.42%
        fun containsDuplicate(nums: IntArray): Boolean {
            val checked = mutableSetOf<Int>()
            nums.forEach {
                if (it in checked) return true
                checked.add(it)
            }
            return false
        }

        // Runtime 33 ms Beats 36.82%
        // Memory 64.58 MB Beats 6.19%
        fun containsDuplicateShort(nums: IntArray): Boolean {
            return nums.toSet().size != nums.size
        }
    }
}