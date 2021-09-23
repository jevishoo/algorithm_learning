package code.LinkedList;

import java.util.Stack;

/**
 * @since 2020/10/16 14:45
 * @Created by Jevis_Hoo
 * @Description 两个单链表生成相加链表
 */
public class AddLists {
    public static Node addLists(Node head1, Node head2) {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        Node cur = head1;
        while (cur != null) {
            stack1.push(cur);
            cur = cur.next;
        }

        cur = head2;
        while (cur != null) {
            stack2.push(cur);
            cur = cur.next;
        }

        Node result = null;
        int ca = 0;

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int n1 = stack1.isEmpty() ? 0 : stack1.pop().value;
            int n2 = stack2.isEmpty() ? 0 : stack2.pop().value;

            int n = n1 + n2 + ca;
            cur = result;
            result = new Node(n % 10);
            result.next = cur;
            ca = n / 10;
        }

        if (ca == 1) {
            cur = result;
            result = new Node(ca);
            result.next = cur;
        }
        return result;
    }

    public static Node addLists1(Node head1, Node head2) {
        head1 = ReverseList.reverseList(head1);
        head2 = ReverseList.reverseList(head2);

        Node result = null;
        int ca = 0;

        Node cur1 = head1;
        Node cur2 = head2;
        Node tmp;
        while (cur1 != null || cur2 != null) {
            int n1 = cur1 == null ? 0 : cur1.value;
            int n2 = cur2 == null ? 0 : cur2.value;

            int n = n1 + n2 + ca;
            tmp = result;
            result = new Node(n % 10);
            result.next = tmp;

            ca = n / 10;
            cur1 = cur1 == null ? null : cur1.next;
            cur2 = cur2 == null ? null : cur2.next;
        }

        if (ca == 1) {
            tmp = result;
            result = new Node(ca);
            result.next = tmp;
        }

        ReverseList.reverseList(head1);
        ReverseList.reverseList(head2);
        return result;
    }

    /*
    leetcode 2 addTwoNumbers
     */
    public Node addTwoNumbers(Node l1, Node l2) {
        int add = 0;
        Node result = null;
        Node cur = null;
        while (l1 != null || l2 != null) {
            int n1 = l1 == null ? 0 : l1.value;
            int n2 = l2 == null ? 0 : l2.value;

            int n = n1 + n2 + add;

            if (result == null) {
                result = new Node(n % 10);
                cur = result;
            } else {
                cur.next = new Node(n % 10);
                cur = cur.next;
            }

            add = n / 10;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        if (add == 1) {
            cur.next = new Node(1);
        }

        return result;
    }

    public static void main(String[] args) {
        Node node1 = new Node(9);
        node1.next = new Node(3);
        node1.next.next = new Node(7);

        Node node2 = new Node(6);
        node2.next = new Node(3);

        Node sum = addLists1(node1, node2);
        while (sum != null) {
            System.out.println(sum.value);
            sum = sum.next;
        }
    }

}
