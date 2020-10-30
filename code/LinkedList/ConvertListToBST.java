package code.LinkedList;

/**
 * @Date 2020/10/30 14:40
 * @Created by Jevis_Hoo
 * @Description 将排好序的链表转换成搜索二叉树
 */
public class ConvertListToBST {
    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    public static TreeNode convert(Node head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return new TreeNode(head.value);
        }

        return getRoot(head, null);
    }

    /*
    两个链表指针
     */
    public static TreeNode getRoot(Node head, Node tail) {
        if (head == tail) {
            return null;
        }
        if (head.next == tail ) {
            return new TreeNode(head.value);
        }

        Node slow = head;
        Node fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }

        TreeNode node;
        node = new TreeNode(slow.value);

        node.left = getRoot(head, slow);
        node.right = getRoot(slow.next, tail);
        return node;
    }

    /*
    三个链表指针
     */
    public static TreeNode getRoot(Node head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return new TreeNode(head.value);
        }

        Node pre = null;
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) { // 查找中间节点
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        Node next = slow.next;
        if (pre != slow)
            pre.next = null;

        TreeNode node = new TreeNode(slow.value);
        node.left = getRoot(head);
        node.right = getRoot(next);

        return node;
    }


    public static void inOrderToQueue(TreeNode head) {
        if (head == null)
            return;

        inOrderToQueue(head.left);
        System.out.println(head.value);
        inOrderToQueue(head.right);
    }

    public static void main(String[] args) {
        Node root = new Node(-10);
        root.next = new Node(-3);
        root.next.next = new Node(0);
        root.next.next.next = new Node(5);
//        root.next.next.next.next = new Node(9);

        TreeNode node = convert(root);
        inOrderToQueue(node);
    }
}
