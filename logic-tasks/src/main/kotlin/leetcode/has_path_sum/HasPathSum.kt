package leetcode.has_path_sum

//https://leetcode.com/problems/path-sum/
fun main() {
    val root = HasPathSum.TreeNode(5)
    root.left = HasPathSum.TreeNode(4)
    root.left!!.left = HasPathSum.TreeNode(11)
    root.left!!.left!!.left = HasPathSum.TreeNode(7)
    root.left!!.left!!.right = HasPathSum.TreeNode(2)

    root.right = HasPathSum.TreeNode(8)
    root.right!!.left = HasPathSum.TreeNode(13)
    root.right!!.right = HasPathSum.TreeNode(4)
    root.right!!.right!!.right = HasPathSum.TreeNode(1)

    assert(HasPathSum.hasPathSum(root, 22)) // should be true

    val root2 = HasPathSum.TreeNode(1)
    root2.left = HasPathSum.TreeNode(2)
    assert(!HasPathSum.hasPathSum(root2, 1)) // should be false

    val root3 = HasPathSum.TreeNode(1)
    assert(HasPathSum.hasPathSum(root3, 1)) // should be true
}

class HasPathSum {

    companion object {
        // Runtime: 187 ms, faster than 98.90% of Kotlin online submissions for Path Sum.
        // Memory Usage: 35.9 MB, less than 89.43% of Kotlin online submissions for Path Sum.
        fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
            if (root?.`val` == targetSum && root.left == null && root.right == null) return true
            return (if (root?.left != null) {
                root.left?.apply { `val` += root.`val` }
                if (root.left?.`val` == targetSum && root.left == null && root.right == null) return true
                hasPathSum(root.left, targetSum)
            } else false)
                .or(if (root?.right != null) {
                    root.right?.apply { `val` += root.`val` }
                    if (root.right?.`val` == targetSum && root.left == null && root.right == null) return true
                    hasPathSum(root.right, targetSum)
                } else false)
        }

        // Runtime: 344 ms, faster than 31.28% of Kotlin online submissions for Path Sum.
        // Memory Usage: 38.5 MB, less than 35.68% of Kotlin online submissions for Path Sum.
        fun hasPathSumSlower(root: TreeNode?, targetSum: Int): Boolean {
            if (root?.`val` == targetSum && root.left == null && root.right == null) return true
            return depthCheck(root?.left, targetSum, root?.`val`).or(depthCheck(root?.right, targetSum, root?.`val`))
        }

        private fun depthCheck(node: TreeNode?, targetSum: Int, rootVal: Int?): Boolean {
            return (if (node != null) {
                node.apply { `val` += rootVal!! }
                if (node.`val` == targetSum && node.left == null && node.right == null) return true
                hasPathSum(node, targetSum)
            } else false)
        }

    }

    //Definition for a binary tree node.
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}

