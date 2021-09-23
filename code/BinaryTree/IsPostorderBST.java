package code.BinaryTree;

import java.util.Stack;

/**
 * @author Jevis Hoo
 * @since 2021/5/7 16:06
 * @description 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果
 */
public class IsPostorderBST {
    public static boolean isPostorderBST(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > root) {
                return false;
            }
            while (!stack.isEmpty() && stack.peek() > postorder[i]) {
                root = stack.pop();
            }
            stack.add(postorder[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 6, 3, 2, 5};
        System.out.println(isPostorderBST(arr));

        arr = new int[]{1,3,2,6,5};
        System.out.println(isPostorderBST(arr));
    }
}
