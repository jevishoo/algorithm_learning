package code.Stack_Queue;

import java.util.Stack;

/**
 * @Date 2020/8/13 16:18
 * @Created by Jevis_Hoo
 * @Description 编写一个类，用两个栈实现队列，支持队列的基本操作（add、poll、peek）。
 */
public class TwoStacksQueue {
    private Stack<Integer> stackOne;
    private Stack<Integer> stackTwo;

    public TwoStacksQueue() {
        this.stackOne = new Stack<>();
        this.stackTwo = new Stack<>();
    }

    public void add(int num) {
        while (!this.stackTwo.isEmpty()) {
            this.stackOne.push(this.stackTwo.peek());
            this.stackTwo.pop();
        }
        this.stackOne.push(num);

        while (!this.stackOne.isEmpty()) {
            this.stackTwo.push(this.stackOne.peek());
            this.stackOne.pop();
        }
    }

    public Integer poll() {
        if (this.stackTwo.isEmpty()) {
            return null;
        } else {

            int temp = this.stackTwo.peek();
            this.stackTwo.pop();
            return temp;
        }
    }

    public Integer peek() {
        if (this.stackTwo.isEmpty()) {
            return null;
        } else {
            return this.stackTwo.peek();
        }
    }

    public static void main(String[] args) {
        TwoStacksQueue queue = new TwoStacksQueue();
        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.peek());
        queue.add(3);
        queue.add(2);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

    }
}
