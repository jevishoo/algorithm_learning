package code.LinkedList;

import java.util.HashMap;

/**
 * @Date 2020/11/9 9:53
 * @Created by Jevis_Hoo
 * @Description Remove Zero Sum Consecutive Nodes from Linked List
 */
public class RemoveZeroSumSublists {
    public static Node removeZeroSumSublistsWithMap(Node head) {
        HashMap<Integer, Node> map = new HashMap<>();
        Node dummy = new Node();
        dummy.next = head;

        int sum = 0;
        map.put(sum, dummy);
        while (head != null) {
            sum += head.value;
            if (map.containsKey(sum)) {
                Node pre = map.get(sum);
                Node copy = pre.next;
                int x = sum;
                while (copy != head) {
                    x += copy.value;
                    map.remove(x);
                    copy = copy.next;
                }
                pre.next = head.next;
            } else {
                map.put(sum, head);
            }
            head = head.next;
        }
        return dummy.next;
    }

    public static Node removeZeroSumSublists(Node head) {
        if (head == null)
            return null;

        int sum = 0;
        Node cur = head;
        while (cur != null) {
            sum += cur.value;
            cur = cur.next;
            if (sum == 0)
                return removeZeroSumSublists(cur);
        }

        return new Node(head.value, removeZeroSumSublists(head.next));
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.next = new Node(-1);
        root.next.next = new Node(2);
        root.next.next.next = new Node(-2);
        root.next.next.next.next = new Node(-3);
        root.next.next.next.next.next = new Node(5);

        Node node = removeZeroSumSublistsWithMap(root);
//        Node node = removeZeroSumSublists(root);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }
}

