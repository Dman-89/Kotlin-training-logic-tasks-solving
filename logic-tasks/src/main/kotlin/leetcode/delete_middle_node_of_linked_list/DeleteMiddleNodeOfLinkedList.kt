package leetcode.delete_middle_node_of_linked_list

//https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
fun main() {

}

class DeleteMiddleNodeOfLinkedList {
    companion object {
        //Runtime: 1592 ms, faster than 7.14% of Kotlin online submissions for Delete the Middle Node of a Linked List. (not representative - runtime statistically is the same across all solutions)
        //Memory Usage: 185.3 MB, less than 57.14% of Kotlin online submissions for Delete the Middle Node of a Linked List.
        fun deleteMiddle(head: ListNode?): ListNode? {
            var len = 1
            var currNode = head
            while (currNode?.next != null) { currNode = currNode.next; len++ }
            if (len == 1) return null
            val middle = len / 2 + 1
            currNode = head
            var i = 0
            while (++i < middle - 1) currNode = currNode?.next
            if (currNode?.next?.next != null)
                currNode.next?.`val` = currNode.next?.next?.`val`!!
            currNode?.next = currNode?.next?.next
            return head
        }
    }
}

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 */
class ListNode(var `val`: Int) {
    var next: ListNode? = null
}
