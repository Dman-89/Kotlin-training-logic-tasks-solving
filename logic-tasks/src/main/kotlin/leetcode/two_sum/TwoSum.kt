package leetcode.two_sum

//https://leetcode.com/problems/two-sum/
fun main() {
    println(TwoSum.twoSum(intArrayOf(2,7,11,15), 9)) // [0,1]
    println(TwoSum.twoSum(intArrayOf(3,3), 6)) // [0,1]
    println(TwoSum.twoSum(intArrayOf(10,5,-20,0,0,1,5,8,2,-12),0)) // [3,4]
}

class TwoSum {
    companion object {
        //Runtime: 486 ms, faster than 30.91% of Kotlin online submissions for Two Sum.
        //Memory Usage: 45.4 MB, less than 8.93% of Kotlin online submissions for Two Sum.
        fun twoSum(nums: IntArray, target: Int): IntArray {
            val copy = nums.copyOf()
            copy.sort()
            var i = 0
            var k = nums.size - 1
            var iNum = copy[i]
            var kNum = copy[k]
            var currSum = iNum + kNum
            while (currSum != target) {
                if (currSum > target) { kNum = copy[--k] }
                else if (currSum > target) { iNum = copy[++i] }
                currSum = iNum + kNum
            }
            return intArrayOf(nums.indexOf(iNum), nums.indexOfLast {it == kNum})
        }
    }
}