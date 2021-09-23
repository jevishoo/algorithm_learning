package code.BinaryTree;

/**
 * @author Jevis Hoo
 * @since 2021/2/7 13:47
 * @description Binary Tree Maximum Path Sum
 * <p>
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence
 * has an edge connecting them. A node can only appear in the sequence at most once.
 * Note that the path does not need to pass through the root.
 * <p>
 * The path sum of a path is the sum of the node's values in the path.
 * <p>
 * Given the root of a binary tree, return the maximum path sum of any path.
 */
public class MaxPathSum {
    static class ReturnType {
        int maxNoRootSum;
        int grantedSum;
        int maxPathSum;

        public ReturnType(int maxNoRootSum, int grantedSum, int maxPathSum) {
            this.maxNoRootSum = maxNoRootSum;
            this.grantedSum = grantedSum;
            this.maxPathSum = maxPathSum;
        }
    }

    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        ReturnType res = process(root);
        return Math.max(res.maxPathSum, res.maxNoRootSum);
    }


    public static ReturnType process(TreeNode root) {
        if (root == null) {
            return new ReturnType(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        }

        ReturnType lData = process(root.left);
        ReturnType rData = process(root.right);

        int grantedPath = Math.max(Math.max(lData.grantedSum, rData.grantedSum), 0) + root.value;
        int maxPath = Math.max(lData.grantedSum, 0) + Math.max(rData.grantedSum, 0) + root.value;

        return new ReturnType(
                Math.max(Math.max(lData.maxPathSum, lData.maxNoRootSum),
                        Math.max(rData.maxPathSum, rData.maxNoRootSum)), grantedPath, maxPath
        );
    }

    static int maxSum = Integer.MIN_VALUE;

    public static int maxPathSum2(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.value;
        }

        getMaxPathSum(root);
        return maxSum;
    }

    public static int getMaxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int lData = getMaxPathSum(root.left);
        int rData = getMaxPathSum(root.right);

        int grantedPath = Math.max(Math.max(lData, rData), 0) + root.value;

        maxSum = Math.max(Math.max(maxSum, root.value), Math.max(lData + rData + root.value, Math.max(lData, rData) + root.value));
        return grantedPath;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(-9);
//        root.right = new TreeNode(20);
//        root.right.left = new TreeNode(15);
//        root.right.right = new TreeNode(7);


//        TreeNode root = new TreeNode(5);
//        root.left = new TreeNode(4);
//        root.right = new TreeNode(8);
//
//        root.left.left = new TreeNode(11);
//        root.left.left.left = new TreeNode(7);
//        root.left.left.right = new TreeNode(2);
//
//        root.right.left = new TreeNode(13);
//        root.right.right = new TreeNode(4);
//        root.right.right.left = new TreeNode(1);

        System.out.println(maxPathSum(root));
        System.out.println(maxPathSum2(root));
    }
}
