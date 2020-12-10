package code.BinaryTree;

import java.util.LinkedList;

/**
 * @Date 2020/9/18 21:00
 * @Created by Jevis_Hoo
 * @Description 完全二叉树的检查
 * <p>
 * 检测树是否为完全二叉树
 */

public class CBTCheck {
    private static boolean isCBT(TreeNode head) {
        if (head == null)
            return true;

        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(head);

        TreeNode left, right;
        boolean levelFlag = false;
        while (!list.isEmpty()) {
            head = list.pollFirst();
            left = head.left;
            right = head.right;

            if ((levelFlag && (left != null || right != null)) || (left == null && right != null)) {
                return false;
            }

            if (left != null) {
                list.addLast(left);
            }

            if (right != null) {
                list.addLast(right);
            } else {
                levelFlag = !levelFlag;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(20);
        head.left = new TreeNode(8);
        head.right = new TreeNode(30);
        head.left.left = new TreeNode(18);
        head.left.right = new TreeNode(35);

        System.out.println(isCBT(head));

    }
}



