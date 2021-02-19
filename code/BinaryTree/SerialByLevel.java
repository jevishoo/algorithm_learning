package code.BinaryTree;

import code.StackQueue.PreorderTraversal;

import java.util.LinkedList;

/**
 * @author Jevis Hoo
 * @Date 2020/11/12 11:13
 * @Description 通过层遍历实现序列化和反序列化
 */
public class SerialByLevel {
    public static String serialByLevel(TreeNode head) {
        if (head == null) {
            return "#,";
        }

        StringBuilder res = new StringBuilder(head.value + ",");
        LinkedList<TreeNode> list = new LinkedList<>();
        list.addLast(head);
        while (!list.isEmpty()) {
            TreeNode node = list.pollFirst();
            if (node.left != null) {
                res.append(node.left.value).append(",");
                list.addLast(node.left);
            } else {
                res.append("#,");
            }

            if (node.right != null) {
                res.append(node.right.value).append(",");
                list.addLast(node.right);
            } else {
                res.append("#,");
            }
        }

        return res.toString();
    }


    public static TreeNode reconByLevelString(String str) {
        String[] nodes = str.split(",");
        LinkedList<TreeNode> list = new LinkedList<>();

        TreeNode head = new TreeNode(Integer.parseInt(nodes[0]));
        int index = 1;
        list.addLast(head);
        TreeNode node;
        while (!list.isEmpty()) {
            node = list.pollFirst();
            node.left = "#".equals(nodes[index]) ? null : new TreeNode(Integer.parseInt(nodes[index]));
            index++;
            node.right = "#".equals(nodes[index]) ? null : new TreeNode(Integer.parseInt(nodes[index]));
            index++;
            if (node.left != null) {
                list.add(node.left);
            }
            if (node.right != null) {
                list.add(node.right);
            }
        }
        return head;
    }

    public static String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder res = new StringBuilder();
        LinkedList<TreeNode> list = new LinkedList<>();
        list.addLast(root);
        while (!list.isEmpty()) {
            TreeNode node = list.pollFirst();
            if (node == null) {
                res.append("null,");
            } else {
                res.append(node.value).append(",");
                list.addLast(node.left);
                list.addLast(node.right);
            }
        }

        return res.toString();
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.right.right.right = new TreeNode(8);

        String res = serialByLevel(root);
        res = res.substring(0, res.length() - 1);
        System.out.println(res);// 1,2,3,4,#,5,6,#,#,7,#,#,8,#,#,#,#,
        System.out.println(serialize(root));

        TreeNode node = reconByLevelString(res);
        PreorderTraversal.preOrderRecur(node);
    }
}
