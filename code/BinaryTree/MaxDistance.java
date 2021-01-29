package code.BinaryTree;

/**
 * @Date 2020/12/16 15:04
 * @Created by Jevis_Hoo
 * @Description 二叉树节点间的最大距离问题
 */
public class MaxDistance {
    public static class ReturnType {
        int height;
        int maxDistance;

        public ReturnType(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }

    public static int getMaxDistance(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return process(root).maxDistance;
    }

    private static ReturnType process(TreeNode root) {
        if (root == null) {
            return new ReturnType(0, 0);
        }
        ReturnType leftData = process(root.left);
        ReturnType rightData = process(root.right);

        int lr = leftData.height + rightData.height + 1;
        int max;
        if (lr > leftData.maxDistance && lr > rightData.maxDistance) {
            max = lr;
        } else {
            max = Math.max(leftData.maxDistance, rightData.maxDistance);
        }

        return new ReturnType(Math.max(leftData.height, rightData.height) + 1, max);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node5.right = node8;

        System.out.println(getMaxDistance(node1));

    }
}
