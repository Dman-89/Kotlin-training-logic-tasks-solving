package has_path_sum

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
        fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
            if (root?.`val` == targetSum && root.left == null && root.right == null) return true
            return (if (root?.left != null) {
                root.left?.`val` = root.left?.`val`?.plus(root.`val`)!!
                if (root.left?.`val` == targetSum && root.left == null && root.right == null) return true
                hasPathSum(root.left, targetSum)
            } else false)
                .or(if (root?.right != null) {
                    root.right?.`val` = root.right?.`val`?.plus(root.`val`)!!
                    if (root.right?.`val` == targetSum && root.left == null && root.right == null) return true
                    hasPathSum(root.right, targetSum)
            } else false)
        }
    }

    //Definition for a binary tree node.
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}

