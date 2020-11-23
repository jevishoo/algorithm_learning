package code.BinaryTree;

import code.StackQueue.InorderTraversal;

import java.util.Stack;

/**
 * @Date 2020/11/22 13:45
 * @Created by Jevis_Hoo
 * @Description 调整搜索二叉树中两个错误的节点 在结构上完全交换两个节点的位置
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

    public static TreeNode exchangeTree(TreeNode root) {
        TreeNode[] errors = getTwoErrNodes(root);
        TreeNode[] parents = getTwoErrParents(root, errors[0], errors[1]);

        TreeNode lcTmp, rcTmp;
        if (parents[0] == null) {
            lcTmp = errors[1].left;
            rcTmp = errors[1].right;
            errors[1].left = errors[0].left;
            errors[1].right = errors[0].right;
            if (parents[1].left == errors[1]) {
                parents[1].left = errors[0];
            } else {
                parents[1].right = errors[0];
            }
            errors[0].left = lcTmp;
            errors[0].right = rcTmp;
            return errors[1];
        } else if (parents[1] == errors[0]) { //父子元素交换
            lcTmp = errors[1].left;
            rcTmp = errors[1].right;
            if (parents[0].left == errors[0]) {
                parents[0].left = errors[1];
            } else {
                parents[0].right = errors[1];
            }

            errors[1].left = errors[0].left;
            errors[1].right = errors[0];

            errors[0].left = lcTmp;
            errors[0].right = rcTmp;
        } else if (parents[0] == errors[1]) { //子父元素交换
            lcTmp = errors[0].left;
            rcTmp = errors[0].right;
            if (parents[1].left == errors[1]) {
                parents[1].left = errors[0];
            } else {
                parents[1].right = errors[0];
            }

            errors[0].left = errors[1];
            errors[0].right = errors[1].right;

            errors[1].left = lcTmp;
            errors[1].right = rcTmp;
        } else {
            if (parents[0].left == errors[0]) {
                parents[0].left = errors[1];
            } else {
                parents[0].right = errors[1];
            }
            if (parents[1].left == errors[1]) {
                parents[1].left = errors[0];
            } else {
                parents[1].right = errors[0];
            }

            lcTmp = errors[1].left;
            rcTmp = errors[1].right;
            errors[1].left = errors[0].left;
            errors[1].right = errors[0].right;
            errors[0].left = lcTmp;
            errors[0].right = rcTmp;
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        root.right = new TreeNode(6);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(7);

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
