package code.BinaryTree;

import code.StackQueue.InorderTraversal;

/**
 * @author Jevis Hoo
 * @date 2021/2/18 10:15
 * @description Invert Binary Tree
 */
public class InvertTree {
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
//        root.right = new TreeNode(6);
//        root.left.left = new TreeNode(1);
//        root.left.right = new TreeNode(3);
//        root.right.left = new TreeNode(5);
//        root.right.right = new TreeNode(7);

        System.out.println(InorderTraversal.inorderTraversal(root));
        invertTree(root);
        System.out.println(InorderTraversal.inorderTraversal(root));
    }
}
