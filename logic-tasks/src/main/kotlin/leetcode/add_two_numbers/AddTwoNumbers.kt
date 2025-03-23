package leetcode.add_two_numbers

//https://leetcode.com/problems/add-two-numbers
fun main() {
    println("case l1 = [2,4,3], l2 = [5,6,4]; expected = [7,0,8]")

    var l1 = ListNode(2)
    l1.next = ListNode(4)
    l1.next?.next = ListNode(3)

    var l2 = ListNode(5)
    l2.next = ListNode(6)
    l2.next?.next = ListNode(4)

    var res = Solution.addTwoNumbers(l1, l2)
    while (res != null) {
        print("${res.`val`} -> ")
        res = res.next
    }

    println("\n\ncase l1 = [0], l2 = [0], expected = [0]")

    l1 = ListNode(0)
    l2 = ListNode(0)

    res = Solution.addTwoNumbers(l1, l2)
    while (res != null) {
        print("${res.`val`} -> ")
        res = res.next
    }

    println("\n\ncase l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]; expected = [8,9,9,9,0,0,0,1]")

    l1 = ListNode(9)
    l1.next = ListNode(9)
    l1.next?.next = ListNode(9)
    l1.next?.next?.next = ListNode(9)
    l1.next?.next?.next?.next = ListNode(9)
    l1.next?.next?.next?.next?.next = ListNode(9)
    l1.next?.next?.next?.next?.next?.next = ListNode(9)

    l2 = ListNode(9)
    l2.next = ListNode(9)
    l2.next?.next = ListNode(9)
    l2.next?.next?.next = ListNode(9)

    res = Solution.addTwoNumbers(l1, l2)
    while (res != null) {
        print("${res.`val`} -> ")
        res = res.next
    }

    println("\n\ncase l1 = [5], l2 = [5]; expected = [0,1]")

    l1 = ListNode(5)

    l2 = ListNode(5)

    res = Solution.addTwoNumbers(l1, l2)
    while (res != null) {
        print("${res.`val`} -> ")
        res = res.next
    }
}

class Solution {
    companion object {
        // Runtime 2 ms Beats 94.03%
        // Memory 46.50 MB Beats 17.76%
        fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
            var list1 = l1
            var list2 = l2
            if (list1 == null && list2 == null) return null
            if (list1 == null) return list2
            if (list2 == null) return list1
            var addOrder = 0
            var res: ListNode? = null
            var resStart: ListNode? = null
            while (list1 != null && list2 != null) {
                if (res == null) {
                    res = ListNode((list1.`val` + list2.`val`) % 10)
                    resStart = res
                } else {
                    res.next = ListNode((list1.`val` + list2.`val` + addOrder) % 10)
                    res = res.next
                }
                if (list1.`val` + list2.`val` + addOrder > 9) {
                    addOrder = 1
                } else {
                    addOrder = 0
                }
                list1 = list1.next
                list2 = list2.next
            }
            if (list1 == null) res?.next = list2
            if (list2 == null) res?.next = list1

            if (addOrder == 1 && res?.next == null) res?.next = ListNode(0)
            if (addOrder == 1) {
                res = res?.next
                do {
                    val sumAddOrder = res?.`val`?.plus(addOrder) ?: addOrder
                    if (sumAddOrder != 10) {
                        res?.`val` = sumAddOrder
                        break
                    } else {
                        res?.`val` = 0
                    }
                    res = generateNextNodeIfNotExist(res)
                } while(true)
            }
            return resStart
        }

        private fun generateNextNodeIfNotExist(l: ListNode?): ListNode? {
            return l?.next ?: run {
                l?.next = ListNode(0)
                return l?.next
            }
        }
    }
}

//Definition for singly-linked list.
class ListNode(var `val`: Int) {
    var next: ListNode? = null
}