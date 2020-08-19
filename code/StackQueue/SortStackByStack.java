package code.StackQueue;

import java.util.Stack;

/**
 * @Date 2020/8/16 19:13
 * @Created by Jevis_Hoo
 * @Description 用一个栈实现另一个栈的排序
 */
public class SortStackByStack {
    public static void sortStack(Stack<Integer> stack) {
        Stack<Integer> auxStack = new Stack<>();

        while (!stack.isEmpty()) {
            int change = stack.pop();
            while (!auxStack.isEmpty() && auxStack.peek() < change) {
                stack.push(auxStack.pop());
            }
            auxStack.push(change);
        }

        while (!auxStack.isEmpty()) {
            stack.push(auxStack.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(9);
        stack.push(3);
        stack.push(6);
        stack.push(4);
        stack.push(8);

        sortStack(stack);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
