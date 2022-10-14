package leetcode.delete_node_in_linked_list

//https://leetcode.com/problems/delete-node-in-a-linked-list/submissions/
fun main() {
}

class DeleteNodeInALinkedList {
    companion object {
        //Runtime: 231 ms, faster than 82.52% of Kotlin online submissions for Delete Node in a Linked List.
        //Memory Usage: 36.9 MB, less than 74.83% of Kotlin online submissions for Delete Node in a Linked List.
        // worked on Kotlin 1.3.10 online compiler in leetcode
        fun deleteNode(node: ListNode?) {
            node?.`val` = node?.next?.`val`!! // double bang operator wasn't needed in online submission
            node?.next = node?.next?.next
        }
    }
}

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
**/
class ListNode(var `val`: Int) {
    var next: ListNode? = null
}