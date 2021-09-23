package code.BinaryTree;

import code.StackQueue.InorderTraversal;

/**
 * @since 2020/12/12 15:18
 * @Created by Jevis_Hoo
 * @Description 通过有序数组生成平衡搜索二叉树
 */
public class GenerateBST {
    public static TreeNode generateTree(int[] sortArr) {
        if (sortArr == null) {
            return null;
        }
        return generate(sortArr, 0, sortArr.length - 1);
    }

    public static TreeNode generate(int[] sortArr, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + ((end - start) >> 1);

        TreeNode head = new TreeNode(sortArr[mid]);
        head.left = generate(sortArr, start, mid - 1);
        head.right = generate(sortArr, mid + 1, end);

        return head;
    }

    public static void main(String[] args) {
        int[] sortedArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        System.out.println(InorderTraversal.inorderTraversal(generateTree(sortedArray)));
    }
}
