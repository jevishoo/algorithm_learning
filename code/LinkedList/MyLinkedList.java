package code.LinkedList;

/**
 * @since 2020/11/4 10:42
 * @Created by Jevis_Hoo
 * @Description Design Linked List
 */
public class MyLinkedList {
    public static class Node {
        int val;
        Node next;
        Node prev;

        public Node(int val) {
            this.val = val;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (checkIndex(index)) return -1;

        Node cur;
        if (index <= size / 2) {
            int i = 0;
            cur = head;
            while (i < index) {
                cur = cur.next;
                i++;
            }
        } else {
            int i = size - 1;
            cur = tail;
            while (index < i) {
                cur = cur.prev;
                i--;
            }
        }
        return cur.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        Node newNode = new Node(val);

        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        Node newNode = new Node(val);

        if (size == 0) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > size || index < 0) return;
        if (index == size) {
            addAtTail(val);
            return;
        }
        if (index == 0) {
            addAtHead(val);
            return;
        }

        Node cur;
        if (index <= size / 2) {
            int i = 0;
            cur = head;

            while (i + 1 < index) {
                cur = cur.next;
                i++;
            }
        } else {
            int i = size - 1;
            cur = tail;

            while (index <= i) {
                cur = cur.prev;
                i--;
            }
        }

        //insert
        Node next = cur.next;
        Node newNode = new Node(val);
        cur.next = newNode;
        newNode.next = next;
        newNode.prev = cur;
        next.prev = newNode;

        size++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (checkIndex(index)) return;
        if (index == 0) {
            deleteAtHead();
            return;
        }
        if (index == size - 1) {
            deleteAtTail();
            return;
        }

        Node cur;
        if (index <= size / 2) {
            int i = 0;
            cur = head;

            while (i + 1 < index) {
                cur = cur.next;
                i++;
            }
        } else {
            int i = size - 1;
            cur = tail;

            while (index <= i) {
                cur = cur.prev;
                i--;
            }
        }

        //delete
        Node delete = cur.next;
        cur.next = delete.next;
        delete.next.prev = cur;

        size--;
    }

    private void deleteAtHead() {
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node next = head.next;
            next.prev = null;
            head = next;
        }
        size--;
    }

    private void deleteAtTail() {
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node prev = tail.prev;
            prev.next = null;
            tail = prev;
        }
        size--;
    }

    private boolean checkIndex(int index) {
        return (index < 0 || index >= size);
    }

    public static void main(String[] args) {
        MyLinkedList obj = new MyLinkedList();
        obj.addAtHead(1);
        obj.addAtTail(3);
        obj.addAtIndex(1, 2);

        Node cur = obj.head;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
        System.out.println("===========");

        int param_1 = obj.get(1);
        System.out.println(param_1);
        System.out.println("===========");

        obj.deleteAtIndex(2);
        cur = obj.head;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}

