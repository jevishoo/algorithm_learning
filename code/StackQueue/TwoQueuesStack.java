package code.StackQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date 2020/9/12 13:44
 * @Created by Jevis_Hoo
 * @Description
 */
public class TwoQueuesStack {
    private Queue<Integer> q1;
    private Queue<Integer> q2;

    /** Initialize your data structure here. */
    public TwoQueuesStack() {
        this.q1 = new LinkedList<>();
        this.q2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        this.q1.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if (this.q1.isEmpty()) {
            return -1;
        }

        while(this.q1.size() != 1) {
            this.q2.add(this.q1.remove());
        }

        int temp = this.q1.remove();
        Queue<Integer> q = this.q1;
        this.q1 = this.q2;
        this.q2 = q;

        return temp;
    }

    /** Get the top element. */
    public int top() {
        if (this.q1.isEmpty()) {
            return -1;
        }

        while(this.q1.size() != 1) {
            this.q2.add(this.q1.remove());
        }

        int temp = this.q1.remove();
        this.q2.add(temp);

        Queue<Integer> q = this.q1;
        this.q1 = this.q2;
        this.q2 = q;

        return temp;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return this.q1.isEmpty();
    }

    public static void main(String[] args) {
        TwoQueuesStack queue = new TwoQueuesStack();

        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);

        System.out.println(queue.top());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.empty());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.empty());
    }
}
