package code.LinkedList;

/**
 * @since 2020/10/27 8:05
 * @Created by Jevis_Hoo
 * @Description Rotate List
 * <p>
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 */
public class RotateRight {
    /*
    利用删除倒数第 K 个元素题思路
     */
    public static Node rotateRight(Node head, int k) {
        if (k == 0 || head == null)
            return head;

        Node cur = head;
        int length = 0;
        while (cur != null) {
            length++;
            cur = cur.next;
        }

        k = k % length;
        cur = head;
        Node tail = null;

        while (cur != null) {
            k--;
            tail = cur;
            cur = cur.next;
        }

        cur = head;
        while (++k != 0) {
            cur = cur.next;
        }

        tail.next = head;
        head = cur.next;
        cur.next = null;

        return head;
    }

    public static void main(String[] args) {
        Node root = new Node(0);
        root.next = new Node(1);
        root.next.next = new Node(2);

        Node node = rotateRight(root, 4);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }
}
