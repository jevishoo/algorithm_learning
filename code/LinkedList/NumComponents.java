package code.LinkedList;

import java.util.HashSet;

/**
 * @Date 2020/11/6 10:00
 * @Created by Jevis_Hoo
 * @Description Linked List Components
 *
 * 找出共存在几组相邻元素
 */
public class NumComponents {
    public static int numComponents(Node head, int[] G) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : G) {
            set.add(num);
        }

        Node cur = head;
        int result = 0;
        while (cur != null) {
            /*
            87%
             */
//            if (set.contains(cur.value)) {
//                result++;
//                while (cur.next != null && set.contains(cur.value)) {
//                    cur = cur.next;
//                }
//            }
            /*
            95%
             */
            boolean flag = false;
            while (cur != null && set.contains(cur.value)) {
                if (!flag) {
                    result++;
                    flag = true;
                }
                cur = cur.next;
            }
            if (cur != null)
                cur = cur.next;
        }

        return result;
    }

    public static void main(String[] args) {
        Node root = new Node(0);
        root.next = new Node(1);
        root.next.next = new Node(2);
        root.next.next.next = new Node(3);
        root.next.next.next.next = new Node(4);
        int[] G = new int[]{0, 4, 1, 3};

        System.out.println(numComponents(root, G));
    }
}
