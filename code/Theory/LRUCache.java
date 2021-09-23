package code.Theory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jevis Hoo
 * @since 2021/4/30 10:19
 * @description 实现一个 LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * <p>
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字 - 值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 */
public class LRUCache {

    private static void printNode(Node head) {
        head = head.next;
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
        System.out.println("===");
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);

//        printNode(cache.head);
        // 返回  1
        System.out.println(cache.get(1));

        // 该操作会使得密钥 2 作废
        cache.put(3, 3);
        // 返回 -1 (未找到)
        System.out.println(cache.get(2));
        // 该操作会使得密钥 1 作废
        cache.put(4, 4);
        // 返回 -1 (未找到)
        System.out.println(cache.get(1));
        // 返回  3
        System.out.println(cache.get(3));
        // 返回  4
        System.out.println(cache.get(4));

    }

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head;
    private final Node tail;


    public LRUCache(int capacity) {

        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node();
        this.tail = new Node();

        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);
        node.pre.next = node.next;
        node.next.pre = node.pre;

        moveHead(node);

        return node.value;
    }

    private void moveHead(Node node) {
        node.next = head.next;
        node.next.pre = node;
        node.pre = head;
        head.next = node;
    }

    public void put(int key, int value) {
        // 如果能 get 到该值，则更新(get 操作已经将其移动到头部)
        if (get(key) != -1) {
            map.get(key).value = value;
            return;
        }

        // Map中无此key
        Node node = new Node(key, value);
        map.put(key, node);
        moveHead(node);

        if (map.size() > this.capacity) {
            map.remove(tail.pre.key);
            tail.pre = tail.pre.pre;
            tail.pre.next = tail;
        }
    }
}

class Node {
    int key;
    int value;
    Node next;
    Node pre;

    public Node() {
    }

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
