package code.LinkedList;

/**
 * @Date 2020/11/6 9:58
 * @Created by Jevis_Hoo
 * @Description Middle of the Linked List
 */
public class MidOfList {
    public Node middleNode(Node head) {
        if (head == null || head.next == null)
            return head;

        Node mid = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            mid = mid.next;
            fast = fast.next.next;
        }

        return mid;
    }
}
