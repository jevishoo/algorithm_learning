package code.LinkedList;

/**
 * @since 2020/11/1 15:57
 * @Created by Jevis_Hoo
 * @Description Sort List
 * <p>
 * Using O(n log(n)) time  and O(1) memory
 */
public class SortList {
    public static Node quickSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        //Define less list, equal list, more list
        Node less = new Node(0);
        Node more = new Node(0);

        Node l = less;
        Node p = head;
        Node m = more;

        //tranverse list using cur pointer
        Node cur = head.next;

        while (cur != null) {

            if (cur.value < head.value) {
                l.next = cur;
                l = l.next;
            } else if (cur.value == head.value) {
                p.next = cur;
                p = p.next;
            } else {
                m.next = cur;
                m = m.next;
            }

            cur = cur.next;
        }

        //recursively sort left and right part of list
        //less -> null, pivot -> null, more -> null
        p.next = null;
        l.next = null;
        m.next = null;

        Node lessSorted = quickSort(less.next);
        Node moreSorted = quickSort(more.next);

        //append less, pivot, more together to get result
        if (lessSorted != null) {     //less sorted is not null, less->pivot->more->null
            l = lessSorted;
            while (l.next != null) {
                l = l.next;
            }

            l.next = head;
            p.next = moreSorted;
            return lessSorted;

        } else { //pivot -> more -> null

            p.next = moreSorted;
            return head;
        }
    }

    public static Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node puunch = head;
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            puunch = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        puunch.next = null;
        Node left = mergeSort(head);
        Node right = mergeSort(slow);

        return merge(left, right);
    }

    public static Node merge(Node left, Node right) {
        Node mergeNode = new Node(0);
        Node cur = mergeNode;

        while (left != null && right != null) {
            if (left.value < right.value) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }

            cur = cur.next;
        }

        if (left != null) {
            cur.next = left;
        } else cur.next = right;

        return mergeNode.next;
    }

    public static void main(String[] args) {
        Node root = new Node(4);
        root.next = new Node(1);
        root.next.next = new Node(5);
        root.next.next.next = new Node(2);
        root.next.next.next.next = new Node(7);
        root.next.next.next.next.next = new Node(3);
        root.next.next.next.next.next.next = new Node(6);


        Node node = mergeSort(root);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }
}
