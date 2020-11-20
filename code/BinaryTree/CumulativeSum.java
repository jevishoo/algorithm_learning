package code.BinaryTree;

import java.util.HashMap;

/**
 * @Date 2020/11/20 9:50
 * @Created by Jevis_Hoo
 * @Description 在二叉树中找到累加和为指定值的最长路径长度
 */
public class CumulativeSum {
    public static int getMaxLength(TreeNode root, int sum) {
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, 0); // 重要
        return preOrder(root, sum, 0, 1, 0, sumMap);
    }

    public static int preOrder(TreeNode head, int sum, int preSum, int height, int maxLength, HashMap<Integer, Integer> sumMap) {
        if (head == null)
            return maxLength;

        int curSum = preSum + head.value;
        sumMap.putIfAbsent(curSum, height);

        if (sumMap.containsKey(curSum - sum))
            maxLength = Math.max(maxLength, height - sumMap.get(curSum - sum));

        maxLength = preOrder(head.left, sum, curSum, height + 1, maxLength, sumMap);
        maxLength = preOrder(head.right, sum, curSum, height + 1, maxLength, sumMap);

        if (height == sumMap.get(curSum))
            sumMap.remove(curSum);

        return maxLength;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-3);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(0);
        root.left.right.left = new TreeNode(1);
        root.left.right.right = new TreeNode(6);

        root.right = new TreeNode(0);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(9);

        System.out.println(getMaxLength(root, 6));
    }
}
