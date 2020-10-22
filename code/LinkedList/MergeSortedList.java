package code.LinkedList;

/**
 * @Date 2020/10/22 10:25
 * @Created by Jevis_Hoo
 * @Description
 */
public class MergeSortedList {

    public static Node merge(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return head1 != null ? head1 : head2;
        }
        Node head = head1.value < head2.value ? head1 : head2; //head为头元素小的链表的头
        Node cur1 = head == head1 ? head1 : head2; // cur1为头元素小的链表
        Node cur2 = head == head1 ? head2 : head1; // cur2为头元素大的链表
        Node pre = cur1;
//        cur1 = cur1.next;
        Node next;

        while (cur1 != null && cur2 != null) {
            if (cur1.value <= cur2.value) { // 首次进循环 必然进入此判断
                pre = cur1;
                cur1 = cur1.next;
            } else {
                next = cur2.next;
                pre.next = cur2;
                cur2.next = cur1;
                pre = cur2;
                cur2 = next;
            }
        }
        pre.next = cur1 == null ? cur2 : cur1;
        return head;
    }
}
