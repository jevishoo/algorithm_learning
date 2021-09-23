package code.BinaryTree;

import code.StackQueue.InorderTraversal;
import code.StackQueue.PostorderTraversal;

/**
 * @author Jevis Hoo
 * @since 2021/2/3 12:15
 * @description Flatten Binary Tree to Linked List
 * <p>
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list
 * and the left child pointer is always null.
 * <p>
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 */
public class FlattenTreeToList {
    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        flatten(root.left);
        flatten(root.right);

        TreeNode mostRight;
        if (root.left != null) {
            mostRight = root.left;
            while (mostRight.right != null) {
                mostRight = mostRight.right;
            }
            mostRight.right = root.right;
            root.right = root.left;
            root.left = null;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        flatten(root);
        System.out.println(InorderTraversal.inorderTraversal(root));
        System.out.println(PostorderTraversal.postorderTraversal(root));
    }
}
