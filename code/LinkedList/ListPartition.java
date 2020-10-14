package code.LinkedList;

/**
 * @Date 2020/10/14 9:15
 * @Created by Jevis_Hoo
 * @Description 将单向链表按某值划分成左边小、中间相等、右边大的形式
 * <p>
 * 进阶：
 * 在原问题的要求之上再增加如下两个要求。
 * >>> 在左、中、右三个部分的内部也做顺序要求，要求每部分里的节点从左到右的顺序与原链表中节点的先后次序一致。
 * >>> 如果链表长度为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)。
 */
public class ListPartition {

    public static Node listPartition(Node head, int pivot) {
        Node smallHead = null;
        Node smallTail = null;

        Node equalHead = null;
        Node equalTail = null;

        Node bigHead = null;
        Node bigTail = null;

        Node next;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (smallHead == null) {
                    smallHead = head;
                } else {
                    smallTail.next = head;
                }
                smallTail = head;
            } else if (head.value == pivot) {
                if (equalHead == null) {
                    equalHead = head;
                } else {
                    equalTail.next = head;
                }
                equalTail = head;
            } else {
                if (bigHead == null) {
                    bigHead = head;
                } else {
                    bigTail.next = head;
                }
                bigTail = head;
            }
            head = next;
        }

        if (smallTail != null) {
            smallTail.next = equalHead;
            if (equalTail == null)
                equalTail = smallTail;
        }

        if (equalTail != null) {
            equalTail.next = bigHead;
        }

        return smallHead != null ? smallHead : equalHead != null ? equalHead : bigHead;
    }

    public static Node listPartitionUsingQuickSort(Node head, int pivot) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        cur = head;
        for (i = 0; i != nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr, pivot);
        for (i = 1; i != nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    public static void arrPartition(Node[] nodeArr, int pivot) {
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index != big) {
            if (nodeArr[index].value < pivot) {
                swap(nodeArr, ++small, index++);
            } else if (nodeArr[index].value == pivot) {
                index++;
            } else {
                swap(nodeArr, --big, index);
            }
        }
    }

    public static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }


    public static void main(String[] args) {
        Node root = new Node(2);
        root.next = new Node(4);
        root.next.next = new Node(7);
        root.next.next.next = new Node(1);
        root.next.next.next.next = new Node(5);

        Node head = listPartitionUsingQuickSort(root, 3);
//        Node head = listPartition(root, 3);

        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }
}
