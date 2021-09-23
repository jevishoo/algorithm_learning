package code.LinkedList;

/**
 * @since 2020/10/31 10:56
 * @Created by Jevis_Hoo
 * @Description Reorder List
 * <p>
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 */
public class ReorderList {
    public static void reorderList(Node head) {
        if (head == null || head.next == null)
            return;

        Node cur = head;
        Node mid = head;

        while (cur != null && cur.next != null) {
            cur = cur.next.next;
            mid = mid.next;
        }
        Node right = mid.next;
        mid.next = null;

        if (right == null) {
            return;
        }

        cur = right.next;
        right.next = null;
        Node tmp;
        while (cur != null) {
            tmp = cur.next;
            cur.next = right;
            right = cur;
            cur = tmp;
        }

        cur = head;
        while (right != null) {
            tmp = right.next;
            right.next = cur.next;
            cur.next = right;
            cur = right.next;
            right = tmp;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.next = new Node(2);
//        root.next.next = new Node(3);
//        root.next.next.next = new Node(4);

        reorderList(root);
        while (root != null) {
            System.out.println(root.value);
            root = root.next;
        }
    }
}
