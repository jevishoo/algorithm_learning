package code.LinkedList;

/**
 * @since 2020/10/8 13:22
 * @Created by Jevis_Hoo
 * @Description 给定两个有序链表的头指针head1 和head2，打印两个链表的公共部分
 */
public class PrintCommonPart {
    public static void printCommonPart(Node head1, Node head2) {
        System.out.println("Common Part: ");

        while (head1 != null && head2 != null) {
            if (head1.value < head2.value) {
                head1 = head1.next;
            } else if (head1.value > head2.value) {
                head2 = head2.next;
            } else {
                System.out.println(head1.value + " ");
                head1 = head1.next;
                head2 = head2.next;
            }
        }
    }

    public static void main(String[] args) {
        Node root1 = new Node(1);
        root1.next = new Node(2);
        root1.next.next = new Node(3);
        root1.next.next.next = new Node(4);
        root1.next.next.next.next = new Node(5);
        root1.next.next.next.next.next = new Node(6);

        Node root2 = new Node(1);
        root2.next = new Node(3);
        root2.next.next = new Node(4);
        root2.next.next.next = new Node(7);
        root2.next.next.next.next = new Node(9);
        root2.next.next.next.next.next = new Node(10);

        printCommonPart(root1, root2);
    }
}

