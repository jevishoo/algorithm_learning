package code.BinaryTree;

import code.StackQueue.InorderTraversal;

import java.util.Stack;

/**
 * @author Jevis Hoo
 * @since 2020/11/22 13:45
 * @description 调整搜索二叉树中两个错误的节点 在结构上完全交换两个节点的位置
 */
public class TwoErrorNodes {
    public static TreeNode[] getTwoErrNodes(TreeNode head) {
        TreeNode[] errs = new TreeNode[2];
        if (head == null) {
            return errs;
        }
        TreeNode pre = null;
        TreeNode mostRight;
        while (head != null) {
            mostRight = head.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != head) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = head;
                    head = head.left;
                } else {
                    if (pre != null && pre.value > head.value) {
                        errs[0] = errs[0] == null ? pre : errs[0];
                        errs[1] = head;
                    }
//                    System.out.print(head.value + " ");
                    mostRight.right = null;
                    pre = head;
                    head = head.right;
                }
            } else {
                if (pre != null && pre.value > head.value) {
                    errs[0] = errs[0] == null ? pre : errs[0];
                    errs[1] = head;
                }
                pre = head;
//                System.out.print(head.value + " ");
                head = head.right;
            }
        }
//        System.out.println();
        return errs;
    }

    public static TreeNode[] getTwoErrParents(TreeNode head, TreeNode e1, TreeNode e2) {
        TreeNode[] parents = new TreeNode[2];
        if (head == null) {
            return parents;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (head.left == e1 || head.right == e1) {
                    parents[0] = head;
                }
                if (head.left == e2 || head.right == e2) {
                    parents[1] = head;
                }
                head = head.right;
            }
        }
        return parents;
    }

    public static TreeNode exchangeTree(TreeNode head) {
        TreeNode[] errors = getTwoErrNodes(head);
        TreeNode[] parents = getTwoErrParents(head, errors[0], errors[1]);

        TreeNode e1 = errors[0];
        TreeNode e1P = parents[0];
        TreeNode e1L = e1.left;
        TreeNode e1R = e1.right;
        TreeNode e2 = errors[1];
        TreeNode e2P = parents[1];
        TreeNode e2L = e2.left;
        TreeNode e2R = e2.right;

        if (e1 == head) {
            if (e1 == e2P) { // 情况1
                e1.left = e2L;
                e1.right = e2R;
                e2.right = e1;
                e2.left = e1L;
            } else if (e2P.left == e2) { // 情况2
                e2P.left = e1;
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2L;
                e1.right = e2R;
            } else { // 情况3
                e2P.right = e1;
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2L;
                e1.right = e2R;
            }
            head = e2;
        } else if (e2 == head) {
            if (e2 == e1P) { // 情况4
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2;
                e1.right = e2R;
            } else if (e1P.left == e1) { // 情况5
                e1P.left = e2;
                e1.left = e2L;
                e1.right = e2R;
                e2.left = e1L;
                e2.right = e1R;
            } else { // 情况6
                e1P.right = e2;
                e1.left = e2L;
                e1.right = e2R;
                e2.left = e1L;
                e2.right = e1R;
            }
            head = e1;
        } else {
            if (e1 == e2P) {
                if (e1P.left == e1) { // 情况7
                    e1P.left = e2;
                    e1.left = e2L;
                    e1.right = e2R;
                    e2.left = e1L;
                    e2.right = e1;
                } else { // 情况8
                    e1P.right = e2;
                    e1.left = e2L;
                    e1.right = e2R;
                    e2.left = e1L;
                    e2.right = e1;
                }
            } else if (e2 == e1P) {
                if (e2P.left == e2) { // 情况9
                    e2P.left = e1;
                    e2.left = e1L;
                    e2.right = e1R;
                    e1.left = e2;
                    e1.right = e2R;
                } else { // 情况10
                    e2P.right = e1;
                    e2.left = e1L;
                    e2.right = e1R;
                    e1.left = e2;
                    e1.right = e2R;
                }
            } else {
                if (e1P.left == e1) {
                    if (e2P.left == e2) { // 情况11
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.left = e2;
                        e2P.left = e1;
                    } else { // 情况12
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.left = e2;
                        e2P.right = e1;
                    }
                } else {
                    if (e2P.left == e2) { // 情况13
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.right = e2;
                        e2P.left = e1;
                    } else { // 情况14
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.right = e2;
                        e2P.right = e1;
                    }
                }
            }
        }
        return head;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
//        root.left.right = new TreeNode(3);
//
//        root.right = new TreeNode(6);
//        root.right.left = new TreeNode(2);
//        root.right.right = new TreeNode(7);

        System.out.println("Error");
        TreeNode[] errNodes = getTwoErrNodes(root);
        for (TreeNode node : errNodes) {
            System.out.println(node.value);
        }
        System.out.println("Parent");
        TreeNode[] parNodes = getTwoErrParents(root, errNodes[0], errNodes[1]);
        for (TreeNode node : parNodes) {
            System.out.println(node == null ? null : node.value);
        }

        System.out.println(InorderTraversal.inorderTraversal(root));
        TreeNode head = exchangeTree(root);
        System.out.println(InorderTraversal.inorderTraversal(head));
    }
}
