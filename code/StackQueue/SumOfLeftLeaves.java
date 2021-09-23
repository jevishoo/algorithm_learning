package code.StackQueue;

import java.util.Stack;

/**
 * @since 2020/9/19 9:22
 * @Created by Jevis_Hoo
 * @Description 计算给定二叉树的所有左叶子之和
 */
public class SumOfLeftLeaves {
    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static int getSumOfLeftLeaves(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> help = new Stack<>();
        int result = 0;

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
                if (root != null && root.left == null && root.right == null) help.push(root.val);
            } else {
                root = stack.pop();
                root = root.right;
            }
        }

        while (!help.isEmpty()) {
            result += help.pop();
        }
        return result;
    }

    public static int getSumOfLeftLeaves1(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        int result = 0;

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
                if (root != null && root.left == null && root.right == null) result += root.val;
            } else {
                root = stack.pop();
                root = root.right;
            }
        }
        return result;
    }

    private int sum = 0;

    public int getSumOfLeftLeaves2(TreeNode root) {
        preOrder(root, false);
        return sum;
    }

    public void preOrder(TreeNode node, boolean isLeft) {
        if (node == null) {
            return;
        }

        if (isLeft && node.left == null && node.right == null) {
            sum += node.val;
        }

        preOrder(node.left, true);
        preOrder(node.right, false);
    }

    public static void main(String[] args) {
        SumOfLeftLeaves solution = new SumOfLeftLeaves();

        TreeNode head = new TreeNode(3);
        head.left = new TreeNode(9);
        head.right = new TreeNode(20);
        head.right.left = new TreeNode(15);
        head.right.right = new TreeNode(7);

        System.out.println(solution.getSumOfLeftLeaves2(head));
        solution.sum=0;

        head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);

        System.out.println(solution.getSumOfLeftLeaves2(head));
    }
}
