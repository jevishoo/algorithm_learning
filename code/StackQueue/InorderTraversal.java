package code.StackQueue;

import code.BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Date 2020/9/13 9:56
 * @Created by Jevis_Hoo
 * @Description Inorder Traversal using Stack
 * <p>
 * Given a binary tree, return the inorder traversal of its nodes' valueues.
 * <p>
 * Recursive solution is trivial, could you do it iteratively?
 */

public class InorderTraversal {
    public static void inOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                list.add(root.value);
                root = root.right;
            }
        }

        return list;
    }

    public static List<Integer> getInorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                list.add(root.value);
                root = root.right;
            }
        }

        return list;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(5);
        head.left = new TreeNode(3);
        head.right = new TreeNode(8);
        head.left.left = new TreeNode(2);
        head.left.right = new TreeNode(4);
        head.left.left.left = new TreeNode(1);
        head.right.left = new TreeNode(7);
        head.right.left.left = new TreeNode(6);
        head.right.right = new TreeNode(10);
        head.right.right.left = new TreeNode(9);
        head.right.right.right = new TreeNode(11);

        inOrderRecur(head);
        System.out.println();
        List<Integer> lst = inorderTraversal(head);
        System.out.println(lst);
        List<Integer> l = getInorderTraversal(head);
        System.out.println(l);
    }

}
