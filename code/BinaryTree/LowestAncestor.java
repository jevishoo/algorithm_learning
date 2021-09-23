package code.BinaryTree;

import code.StackQueue.PostorderTraversal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * @since 2020/12/14 11:23
 * @Created by Jevis_Hoo
 * @Description 在二叉树中找到两个节点的最近公共祖先
 */
public class LowestAncestor {
    public static TreeNode getLowestAncestor(TreeNode head, TreeNode head1, TreeNode head2) {
        if (head == null || head == head1 || head == head2) {
            return head;
        }

        TreeNode left = getLowestAncestor(head.left, head1, head2);
        TreeNode right = getLowestAncestor(head.right, head1, head2);

        if (left != null && right != null) {
            return head;
        }
        return left != null ? left : right;
    }


    static class Record {
        public HashMap<TreeNode, TreeNode> map = new HashMap<>();

        public Record(TreeNode head) {
            setMap(head);
        }

        private void setMap(TreeNode head) {
            this.map.put(head, null);
            LinkedList<TreeNode> list = new LinkedList<>();
            list.add(head);

            while (!list.isEmpty()) {
                TreeNode cur = list.pollFirst();

                if (cur.left != null) {
                    this.map.put(cur.left, cur);
                    list.addLast(cur.left);
                }
                if (cur.right != null) {
                    this.map.put(cur.right, cur);
                    list.addLast(cur.right);
                }
            }
        }

    }

    public static TreeNode getLowestAncestorByRecord(Record record, TreeNode head1, TreeNode head2) {
        HashSet<TreeNode> set = new HashSet<>();

        while (record.map.containsKey(head1)) {
            set.add(head1);
            head1 = record.map.get(head1);
        }

        while (!set.contains(head2)) {
            head2 = record.map.get(head2);
        }
        return head2;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node7.left = node8;

        System.out.println(PostorderTraversal.postorderTraversal(node1));
        System.out.println(node6.value + " next is " + getLowestAncestor(node1, node6, node8).value);

        Record record = new Record(node1);
        System.out.println(node6.value + " next is " + getLowestAncestorByRecord(record, node6, node8).value);
    }
}
