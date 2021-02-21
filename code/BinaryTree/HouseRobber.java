package code.BinaryTree;

import java.util.HashMap;

/**
 * @author Jevis Hoo
 * @date 2021/2/21 10:18
 * @description House Robber III
 * <p>
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * <p>
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 */
public class HouseRobber {
    static HashMap<TreeNode, Integer> map = new HashMap<>();

    public static int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (map.containsKey(root)) {
            return map.get(root);
        }
        int val = 0;
        if (root.left != null) {
            val += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            val += rob(root.right.left) + rob(root.right.right);
        }
        map.put(root, Math.max(root.value + val, rob(root.left) + rob(root.right)));

        return map.get(root);
    }

    static class ReturnType {
        int selected;
        int unselected;

        public ReturnType(int selected, int unselected) {
            this.selected = selected;
            this.unselected = unselected;
        }

    }

    public static int rob2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ReturnType res = process(root);
        return Math.max(res.selected, res.unselected);
    }

    public static ReturnType process(TreeNode root) {
        if (root == null) {
            return new ReturnType(0, 0);
        }

        ReturnType lData = process(root.left);
        ReturnType rData = process(root.right);

        int selected = root.value + lData.unselected + rData.unselected;
        int unselected = Math.max(lData.selected, lData.unselected) + Math.max(rData.selected, rData.unselected);

        return new ReturnType(selected, unselected);
    }

    public static int rob3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] res = process1(root);
        return Math.max(res[0], res[1]);
    }

    public static int[] process1(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        int[] lData = process1(root.left);
        int[] rData = process1(root.right);

        int selected = root.value + lData[1] + rData[1];
        int unselected = Math.max(lData[0], lData[1]) + Math.max(rData[0], rData[1]);

        return new int[]{selected, unselected};
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        root.left.right.right = new TreeNode(6);

        System.out.println(rob(root));
        System.out.println(rob2(root));
        System.out.println(rob3(root));
    }
}
