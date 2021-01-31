package code.BinaryTree;

import java.util.LinkedList;

/**
 * @author Jevis Hoo
 * @date 2020/11/22 12:56
 * @description 二叉树的按层打印与 ZigZag 打印
 */
public class LevelTraversal {
    public static void printByLevel(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        list.addLast(root);
        TreeNode last = root;
        TreeNode cur, lastTmp = null;
        int level = 1;
        System.out.print("Level " + level++ + ":");
        while (!list.isEmpty()) {
            cur = list.pollFirst();
            if (cur.left != null) {
                list.addLast(cur.left);
                lastTmp = cur.left;
            }
            if (cur.right != null) {
                list.addLast(cur.right);
                lastTmp = cur.right;
            }

            if (cur == last && !list.isEmpty()) {
                System.out.print(cur.value + " ");
                System.out.print("\nLevel " + level++ + ":");
                last = lastTmp;
            } else {
                System.out.print(cur.value + " ");
            }
        }
    }

    public static void printByZigzag(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        TreeNode last = root;
        TreeNode cur, lastTmp = null;
        int level = 1;
        boolean flag = true;

        list.add(root);
        System.out.print("Level " + level++ + " from left to right:");
        while (!list.isEmpty()) {
            if (flag) {
                cur = list.pollFirst();
                if (cur.left != null) {
                    list.addLast(cur.left);
                    lastTmp = cur.left;
                }
                if (cur.right != null) {
                    list.addLast(cur.right);
                }
            } else {
                cur = list.pollLast();
                if (cur.right != null) {
                    list.addFirst(cur.right);
                    lastTmp = cur.right;
                }
                if (cur.left != null) {
                    list.addFirst(cur.left);
                }
            }
            System.out.print(cur.value + " ");

            if (cur == last && !list.isEmpty()) {
                flag = !flag;
                System.out.print(flag ? "\nLevel " + level++ + " from left to right:" : "\nLevel " + level++ + " from right to left:");
                last = lastTmp;
            }

        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);

        root.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);

        System.out.println("Print By Level");
        printByLevel(root);
        System.out.println("\nPrint By Zigzag");
        printByZigzag(root);
    }
}
