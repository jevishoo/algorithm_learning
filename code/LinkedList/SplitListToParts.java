package code.LinkedList;

/**
 * @Date 2020/11/5 10:34
 * @Created by Jevis_Hoo
 * @Description Split Linked List in Parts
 * <p>
 * <p>
 * The length of each part should be as equal as possible: no two parts should have a size differing by more than 1.
 * This may lead to some parts being null.
 * <p>
 * The parts should be in order of occurrence in the input list,
 * and parts occurring earlier should always have a size greater
 * than or equal parts occurring later.
 */
public class SplitListToParts {
    public static Node[] splitListToParts(Node root, int k) {
        Node[] nodes = new Node[k];

        int length = 0;
        Node cur = root;
        while (cur != null) {
            length++;
            cur = cur.next;
        }

        cur = root;
        for (int i = 0; i < k; i++) {
            int len = length / k;
            if (i < length % k) {
                len++;
            }
            Node tmp = null;
            while (cur != null && len-- != 0) {
                if (nodes[i] == null)
                    nodes[i] = cur;
                tmp = cur;
                cur = cur.next;
            }
            if (tmp != null)
                tmp.next = null;
        }

        return nodes;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.next = new Node(2);
        root.next.next = new Node(3);
        root.next.next.next = new Node(4);
        root.next.next.next.next = new Node(5);
        root.next.next.next.next.next = new Node(6);
        root.next.next.next.next.next.next = new Node(7);
        root.next.next.next.next.next.next.next = new Node(8);
        root.next.next.next.next.next.next.next.next = new Node(9);
        root.next.next.next.next.next.next.next.next.next = new Node(10);
//        root.next.next.next.next.next.next.next.next.next.next = new Node(11);
        Node[] nodes = splitListToParts(root, 3);
        for (Node node : nodes) {
            System.out.println("===");
            while (node != null) {
                System.out.println(node.value);
                node = node.next;
            }
        }
    }
}
