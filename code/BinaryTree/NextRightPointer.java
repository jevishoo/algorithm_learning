package code.BinaryTree;

import java.util.LinkedList;

/**
 * @author Jevis Hoo
 * @date 2021/2/5 11:43
 * @description Populating Next Right Pointers in Each Node
 * <p>
 * >>> 1
 * Populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set to NULL.
 * <p>
 * i) You may only use constant extra space.
 * ii) Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 */
public class NextRightPointer {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right, Node next) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }

    /**
     * perfect binary tree
     * O(N)
     *
     * @param root tree
     * @return tree
     */
    public static Node connect1(Node root) {
        if (root == null) {
            return null;
        }
        LinkedList<Node> list = new LinkedList<>();
        list.addLast(root);

        Node last = root, lastTmp = null, pre = null;
        while (!list.isEmpty()) {
            Node cur = list.pollFirst();
            if (cur.left != null) {
                list.addLast(cur.left);
                lastTmp = cur.left;
            }

            if (cur.right != null) {
                list.addLast(cur.right);
                lastTmp = cur.right;
            }

            if (pre != null) {
                pre.next = cur;
            }

            if (cur == last) {
                pre = null;
                last = lastTmp;
            } else {
                pre = cur;
            }
        }

        return root;
    }

    /**
     * perfect binary tree
     * O(h)
     *
     * @param root tree
     * @return tree
     */
    public static Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        populate(root.left, root.right);
        return root;
    }

    private static void populate(Node left, Node right) {
        if (left == null) {
            return;
        }
        left.next = right;
        populate(left.left, left.right);
        populate(left.right, right.left);
        populate(right.left, right.right);
    }


    /**
     * perfect binary tree
     * O(1)
     *
     * @param root tree
     * @return tree
     */
    public static Node connect3(Node root) {
        if (root == null) {
            return null;
        }

        Node leftmost = root;

        while (leftmost.left != null) {
            Node head = leftmost;

            while (head != null) {

                // CONNECTION 1
                head.left.next = head.right;
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            leftmost = leftmost.left;
        }

        return root;
    }

    /**
     * Not perfect binary tree
     * O(1)
     *
     * @param root tree
     * @return tree
     */
    public static Node connect4(Node root) {
        if (root == null) {
            return null;
        }

        Node cur = root, pre;
        Node dummy = new Node(0);
        while (cur != null) {
            pre = dummy;
            while (cur != null) {
                if (cur.left != null) {
                    pre.next = cur.left;
                    pre = pre.next;
                }

                if (cur.right != null) {
                    pre.next = cur.right;
                    pre = pre.next;
                }

                cur = cur.next;
            }
            cur = dummy.next;
            // important if don't exist, dummy.next only points to a node with no children.
            dummy.next = null;
        }

        return root;
    }


    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
//        Node node6 = new Node(6);
        Node node7 = new Node(7);


        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
//        node3.left = node6;
        node3.right = node7;

//        Node root = connect1(node1);
//        Node root = connect2(node1);
//        Node root = connect3(node1);
        Node root = connect4(node1);
        System.out.println("Next Right Pointer");

        System.out.println(node1.next == null ? null : node1.next.val);
        System.out.println(node2.next == null ? null : node2.next.val);
        System.out.println(node3.next == null ? null : node3.next.val);
        System.out.println(node4.next == null ? null : node4.next.val);
        System.out.println(node5.next == null ? null : node5.next.val);
//        System.out.println(node6.next == null ? null : node6.next.val);
        System.out.println(node7.next == null ? null : node7.next.val);

    }
}
