package code.LinkedList;

/**
 * @since 2020/10/26 8:52
 * @Created by Jevis_Hoo
 * @Description Merge k Sorted Lists
 */
public class MergeKLists {
    public static Node merge(Node[] lists) {
        if (lists.length == 0) {
            return null;
        }

        Node head1 = lists[0];
        for (int i = 1; i < lists.length; i++) {
            Node head2 = lists[i];

            if (head1 == null || head2 == null) {
                head1 = head1 != null ? head1 : head2;
                continue;
            }

            Node combine = head1.value < head2.value ? head1 : head2; //head为头元素小的链表的头
            Node cur1 = combine == head1 ? head1 : head2; // cur1为头元素小的链表
            Node cur2 = combine == head1 ? head2 : head1; // cur2为头元素大的链表
            Node pre = cur1;
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
            pre.next = cur2 == null ? cur1 : cur2;

            head1 = combine;
        }
        return head1;
    }

    public static Node mergeKLists(Node[] lists) {
        int i, n = lists.length - 1;
        if (n == -1) {
            return null;
        }
        if (n == 0) {
            return lists[0];
        }
        while (n > 0) {
            i = 0;
            while (i < n - i) {
                lists[i] = MergeSortedList.merge(lists[i], lists[n - i]); // 关键
                i++;
            }
            n = n / 2;
        }
        return lists[0];
    }

    public static void main(String[] args) {
        Node root1 = new Node(2);
//        root1.next = new Node(4);
//        root1.next.next = new Node(5);

        Node root2 = null;
//        root2.next = new Node(3);
//        root2.next.next = new Node(4);

        Node root3 = new Node(-1);
//        root3.next = new Node(6);

        Node[] nodes = new Node[]{root1, root2, root3};
        Node node = mergeKLists(nodes);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }
}
