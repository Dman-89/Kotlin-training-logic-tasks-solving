package codewars.tree_to_linked_list;

import java.util.Iterator;
import java.util.TreeSet;

//https://www.codewars.com/kata/586640f739c5ab4f4c00022f/java
public class TreeToLinkedList {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(17, new ListNode(1));
        ListNode l2 = new ListNode(3);
        ListNode l3 = new ListNode(1);
        ListNode l4 = new ListNode(2);
        ListNode l5 = new ListNode(16);
        ListNode l6 = new ListNode(7, new ListNode(3));
        TreeNode root = new TreeNode(l1, new TreeNode(l2, new TreeNode(l4), null), new TreeNode(l3, new TreeNode(l5), new TreeNode(l6)));

        flatten(root);

    }

    static ListNode flatten(TreeNode root) {
        if (root == null) return null;
        TreeSet<Integer> treeSet = new TreeSet<>();
        collectDataInTree(root, treeSet);
        Iterator<Integer> treeIterator = treeSet.iterator();
        ListNode head = new ListNode(treeIterator.next());
        ListNode curr = head;
        while (treeIterator.hasNext()) {
            curr.next = new ListNode(treeIterator.next());
            curr = curr.next;
        }

        return head;
    }

    private static void collectDataInTree(TreeNode root, TreeSet<Integer> treeSetDest) {
        if(root == null) return;
        ListNode currNode = root.value;
        while (currNode != null) {
            treeSetDest.add(currNode.data);
            currNode = currNode.next;
        }
        collectDataInTree(root.left, treeSetDest);
        collectDataInTree(root.right, treeSetDest);
    }


}

class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public ListNode value;

    TreeNode(ListNode value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    TreeNode(ListNode value) {
        this(value, null, null);
    }
}

class ListNode {
    public int data;
    public ListNode next;

    ListNode(int data, ListNode next) {
        this.data = data;
        this.next = next;
    }

    ListNode(int data) {
        this(data, null);
    }
}