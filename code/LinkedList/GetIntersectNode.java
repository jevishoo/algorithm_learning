package code.LinkedList;

/**
 * @since 2020/10/17 10:30
 * @Created by Jevis_Hoo
 * @Description 两个单链表相交的一系列问题
 * <p>
 * 要求：
 * 如果两个链表相交，请返回相交的第一个节点；如果不相交，返回null 即可。
 * <p>
 * 如果链表1 的长度为N，链表2 的长度为M，时间复杂度请达到O(N+M)，额外空间复杂度请达到O(1)。
 * <p>
 * 问题一：如何判断一个链表是否有环，如果有，则返回第一个进入环的节点，没有则返回null。
 * 问题二：如何判断两个无环链表是否相交，相交则返回第一个相交节点，不相交则返回null。
 * 问题三：如何判断两个有环链表是否相交，相交则返回第一个相交节点，不相交则返回null。
 * 注意：如果一个链表有环，另外一个链表无环，它们是不可能相交的，直接返回null。
 */
public class GetIntersectNode {

    public static Node getLoopNode(Node head) {
        if (head.next == null || head.next.next == null) {
            return null;
        }

        Node slow, fast;
        slow = head.next;
        fast = head.next.next;

        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }

    public static Node getIntersectNodeWithNoLoop(Node head1, Node head2) {
        int len1 = 0, len2 = 0;

        Node cur1, cur2;
        cur1 = head1;
        cur2 = head2;

        while (cur1.next != null) {
            len1++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            len2++;
            cur2 = cur2.next;
        }

        if (cur1 != cur2) {
            return null;
        }

        cur1 = len1 > len2 ? head1 : head2; //长的
        cur2 = len1 > len2 ? head2 : head1;

        int len = Math.abs(len1 - len2);
        while (len != 0) {
            cur1 = cur1.next;
            len--;
        }

        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;
    }

    public static Node getIntersectNodeWithBothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1, cur2;
        if (loop1 != loop2) {
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop2;
                }
                cur1 = cur1.next;
            }
            return null;
        } else {
            cur1 = head1;
            cur2 = head2;
            int len1 = 0, len2 = 0;

            while (cur1.next != loop1) {
                len1++;
                cur1 = cur1.next;
            }
            while (cur2.next != loop1) {
                len2++;
                cur2 = cur2.next;
            }

            cur1 = len1 >= len2 ? head1 : head2;
            cur2 = len1 >= len2 ? head2 : head1;

            int len = Math.abs(len1 - len2);
            while (len != 0) {
                cur1 = cur1.next;
                len--;
            }

            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }

            return cur1;
        }
    }


    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);

        if (loop1 == null && loop2 == null) {
            return getIntersectNodeWithNoLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return getIntersectNodeWithBothLoop(head1, loop1, head2, loop2);
        }
        return null;

    }

    public static void main(String[] args) {

        /*
        test getLoopNode()
         */
//        Node node1 = new Node(1);
//        Node node2 = new Node(2);
//        Node node3 = new Node(3);
//        Node node4 = new Node(4);
//        Node node5 = new Node(5);
//        Node node6 = new Node(6);
//        Node node7 = new Node(7);
//        Node node8 = new Node(8);
//
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;
//        node6.next = node7;
//        node7.next = node8;
//        node8.next = node3;
//
//        Node result;
//        if (getLoopNode(node1) != null) {
//            result = getLoopNode(node1);
//            if (result != null) {
//                System.out.println(result.value);
//            }
//        } else System.out.println("null");

        /*
        test getIntersectNodeWithNoLoop()
         */
//        Node node1 = new Node(1);
//        Node node2 = new Node(3);
//        Node node3 = new Node(5);
//        Node node4 = new Node(7);
//        Node node5 = new Node(8);
//
//        Node node6 = new Node(2);
//        Node node7 = new Node(4);
//
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//
//        node6.next = node7;
//        node7.next = node4;
//
//        Node result;
//        if (getIntersectNode(node1, node6) != null) {
//            result = getIntersectNode(node1, node6);
//            if (result != null) {
//                System.out.println(result.value);
//            }
//        } else System.out.println("null");

        /*
        test getIntersectNodeWithBothLoop()
         */
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        Node node6 = new Node(6);
        Node node7 = new Node(7);

        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node6.next = node7;
        node7.next = node5;

        node5.next = node8;
        node8.next = node9;
        node9.next = node10;
        node10.next = node8;

        Node result;
        if (getIntersectNode(node1, node6) != null) {
            result = getIntersectNode(node1, node6);
            if (result != null) {
                System.out.println(result.value);
            }
        } else System.out.println("null");

    }
}
