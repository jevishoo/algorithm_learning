package code.LinkedList;

/**
 * @since 2020/10/11 9:41
 * @Created by Jevis_Hoo
 * @Description 反转单向和双向链表
 */
public class ReverseList {
    public static Node reverseList(Node head) {
        Node pre = null;
        Node next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        head = pre;
        return head;
    }

    public static DoubleNode reverseList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        head = pre;
        return head;
    }

    public static void main(String[] args) {
        Node root = new Node(3);
        root.next = new Node(6);
        root.next.next = new Node(3);
        root.next.next.next = new Node(4);
        root.next.next.next.next = new Node(5);
        root.next.next.next.next.next = new Node(6);

        root = reverseList(root);

        while (root != null) {
            System.out.println(root.value);
            root = root.next;
        }

        DoubleNode node = new DoubleNode(1);
        //构建一个双向链表1 2 3 4
        DoubleNode mid1 = new DoubleNode(2);
        DoubleNode mid2 = new DoubleNode(3);
        DoubleNode tail = new DoubleNode(4);
        node.next = mid1;
        mid1.next = mid2;
        mid2.next = tail;

        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }

        node = new DoubleNode(1);
        //构建一个双向链表1 2 3 4
        mid1 = new DoubleNode(2);
        mid2 = new DoubleNode(3);
        tail = new DoubleNode(4);
        node.next = mid1;
        mid1.next = mid2;
        mid2.next = tail;
        node = reverseList(node);
        while (node != null) {
//            System.out.println(node.value);
            if (node.last != null) {
                System.out.println("====");
                System.out.println("pre: " + node.last.value);
            }
            node = node.next;
        }
    }
}
