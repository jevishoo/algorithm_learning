package code.LinkedList;

/**
 * @since 2020/10/26 9:28
 * @Created by Jevis_Hoo
 * @Description
 */
public class SwapPairs {
    public static Node swapPairs(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node cur = head;
        Node swap = head.next, next, pre = null;

        head = swap;
        while (true) {
            next = swap.next;
            swap.next = cur;
            if (pre != null) {
                pre.next = swap;
            }
            pre = cur;
            cur.next = next;

            if (next == null || next.next == null) {
                break;
            } else {
                cur = next;
                swap = next.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.next = new Node(2);
        root.next.next = new Node(3);
        root.next.next.next = new Node(4);

        Node node = swapPairs(root);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }
}
