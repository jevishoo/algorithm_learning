package code.BinaryTree;

/**
 * @author Jevis Hoo
 * @since 2020/11/27 14:37
 * @description
 */
public class IsBalanceTree {
    public static class ReturnType {
        public boolean isBalanced;
        public int height;

        public ReturnType(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static boolean isBalanceTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isBalanced;
    }

    private static ReturnType process(TreeNode root) {
        if (root == null) {
            return new ReturnType(true, 0);
        }
        ReturnType left = process(root.left);
        ReturnType right = process(root.right);

        int height = 1 + Math.max(left.height, right.height);
        boolean isBalanced = left.isBalanced && right.isBalanced && Math.abs(left.height - right.height) <= 1;

        return new ReturnType(isBalanced, height);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);

        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(5);
        root.right.right.left = new TreeNode(6);

        System.out.println(isBalanceTree(root));
        System.out.println(isBalanceTree(root.right));
    }
}
