package code.BinaryTree;

/**
 * @author Jevis Hoo
 * @date 2021/2/2 12:10
 * @description Path Sum
 * <p>
 * Given the root of a binary tree and an integer targetSum,
 * return true if the tree has a **root-to-leaf** path such that adding up all the values along the path equals targetSum.
 */
public class PathSum {
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.value == targetSum && root.left == null && root.right == null) {
            return true;
        }

        boolean lData = hasPathSum(root.left, targetSum - root.value);
        boolean rData = hasPathSum(root.right, targetSum - root.value);

        return lData || rData;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(0);
        root.right = new TreeNode(9);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        System.out.println(hasPathSum(root, 15));

        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        System.out.println(hasPathSum(head, 1));

    }
}
