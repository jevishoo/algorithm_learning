package code.LinkedList;

/**
 * @Date 2020/11/3 8:04
 * @Created by Jevis_Hoo
 * @Description Odd Even Linked List
 * <p>
 * Given a singly linked list, group all odd nodes together followed by the even nodes.
 * Please note here we are talking about the node number and not the value in the nodes.
 */
public class OddEvenList {
    public static Node oddEvenList(Node head) {
        if (head == null || head.next == null)
            return head;

        Node odd = head;
        Node eHead = head.next;
        Node even;

        while (odd.next != null && odd.next.next != null) {
            even = odd.next;
            odd.next = even.next;
            even.next = odd.next.next;
            odd = odd.next;
        }

        odd.next = eHead;
        return head;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.next = new Node(2);
//        root.next.next = new Node(3);
//        root.next.next.next = new Node(4);
//        root.next.next.next.next = new Node(5);
//        root.next.next.next.next.next = new Node(6);

        Node node = oddEvenList(root);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }
}
