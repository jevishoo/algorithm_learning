package code.BinaryTree;

/**
 * @Date 2020/9/18 21:00
 * @Created by Jevis_Hoo
 * @Description 二叉搜索树的检查
 * <p>
 * 检测树是否为二叉搜索树
 */

public class BSTCheck {
    public static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode() {
        }

    }

    public static boolean isBST(TreeNode root, int min, int max) {
        if (root == null) return true;

        return root.val > min
                && root.val < max
                && isBST(root.left, min, root.val)
                && isBST(root.right, root.val, max);
    }


    private static int help = Integer.MIN_VALUE;

    public static boolean isBST(TreeNode root) {
        if (root == null) return true;

        if (!isBST(root.left)) return false;

        if (root.val <= help) return false;

        help = root.val;

        return isBST(root.right);
    }

    public static void main(String[] args) {
        int minNum = Integer.MIN_VALUE;
        int maxNum = Integer.MAX_VALUE;

        TreeNode head = new TreeNode(20);
        head.left = new TreeNode(8);
        head.right = new TreeNode(30);
        head.right.left = new TreeNode(18);
        head.right.right = new TreeNode(35);

        System.out.println(isBST(head, minNum, maxNum));
        System.out.println(isBST(head));

    }
}



