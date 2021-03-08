package code.LinkedList;

/**
 * @author Jevis Hoo
 * @Date 2020/10/27 8:28
 * @Description Delete Duplicates from Sorted List II
 * <p>
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * <p>
 * Return the linked list sorted as well.
 */
public class DeleteDuplicates {
    /**
     * List Solution Easily understand
     */
    public static Node deleteDuplicates(Node head) {
        Node cur = head;
        Node pre = null;
        int repeat = Integer.MIN_VALUE;
        while (cur != null && cur.next != null) {
            if (cur.value == cur.next.value) {
                repeat = cur.value;
                if (pre == null) {
                    head = cur.next.next;
                } else {
                    pre.next = cur.next.next;
                }
                cur = cur.next.next;
            } else if (cur.value == repeat) {
                if (pre == null) {
                    head = cur.next;
                } else {
                    pre.next = cur.next;
                }
                cur = cur.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }

        if (cur != null && cur.value == repeat) {
            if (pre != null) {
                pre.next = null;
            } else {
                head = null;
            }
        }
        return head;
    }

    /*
    Recursion Solution Need Review
     */
    public static Node deleteDuplicatesRecursion(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        if (head.value == head.next.value) {
            return deleteDuplicatesRecursion(getNextNotEqualsToMe(head).next);
        }
        head.next = deleteDuplicatesRecursion(head.next);

        return head;
    }

    public static Node getNextNotEqualsToMe(Node node) {
        while (node.next != null && node.value == node.next.value) {
            node = node.next;
        }
        return node;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.next = new Node(1);
        root.next.next = new Node(1);
        root.next.next.next = new Node(4);
        root.next.next.next.next = new Node(4);
        root.next.next.next.next.next = new Node(4);

        Node node = deleteDuplicatesRecursion(root);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }
}
