package code.LinkedList;

/**
 * @since 2020/10/12 9:59
 * @Created by Jevis_Hoo
 * @Description 环形单链表的约瑟夫问题
 * <p>
 * 输入：一个环形单向链表的头节点head 和报数的值m。
 * 返回：最后生存下来的节点，且这个节点自己组成环形单向链表，其他节点都删掉。
 * 进阶问题：如果链表节点数为N，想在时间复杂度为O(N)时完成原问题的要求，该怎么实现？
 */
public class JosephusKill {
    public static Node josephusKill1(Node head, int m) {
        if (head == null || head.next == null || m < 1) {
            return head;
        }

        Node last = head;
        while (last.next != head) {
            last = last.next;
        }
        int count = 0;

        while (head != last) {
            if (++count == m) {
                last.next = head.next;
                count = 0;
            } else last = last.next;

            head = last.next;
        }
        return head;
    }


    public static Node josephusKill2(Node head, int m) {
        if (head == null || head.next == null || m < 1) {
            return head;
        }
        Node cur = head.next;
        int tmp = 1; // tmp -> list size
        while (cur != head) {
            tmp++;
            cur = cur.next;
        }
        tmp = getLive(tmp, m); // tmp -> service node position
        while (--tmp != 0) {
            head = head.next;
        }
        head.next = head;
        return head;
    }

    public static int getLive(int i, int m) {
        if (i == 1) {
            return 1;
        }
        return (getLive(i - 1, m) + m - 1) % i + 1;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.next = new Node(8);
        root.next.next = new Node(3);
        root.next.next.next = new Node(4);
        root.next.next.next.next = new Node(5);
        root.next.next.next.next.next = new Node(6);
        root.next.next.next.next.next.next = root;

        System.out.println(josephusKill1(root, 2).value);
//        System.out.println(josephusKill2(root, 2).value);
    }
}
