package code.LinkedList;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date 2020/10/20 8:07
 * @Created by Jevis_Hoo
 * @Description 将搜索二叉树转换成双向链表
 * <p>
 * 如何实现 时间复杂度为O(N)，额外空间复杂度为O(h)，h 为二叉树的高度的方法？
 */
public class ConvertBSTToList {
    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    /*
    时间复杂度为O(N)，额外空间复杂度为O(N)
     */
    public static DoubleNode convert(TreeNode head) {
        Queue<DoubleNode> queue = new LinkedList<>();

        inOrderToQueue(head, queue);

        if (queue.isEmpty())
            return null;

        DoubleNode root = queue.poll();
        DoubleNode pre = root;
        pre.last = null;
        DoubleNode cur;

        while (!queue.isEmpty()) {
            cur = queue.poll();
            pre.next = cur;
            cur.last = pre;
            pre = cur;
        }
        return root;
    }

    public static void inOrderToQueue(TreeNode head, Queue<DoubleNode> queue) {
        if (head == null)
            return;

        inOrderToQueue(head.left, queue);
        queue.offer(new DoubleNode(head.value));
        inOrderToQueue(head.right, queue);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(4);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(1);
        root.left.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);
        root.right.right.left = new TreeNode(8);

        DoubleNode node = convert(root);
        DoubleNode pre = null;
        while (node != null) {
            System.out.println(node.value);
            pre = node;
            node = node.next;
        }

        while (pre != null) {
            System.out.println(pre.value);
            pre = pre.last;
        }
    }
}
