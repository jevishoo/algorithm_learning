package code.LinkedList;

import java.util.HashMap;

/**
 * @since 2020/10/15 9:38
 * @Created by Jevis_Hoo
 * @Description 复制含有随机指针节点的链表
 */
public class CopyListWithRand {
    public static class RandNode {
        public int value;
        public RandNode next;
        public RandNode rand;

        public RandNode(int data) {
            this.value = data;
        }
    }

    public static RandNode copyListWithRand(RandNode head) {
        RandNode cur = head;
        RandNode next;

        while (cur != null) {
            next = cur.next;
            cur.next = new RandNode(cur.value);
            cur.next.next = next;
            cur = next;
        }

        cur = head;
        RandNode curCopy;

        // 设置复制节点的rand 指针
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }

        RandNode res = head.next;
        cur = head;

        // 拆分
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }

    public static RandNode copyListWithRand1(RandNode head) {
        HashMap<RandNode, RandNode> map = new HashMap<>();
        RandNode cur = head;
        while (cur != null) {
            map.put(cur, new RandNode(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static void main(String[] args) {
        RandNode node1 = new RandNode(1);
        RandNode node2 = new RandNode(2);
        RandNode node3 = new RandNode(3);

        node1.next = node2;
        node2.next = node3;
        node3.next = null;

        node1.rand = node3;
        node3.rand = node2;
        node2.rand = null;

        RandNode root = copyListWithRand1(node1);
        while (root != null) {
            System.out.println(root.value);
            System.out.println(root.rand != null ? root.rand.value : "null");
            System.out.println("====rand====");
            root = root.rand;
        }
    }
}
