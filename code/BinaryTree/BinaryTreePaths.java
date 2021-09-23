package code.BinaryTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Jevis Hoo
 * @since 2021/2/19 9:41
 * @description Binary Tree Paths
 * <p>
 * Given a binary tree, return all root-to-leaf paths.
 */
public class BinaryTreePaths {
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root != null) {
            traverseAllPaths(root, new StringBuilder(), result);
        }
        return result;
    }

    public static void traverseAllPaths(TreeNode root, StringBuilder currentPath, List<String> result) {
        if (root.left == null && root.right == null) {
            result.add(currentPath.append(root.value).toString());
        }

        if (root.left != null) {
            traverseAllPaths(root.left, new StringBuilder(currentPath).append(root.value).append("->"), result);
        }

        if (root.right != null) {
            traverseAllPaths(root.right, new StringBuilder(currentPath).append(root.value).append("->"), result);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        List<String> paths = binaryTreePaths(root);
        System.out.println(Collections.singletonList(paths));
    }
}
