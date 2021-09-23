package code.BinaryTree;

import java.util.LinkedList;

/**
 * @author Jevis Hoo
 * @since 2021/2/2 11:18
 * @description Minimum Depth of Binary Tree
 * <p>
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * <p>
 * Note: A leaf is a node with no children.
 */
public class MinDepth {

    public static int minDepth(TreeNode root) {
        int lowHeight = getHeight(root);
        System.out.println("lowHeight:" + lowHeight);
        return lowHeight;
    }

    private static int getHeight(TreeNode root) {
        int height = 1;
        LinkedList<TreeNode> list = new LinkedList<>();
        list.addLast(root);
        TreeNode last = root;
        TreeNode cur, lastTmp = null;

        while (!list.isEmpty()) {
            cur = list.pollFirst();

            if (cur.left == null && cur.right == null) {
                break;
            }

            if (cur.left != null) {
                list.addLast(cur.left);
                lastTmp = cur.left;
            }
            if (cur.right != null) {
                list.addLast(cur.right);
                lastTmp = cur.right;
            }

            if (cur == last) {
                height++;
                last = lastTmp;
            }
        }
        return height;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(0);
        root.right = new TreeNode(9);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        System.out.println(minDepth(root));

        TreeNode head = new TreeNode(1);
        head.right = new TreeNode(2);
        head.right.right = new TreeNode(3);
        head.right.right.right = new TreeNode(4);
        head.right.right.right.right = new TreeNode(5);
        System.out.println(minDepth(head));

    }
}
