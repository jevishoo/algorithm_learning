package code.BinaryTree;

import java.util.LinkedList;

/**
 * @Date 2020/11/14 9:44
 * @Created by Jevis_Hoo
 * @Description Morris遍历
 */
public class MorrisTraversal {
    /*
    来到的当前节点即为cur
    1. cur无左孩子，cur向右移动（cur = cur.right）
    2. cur有左孩子，找到cur左子树上最后得节点，记为 mostRight
       >>>如果 mostRight的 right指针指向空，让其指向 cur,
          cur向左移动（cur = cur.left）
       >>>如果 mostRight的 right指针指向 cur，让其指向空,
          cur向右移动（cur = cur.right）
     */

    public static void morrisTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight;
        while (cur != null) {
            System.out.print(cur.value + " ");
            mostRight = cur.left;
            if (mostRight != null) {
                mostRight = getMostRight(cur, mostRight);
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    cur = cur.right;
                }
            } else {
                cur = cur.right;
            }
        }
    }


    public static TreeNode getMostRight(TreeNode cur, TreeNode mostRight) {
        // 返回左子树最右的节点
        while (mostRight.right != null && mostRight.right != cur) {
            mostRight = mostRight.right;
        }
        return mostRight;
    }


    public static void morrisPre(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                mostRight = getMostRight(cur, mostRight);
                if (mostRight.right == null) {
                    System.out.print(cur.value + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    cur = cur.right;
                }
            } else {
                System.out.print(cur.value + " ");
                cur = cur.right;
            }
        }
    }

    public static void morrisIn(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                mostRight = getMostRight(cur, mostRight);
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    System.out.print(cur.value + " ");
                    mostRight.right = null;
                    cur = cur.right;
                }
            } else {
                System.out.print(cur.value + " ");
                cur = cur.right;
            }
        }
    }

    public static void morrisPos(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                mostRight = getMostRight(cur, mostRight);
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    printEdge(cur.left);
                    cur = cur.right;
                }
            } else {
                cur = cur.right;
            }
        }
        printEdge(root);
    }


    public static void printEdge(TreeNode head) {
        TreeNode tail = reverseEdge(head);
        TreeNode cur = tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public static TreeNode reverseEdge(TreeNode from) {
        TreeNode pre = null;
        TreeNode next;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }

    public static void morrisRevPos(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<Integer> list = new LinkedList<>();
        TreeNode cur = root;
        TreeNode mostLeft;
        while (cur != null) {
            mostLeft = cur.right;
            if (mostLeft != null) {
                while (mostLeft.left != null && mostLeft.left != cur) {
                    mostLeft = mostLeft.left;
                }
                if (mostLeft.left == null) {
                    list.add(cur.value);
                    mostLeft.left = cur;
                    cur = cur.right;
                } else {
                    mostLeft.left = null;
                    cur = cur.left;
                }
            } else {
                list.add(cur.value);
                cur = cur.left;
            }
        }

        while (!list.isEmpty()) {
            System.out.print(list.pollLast() + " ");
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        root.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        morrisTraversal(root);
        System.out.println();
        morrisPre(root);
        System.out.println();
        morrisIn(root);
        System.out.println();
        morrisPos(root);
        System.out.println();
        morrisRevPos(root);
    }
}