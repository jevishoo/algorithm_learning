package code.LinkedList;

import java.util.HashSet;

/**
 * @Date 2020/10/19 9:14
 * @Created by Jevis_Hoo
 * @Description 删除无序单链表中值重复出现的节点
 * <p>
 * 方法1：如果链表长度为N，时间复杂度达到 O(N)。
 * 方法2：额外空间复杂度为 O(1)。
 */
public class RemoveRepeat {

    /*
    时间复杂度达到 O(N)
     */
    public static void removeRepeat(Node head) {
        Node cur = head;
        HashSet<Integer> set = new HashSet<>();
        Node pre = null;
        while (cur != null) {
            if (set.contains(cur.value)) {
                pre.next = cur.next;
            } else {
                set.add(cur.value);
                pre = cur;
            }

            cur = cur.next;
        }
    }

    /*
    空间复杂度为 O(1)
     */
    public static void removeRepeat1(Node head) {
        Node cur = head;
        Node pre, next;
        while (cur != null) {
            pre = cur;
            next = cur.next;
            while (next != null) {
                if (cur.value == next.value) {
                    pre.next = next.next;
                } else {
                    pre = next;
                }
                next = next.next;
            }
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.next = new Node(2);
        root.next.next = new Node(3);
        root.next.next.next = new Node(3);
        root.next.next.next.next = new Node(4);
        root.next.next.next.next.next = new Node(4);
        root.next.next.next.next.next.next = new Node(2);
        root.next.next.next.next.next.next.next = new Node(1);
        root.next.next.next.next.next.next.next.next = new Node(1);

        removeRepeat(root);
        while (root != null) {
            System.out.println(root.value);
            root = root.next;
        }
    }
}
