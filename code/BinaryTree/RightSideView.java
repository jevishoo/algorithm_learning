package code.BinaryTree;

import java.util.*;

/**
 * @author Jevis Hoo
 * @since 2021/2/8 15:11
 * @description
 */
public class RightSideView {
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new LinkedList<>();

        if (root == null) {
            return list;
        }
        list.add(root.value);

        getRightSideView(root, 1, list);
        return list;
    }

    public static void getRightSideView(TreeNode root, int level, List<Integer> list) {
        if (root == null) {
            return;
        }

        if (list.size() < level) {
            list.add(root.value);
        }

        level++;

        getRightSideView(root.right, level, list);
        getRightSideView(root.left, level, list);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(6);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);

        System.out.println(Collections.singletonList(rightSideView(root)));
    }
}
