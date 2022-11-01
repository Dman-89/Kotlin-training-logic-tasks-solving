package leetcode.palindrome_linked_list

import java.util.*

//https://leetcode.com/problems/palindrome-linked-list/
fun main() {
    val head = ListNode(1)
    head.next = ListNode(2)
    head.next!!.next = ListNode(3)
    head.next!!.next?.next = ListNode(2)
    head.next!!.next?.next?.next = ListNode(1)
    println(isPalindrome(head)) // true
}

//Runtime: 608 ms, faster than 87.12% of Kotlin online submissions for Palindrome Linked List.
//Memory Usage: 49.3 MB, less than 98.23% of Kotlin online submissions for Palindrome Linked List.
fun isPalindrome(head: ListNode?): Boolean {
    var len = 0
    var headLoop = head
    while (headLoop != null) { len++; headLoop = headLoop.next }
    val tillMiddle = LinkedList<Int>()
    headLoop = head
    for (i in 0 until len / 2) {
        if (headLoop != null) {
            tillMiddle.add(headLoop.`val`)
        }
        headLoop = headLoop?.next
    }
    tillMiddle.reverse()
    val iterator = tillMiddle.iterator()
    if (len % 2 != 0) headLoop = headLoop?.next
    while(headLoop != null) {
        if (headLoop.`val` != iterator.next()) return false
        headLoop = headLoop.next
    }
    return true
}

//Definition for singly-linked list.
class ListNode(var `val`: Int) {
    var next: ListNode? = null
}