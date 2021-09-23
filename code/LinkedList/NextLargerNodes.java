package code.LinkedList;

import java.util.*;

/**
 * @since 2020/11/8 16:06
 * @Created by Jevis_Hoo
 * @Description Next Greater Node In Linked List
 */
public class NextLargerNodes {
    private static class Map {
        int index;
        int value;

        Map(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public static int[] nextLargerNodes(Node head) {
        if (head == null)
            return new int[0];

        Stack<Map> stack = new Stack<>();
        Node cur = head;
        int length = 0;
        while (cur != null) {
            length++;
            cur = cur.next;
        }

        int[] r = new int[length];
        int index = 0;

        while (head != null) {
            while (!stack.isEmpty() && stack.peek().value < head.value) {
                r[stack.pop().index] = head.value;
            }
            stack.push(new Map(index++, head.value));
            head = head.next;

        }
        return r;
    }

    public static int[] nextLargerNodes1(Node head) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();
        while (head != null) {
            list.add(head.value);
            head = head.next;
        }

        int[] r = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            while (!stack.isEmpty() && list.get(stack.peek()) < list.get(i)) {
                r[stack.pop()] = list.get(i);
            }
            stack.push(i);
        }
        return r;
    }

    public static void main(String[] args) {
        Node root = new Node(2);
        root.next = new Node(7);
        root.next.next = new Node(4);
        root.next.next.next = new Node(3);
        root.next.next.next.next = new Node(5);

        int[] res = nextLargerNodes(root);
        System.out.println(Arrays.toString(res));
    }
}
