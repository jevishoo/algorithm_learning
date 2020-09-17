package code.StackQueue;

import java.util.Stack;

/**
 * @Date 2020/9/17 19:19
 * @Created by Jevis_Hoo
 * @Description Binary Search Tree Iterator
 * <p>
 * Implement an iterator over a binary search tree (BST).
 * Your iterator will be initialized with the root node of a BST.
 * <p>
 * Calling next() will return the next smallest number in the BST.
 * <p>
 * next() and hasNext() should run in average O(1) time and uses O(h) memory,
 * where h is the height of the tree.
 * <p>
 * You may assume that next() call will always be valid, that is,
 * there will be at least a next smallest number in the BST when next() is called.
 */
public class BSTIterator {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        this.stack = new Stack<>();

        while (root != null) {
            stack.add(root);
            root = root.left;
        }
    }


    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode node = stack.pop();
        int result = node.val;

        if (node.right != null) {
            node = node.right;

            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        return result;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(7);
        head.left = new TreeNode(3);
        head.right = new TreeNode(15);
        head.left.left = new TreeNode(9);
        head.left.right = new TreeNode(20);

        BSTIterator iterator = new BSTIterator(head);
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
    }
}
