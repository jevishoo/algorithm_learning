package code.LinkedList;

/**
 * @since 2020/10/23 14:41
 * @Created by Jevis_Hoo
 * @Description 按照左右半区的方式重新组合单链表
 * <p>
 * 给定一个单链表的头部节点 head，链表长度为 N，如果 N 为偶数，那么前 N/2 个节点算作左半区，
 * 后 N/2 个节点算作右半区；如果 N 为奇数，那么前 N/2 个节点算作左半区，后 N/2+1 个节点算作右半区。
 * 左半区从左到右依次记为 L1->L2->…，右半区从左到右依次记为 R1->R2->…，
 * 请将单链表调整成L1->R1->L2->R2->…的形式。
 */
public class RelocateList {
    public static void relocate(Node head) {
        if (head == null || head.next == null)
            return;

        Node cur = head;
        Node mid = head;

        while (cur != null && cur.next != null) {
            mid = mid.next;
            cur = cur.next.next;
        }

        cur = head.next;
        Node result = head;
        Node right = mid;

        while (cur != mid) {
            result.next = right;
            right = right.next;

            result.next.next = cur;
            result = result.next.next;
            cur = cur.next;
        }

        while (right != null) {
            result.next = right;
            result = result.next;
            right = right.next;
        }
    }

    public static void relocateList(Node head) {
        if (head == null || head.next == null) {
            return;
        }
        Node mid = head;
        Node right = head.next;
        while (right.next != null && right.next.next != null) {
            mid = mid.next;
            right = right.next.next;
        }
        right = mid.next;
        mid.next = null;

        Node next, left = head;

        while (left.next != null) {
            next = right.next;
            right.next = left.next;
            left.next = right;
            left = right.next;
            right = next;
        }
        left.next = right;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.next = new Node(2);
        root.next.next = new Node(3);
        root.next.next.next = new Node(4);
//        root.next.next.next.next = new Node(5);

        relocate(root);
//        relocateList(root);
        while (root != null) {
            System.out.println(root.value);
            root = root.next;
        }
    }
}
