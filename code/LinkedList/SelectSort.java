package code.LinkedList;

/**
 * @Date 2020/10/21 9:08
 * @Created by Jevis_Hoo
 * @Description 单链表的选择排序
 * <p>
 * 额外空间复杂度为O(1)
 */
public class SelectSort {
    public static Node selectSort(Node head) {
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


    public static Node selectSort1(Node head) {
        Node tail = null; // 排序部分尾部
        Node cur = head; // 未排序部分头部
        Node smallPre = null; // 最小节点的前一个节点
        Node small = null; // 最小的节点
        while (cur != null) {
            small = cur;
            smallPre = getSmallestPreNode(cur);
            if (smallPre != null) {
                small = smallPre.next;
                smallPre.next = small.next;
            }
            cur = cur == small ? cur.next : cur;
            if (tail == null) {
                head = small;
            } else {
                tail.next = small;
            }
            tail = small;
        }
        return head;
    }

    private static Node getSmallestPreNode(Node head) {
        Node smallPre = null;
        Node small = head;
        Node pre = head;
        Node cur = head.next;
        while (cur != null) {
            if (cur.value < small.value) {
                smallPre = pre;
                small = cur;
            }
            pre = cur;
            cur = cur.next;
        }
        return smallPre;
    }

    public static void main(String[] args) {
        Node root = new Node(4);
        root.next = new Node(1);
        root.next.next = new Node(5);
        root.next.next.next = new Node(2);
        root.next.next.next.next = new Node(7);
        root.next.next.next.next.next = new Node(3);
        root.next.next.next.next.next.next = new Node(6);


        Node node = selectSort(root);
        System.out.println("======");
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }
}
