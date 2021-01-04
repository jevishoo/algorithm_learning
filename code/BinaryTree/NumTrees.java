package code.BinaryTree;

import code.StackQueue.PreorderTraversal;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jevis_Hoo
 * @date 2020/12/25 14:52
 * @description 统计和生成所有不同的二叉树
 * <p>
 * 给定一个整数 N，如果 N<1，代表空树结构，否则代表中序遍历的结果为 {1,2,3,…，N}。
 * 返回可能的二叉树结构有多少
 */
public class NumTrees {
    public static int getTreesNum(int n) {
        int minNodes = 2;
        if (n < minNodes) {
            return 1;
        }

        int[] nums = new int[n + 1];

        nums[0] = 1;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                nums[i] += nums[j - 1] * nums[i - j];
            }
        }

        return nums[n];
    }

    /**
     * 进阶：N 的含义不变，假设可能的二叉树结构有 M 种，请返回 M 个二叉树的头节点，
     * 每一棵二叉树代表一种可能的结构。
     */
    public static List<TreeNode> generateTrees(int N) {
        return generate(1, N);
    }

    private static List<TreeNode> generate(int start, int end) {
        List<TreeNode> res = new LinkedList<>();
        if (start > end) {
            res.add(null);
        }

        TreeNode head;
        for (int i = start; i < end + 1; i++) {
            head = new TreeNode(i);
            List<TreeNode> lNodeList = generate(start, i - 1);
            List<TreeNode> rNodeList = generate(i + 1, end);

            for (TreeNode lNode : lNodeList) {
                for (TreeNode rNode : rNodeList) {
                    head.left = lNode;
                    head.right = rNode;
                    res.add(head);
                }
            }
        }

        return res;
    }


    public static void main(String[] args) {
        int n = 3;
        System.out.println(getTreesNum(n));
        for (TreeNode head : generateTrees(n)) {
            System.out.println(PreorderTraversal.preorderTraversal(head));
        }
    }

}
