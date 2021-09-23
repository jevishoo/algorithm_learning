package code.StackQueue;

import java.util.Stack;

/**
 * @since 2020/8/12 15:23
 * @Created by Jevis_Hoo
 * @Description 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。
 * 【要求】
 * 1．pop、push、getMin 操作的时间复杂度都是O(1)。
 * 2．设计的栈类型可以使用现成的栈结构。
 */

public class MinStack {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public MinStack() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int num) {
        this.stackData.push(num);

        if (this.stackMin.isEmpty()) {
            this.stackMin.push(num);
        } else if (num < this.getMin()) {
            this.stackMin.push(num);
        } else {
            this.stackMin.push(this.stackMin.peek());
        }
    }

    public int pop() {
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("Your stack is empty.");
        }
        this.stackMin.pop();
        return this.stackData.pop();
    }

    public int getMin() {
        if (this.stackMin.isEmpty()) {
            throw new RuntimeException("Your stack is empty.");
        }
        return this.stackMin.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(3);
        System.out.println(minStack.getMin());
        minStack.push(4);
        System.out.println(minStack.getMin());
        minStack.push(5);
        System.out.println(minStack.getMin());
        minStack.push(1);
        System.out.println(minStack.getMin());
        minStack.push(2);
        System.out.println(minStack.getMin());
        minStack.push(1);
        System.out.println(minStack.getMin());
    }
}
