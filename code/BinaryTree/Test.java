package code.BinaryTree;

import java.util.HashMap;

/**
 * @author Jevis Hoo
 * @date 2021/3/29 12:10
 * @description
 */
public class Test {

    private static HashMap<Integer, Integer> map;
    private static int position = 0;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return buildTree(preorder, 0, inorder.length - 1);
    }

    public static TreeNode buildTree(int[] preorder, int begin, int end) {
        if (begin > end) {
            return null;
        }

        TreeNode head = new TreeNode(preorder[position++]);

        System.out.println(head.value);

        head.left = buildTree(preorder, begin, map.get(head.value) - 1);
        head.right = buildTree(preorder, map.get(head.value) + 1, end);

        return head;
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        buildTree(preorder, inorder);
    }
}
