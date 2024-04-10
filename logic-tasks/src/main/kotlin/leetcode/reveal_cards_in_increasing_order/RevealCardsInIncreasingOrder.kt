package leetcode.reveal_cards_in_increasing_order

// https://leetcode.com/problems/reveal-cards-in-increasing-order/description/
fun main() {
    println(RevealCardsInIncreasingOrder.deckRevealedIncreasing(intArrayOf(17,13,11,2,3,5,7)).toList().toString()) // [2,13,3,11,5,17,7]
    println(RevealCardsInIncreasingOrder.deckRevealedIncreasing(intArrayOf(1,1000)).toList().toString()) // [1,1000]
}

class RevealCardsInIncreasingOrder {
    companion object {
        // Runtime 207 ms Beats 100.00% of users with Kotlin
        // Memory 38.84 MB Beats 33.33% of users with Kotlin
        // https://leetcode.com/problems/reveal-cards-in-increasing-order/submissions/1228820991/
        fun deckRevealedIncreasing(deck: IntArray): IntArray {
            deck.sort()
            if (deck.size < 3) return deck
            val res = IntArray(deck.size)
            val deque = ArrayDeque(IntRange(0, deck.size - 1).toList())
            var idx: Int
            deck.forEach {
                idx = deque.removeFirst()
                res[idx] = it
                if (deque.isNotEmpty()) deque.addLast(deque.removeFirst())
            }
            return res
        }
    }
}