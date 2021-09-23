package code.LinkedList;

/**
 * @since 2020/11/3 8:39
 * @Created by Jevis_Hoo
 * @Description Flatten a Multilevel Doubly Linked List
 */
public class FlattenMultilevelList {
    public static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node flatten(Node head) {
        process(head);
        return head;
    }

    public static Node process(Node node) {
        if (node == null || (node.next == null && node.child == null))
            return node;
        Node next = node.next;
        if (node.child != null) {
            node.next = node.child;
            node.next.prev = node;
            node.child = null;

            Node last = process(node.next);
            if (next != null) {
                last.next = next;
                next.prev = last;
            } else return process(last);
        }
        return process(next);
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
//        Node node4 = new Node(4);
//        Node node5 = new Node(5);
//        Node node6 = new Node(6);
//        Node node7 = new Node(7);
//        Node node8 = new Node(8);
//        Node node9 = new Node(9);
//        Node node10 = new Node(10);
//        Node node11 = new Node(11);
//        Node node12 = new Node(12);

//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;
//        node7.next = node8;
//        node8.next = node9;
//        node9.next = node10;
//        node11.next = node12;

//        node2.prev = node1;
//        node3.prev = node2;
//        node4.prev = node3;
//        node5.prev = node4;
//        node6.prev = node5;
//        node8.prev = node7;
//        node9.prev = node8;
//        node10.prev = node9;
//        node12.prev = node11;

//        node3.child = node7;
//        node8.child = node11;
        node1.child = node2;
        node2.child = node3;

        Node node = flatten(node1);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}
