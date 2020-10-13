package code.LinkedList;

import java.util.Stack;

/**
 * @Date 2020/10/13 8:27
 * @Created by Jevis_Hoo
 * @Description 判断一个链表是否为回文结构
 */
public class Palindrome {
    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Node right = head.next;
        Node cur = head;

        while (cur.next != null && cur.next.next != null) {
            right = right.next;
            cur = cur.next.next;
        }

        Stack<Node> stack = new Stack<>();
        while (right != null) {
            stack.push(right);
            right = right.next;
        }

        while (!stack.isEmpty()) {
            if (stack.pop().value != head.value) {
                return false;
            }
            head = head.next;
        }

        return true;
    }

    public static void main(String[] args) {
        Node root = new Node(5);
        root.next = new Node(1);
        root.next.next = new Node(3);
        root.next.next.next = new Node(1);
        root.next.next.next.next = new Node(5);

        System.out.println(isPalindrome(root));
    }
}
