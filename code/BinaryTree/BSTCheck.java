package code.BinaryTree;

/**
 * @since 2020/9/18 21:00
 * @author Jevis Hoo
 * @description 二叉搜索树的检查
 * <p>
 * 检测树是否为二叉搜索树
 */

public class BSTCheck {
    public static boolean isBST(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        return root.value > min
                && root.value < max
                && isBST(root.left, min, root.value)
                && isBST(root.right, root.value, max);
    }


    private static int help = Integer.MIN_VALUE;

    public static boolean isBSTByRecursion(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isBSTByRecursion(root.left)) {
            return false;
        }
        if (root.value <= help) {
            return false;
        }
        help = root.value;

        return isBSTByRecursion(root.right);
    }


    private static boolean isBST(TreeNode head) {
        if (head == null) {
            return true;
        }
        boolean res = true;
        int pre = Integer.MIN_VALUE;
        TreeNode cur = head;
        TreeNode mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    if (cur.value <= pre) {
                        res = false;
                    }
                    pre = cur.value;
                    cur = cur.right;
                }
            } else {
                if (cur.value <= pre) {
                    res = false;
                }
                pre = cur.value;
                cur = cur.right;
            }
        }
        System.out.println();
        return res;
    }

    public static void main(String[] args) {
        int minNum = Integer.MIN_VALUE;
        int maxNum = Integer.MAX_VALUE;

        TreeNode head = new TreeNode(20);
        head.left = new TreeNode(8);
        head.right = new TreeNode(30);
        head.right.left = new TreeNode(18);
        head.right.right = new TreeNode(35);

        System.out.println(isBST(head, minNum, maxNum));
        System.out.println(isBSTByRecursion(head));
        System.out.println(isBST(head));

    }
}



