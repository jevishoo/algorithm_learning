package code.LinkedList;

/**
 * @author Jevis Hoo
 * @date 2021/3/29 11:38
 * @description 从尾到头打印链表
 */
public class ReversePrint {
    public int[] reversePrint(Node head) {
        int length = 0;
        Node cur = head;
        while (cur != null) {
            cur = cur.next;
            length++;
        }
        int[] result = new int[length];
        while (head != null) {
            result[--length] = head.value;
            head = head.next;
        }

        return result;
    }

}
