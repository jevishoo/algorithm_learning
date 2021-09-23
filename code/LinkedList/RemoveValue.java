package code.LinkedList;

import java.util.Stack;

/**
 * @since 2020/10/19 9:28
 * @Created by Jevis_Hoo
 * @Description 在单链表中删除指定值的节点
 */
public class RemoveValue {
    public static Node removeValue(Node head, int num) {
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }

        Node pre = null;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                assert pre != null;
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return head;
    }

    public static Node removeValue1(Node head, int num) {
        Stack<Node> stack = new Stack<Node>();
        while (head != null) {
            if (head.value != num) {
                stack.push(head);
            }
            head = head.next;
        }
        while (!stack.isEmpty()) {
            stack.peek().next = head;
            head = stack.pop();
        }

        return head;
    }

    public static void main(String[] args) {
        Node root = new Node(3);
        root.next = new Node(1);
        root.next.next = new Node(3);
        root.next.next.next = new Node(4);
        root.next.next.next.next = new Node(2);
        root.next.next.next.next.next = new Node(4);
        root.next.next.next.next.next.next = new Node(2);
        root.next.next.next.next.next.next.next = new Node(1);

        Node node = removeValue(root, 3);

        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }
}
