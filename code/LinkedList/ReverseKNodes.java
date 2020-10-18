package code.LinkedList;

/**
 * @Date 2020/10/18 10:32
 * @Created by Jevis_Hoo
 * @Description 将单链表的每K 个节点之间逆序
 */
public class ReverseKNodes {
    public static Node reverseKNodes(Node head, int K) {
        Node cur = head;
        Node start, pre = null, next;
        int len = 1;
        while (cur != null) {
            next = cur.next;
            if (len % K == 0) {
                start = pre == null ? head : pre.next;
                System.out.println("**" + start.value + "**");
                head = pre == null ? cur : head;
                reverse(pre, start, cur, next);
                pre = start;
            }

            len++;
            cur = next;
        }

        return head;
    }


    public static void reverse(Node left, Node start, Node end, Node right) {
        Node pre = start;
        Node cur = start.next;
        Node next;
        while (cur != right) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if (left != null) {
            left.next = end;
        }
        start.next = right;
    }
//    private static void reverse(Node pre, Node start, Node end, Node next) {
//        Node cur = start;
//        Node node, tmp = pre;
//        while (cur != next) {
//            node = cur.next;
//            cur.next = tmp;
//            tmp = cur;
//            cur = node;
//        }
//
//        if (pre != null)
//            pre.next = end;
//
//        start.next = next;
//    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.next = new Node(2);
        root.next.next = new Node(3);
        root.next.next.next = new Node(4);
        root.next.next.next.next = new Node(5);
        root.next.next.next.next.next = new Node(6);
        root.next.next.next.next.next.next = new Node(7);
        root.next.next.next.next.next.next.next = new Node(8);
        root.next.next.next.next.next.next.next.next = new Node(9);
        root.next.next.next.next.next.next.next.next.next = new Node(10);
        root.next.next.next.next.next.next.next.next.next.next = new Node(11);
        root.next.next.next.next.next.next.next.next.next.next.next = new Node(12);

        Node cur = reverseKNodes(root, 4);
        while (cur != null) {
            System.out.println(cur.value);
            cur = cur.next;
        }

    }
}
