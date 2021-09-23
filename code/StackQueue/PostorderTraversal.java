package code.StackQueue;

import code.BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @since 2020/9/14 8:56
 * @Created by Jevis_Hoo
 * @Description
 */
public class PostorderTraversal {
    public static void postOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        postOrderRecur(head.left);
        postOrderRecur(head.right);
        System.out.print(head.value + " ");
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> help = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                help.push(root.value);
                root = root.right;
            } else {
                root = stack.pop();
                root = root.left;
            }
        }

        while (!help.isEmpty()) {
            list.add(help.pop());
        }

        return list;
    }

    public static List<Integer> postorderTraversalWithSingleStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();

        stack.push(root);

        TreeNode c;
        while (!stack.isEmpty()) {
            c = stack.peek();

            if (c.left != null && c.left != root && c.right != root) {
                stack.push(c.left);
            } else if (c.right != null && c.right != root) {
                stack.push(c.right);
            } else {
                list.add(stack.pop().value);
                root = c;
            }
        }

        return list;
    }


    public static void main(String[] args) {
//        TreeNode head = new TreeNode(5);
//        head.left = new TreeNode(3);
//        head.right = new TreeNode(8);
//        head.left.left = new TreeNode(2);
//        head.left.right = new TreeNode(4);
//        head.left.left.left = new TreeNode(1);
//        head.right.left = new TreeNode(7);
//        head.right.left.left = new TreeNode(6);
//        head.right.right = new TreeNode(10);
//        head.right.right.left = new TreeNode(9);
//        head.right.right.right = new TreeNode(11);

        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(2);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(3);

        head.right = new TreeNode(6);
        head.right.left = new TreeNode(5);
        head.right.right = new TreeNode(7);


        postOrderRecur(head);
        System.out.println();
        List<Integer> lst = postorderTraversal(head);
        System.out.println(lst);
        List<Integer> list = postorderTraversalWithSingleStack(head);
        System.out.println(list);
    }

}
