package code.LinkedList;

/**
 * @since 2020/11/1 11:04
 * @Created by Jevis_Hoo
 * @Description
 */
public class InsertionSort {
    public static Node insertSort(Node head) {
        Node dummy = new Node(0);
        dummy.next = head;

        Node temp, prev, cur = head;

        while (cur != null && cur.next != null) {

            if (cur.value <= cur.next.value) {
                cur = cur.next;
            } else {
                temp = cur.next;
                cur.next = cur.next.next;
                prev = dummy;
                while (prev.next.value <= temp.value) {
                    prev = prev.next;
                }

                temp.next = prev.next;
                prev.next = temp;
            }
        }

        return dummy.next;
    }


    public static Node insertSort1(Node head) {
        Node result = null;

        Node cur = head;
        while (cur != null) {
            Node tmp = result;

            if (tmp == null) {
                result = new Node(cur.value);
            } else {
                Node small = null;
                while (tmp != null && tmp.value < cur.value) {
                    small = tmp;
                    tmp = tmp.next;
                }

                if (small != null) {
                    small.next = new Node(cur.value);
                    small.next.next = tmp;
                } else {
                    Node pre = new Node(cur.value);
                    pre.next = result;
                    result = pre;
                }
            }
            cur = cur.next;
        }
        return result;
    }
}
