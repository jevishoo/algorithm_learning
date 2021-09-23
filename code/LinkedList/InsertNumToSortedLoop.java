package code.LinkedList;

/**
 * @since 2020/10/22 10:05
 * @Created by Jevis_Hoo
 * @Description 向有序的环形单链表中插入新节点
 */
public class InsertNumToSortedLoop {
    public static Node insertNum(Node head, int num) {
        Node node = new Node(num);

        if (head == null) {
            node.next = node;
            return node;
        }

        Node cur = head.next;
        Node pre = head;
        while (cur != head) {
            if (pre.value <= num && cur.value > num) {
                break;
            }
            pre = cur;
            cur = cur.next;
        }

        pre.next = node;
        node.next = cur;

        return head.value < num ? head : node; // 注意 最后要加上num比head的value小的情况，头结点发生变化
    }
}
