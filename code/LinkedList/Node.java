package code.LinkedList;

/**
 * @Date 2020/10/8 13:24
 * @Created by Jevis_Hoo
 * @Description
 */
public class Node {
    public int value;
    public Node next;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }
}
