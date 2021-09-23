package code.LinkedList;

/**
 * @since 2020/10/11 10:40
 * @Created by Jevis_Hoo
 * @Description 反转部分单向链表
 */
public class ReversePartList {
    public static Node reversePart(Node head, int from, int to) {
        int len = 0;
        Node node1 = head;
        Node fPre = null;
        Node tPos = null;
        while (node1 != null) {
            len++;
            fPre = len == from - 1 ? node1 : fPre;
            tPos = len == to + 1 ? node1 : tPos;
            node1 = node1.next;
        }
        if (from > to || from < 1 || to > len) {
            return head;
        }
        node1 = fPre == null ? head : fPre.next;
        Node node2 = node1.next;
        node1.next = tPos;
        Node next;

        while (node2 != tPos) {
            next = node2.next;
            node2.next = node1;
            node1 = node2;
            node2 = next;
        }
        if (fPre != null) {
            fPre.next = node1;
            return head;
        }
        return node1;

    }

    public static void main(String[] args) {
        Node root = new Node(8);
        root.next = new Node(7);
        root.next.next = new Node(3);
        root.next.next.next = new Node(4);
        root.next.next.next.next = new Node(5);
        root.next.next.next.next.next = new Node(6);

        root = reversePart(root, 2, 4);

        while (root != null) {
            System.out.println(root.value);
            root = root.next;
        }
    }
}
