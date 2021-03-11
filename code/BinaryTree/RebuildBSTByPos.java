package code.BinaryTree;

import code.StackQueue.InorderTraversal;
import code.StackQueue.PostorderTraversal;
import code.StackQueue.PreorderTraversal;

/**
 * @author Jevis Hoo
 * @Date 2020/12/8 7:59
 * @Description 根据后序数组重建搜索二叉树
 */
public class RebuildBSTByPos {
    public static boolean isPosArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        return isPost(arr, 0, arr.length - 1);
    }

    private static boolean isPost(int[] arr, int start, int end) {
        if (start == end) {
            return true;
        }

        int less = -1;
        int more = end;
        for (int i = start; i < end; i++) {
            if (arr[i] < arr[end]) {
                less = i;
            } else {
                more = more == end ? i : more;
            }
        }

        if (less == -1 || more == end) {
            return isPost(arr, start, end - 1);
        }
        if (less != more - 1) {
            return false;
        }

        return isPost(arr, start, less) && isPost(arr, more, end - 1);
    }

    public static TreeNode posToBST(int[] arr) {
        if (!isPosArray(arr)) {
            return null;
        }

        return rebuiltBST(arr, 0, arr.length - 1);
    }

    public static TreeNode rebuiltBST(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode head = new TreeNode(arr[end]);

        int less = -1;
        int more = end;

        for (int i = start; i < end; i++) {
            if (arr[end] > arr[i]) {
                // 比头小的数的最后一个
                less = i;
            } else {
                // 比头大的数的第一个
                more = more == end ? i : more;
            }
        }

        head.left = rebuiltBST(arr, start, less);
        head.right = rebuiltBST(arr, more, end - 1);

        return head;
    }

    public static void main(String[] args) {
        int[] array = new int[]{2, 1, 3, 6, 5, 7, 4};
//        int[] array = new int[]{1, 3, 2, 5, 7, 6, 4};
//        int[] array = new int[]{3, 2};

        System.out.println(isPosArray(array));

        TreeNode root = posToBST(array);
        System.out.println(PreorderTraversal.preorderTraversal(root));
        System.out.println(InorderTraversal.inorderTraversal(root));
        System.out.println(PostorderTraversal.postorderTraversal(root));
    }
}
