package code.LinkedList;

/**
 * @since 2020/10/9 11:33
 * @Created by Jevis_Hoo
 * @Description 删除链表的中间节点和 a/b 处的节点
 */
public class RemoveMidNode {

    public static Node removeMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.next.next == null) {
            return head.next;
        }

        Node pre = head;
        Node cur = head.next.next;
        while (cur.next != null && cur.next.next != null) {
            pre = pre.next;
            cur = cur.next.next;
        }
        pre.next = pre.next.next;
        return head;
    }

    public static Node removeByRatio(Node head, int a, int b) {
        if (a < 1 || a > b) {
            return head;
        }
        int n = 0;
        Node cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }

        int r = (int) Math.ceil(((double) (a * n)) / (double) b);

        if (r == 1) {
            head = head.next;
        }

        if (r > 1) {
            cur = head;
            while (--r != 1) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Node root = new Node(2);
        root.next = new Node(6);
        root.next.next = new Node(3);
        root.next.next.next = new Node(4);
        root.next.next.next.next = new Node(5);
        root.next.next.next.next.next = new Node(6);

        root = removeByRatio(root, 3, 9);

        while (root != null) {
            System.out.println(root.value);
            root = root.next;
        }
    }
}
