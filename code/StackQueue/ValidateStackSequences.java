package code.StackQueue;

import java.util.Stack;

/**
 * @author Jevis Hoo
 * @since 2021/4/9 10:02
 * @description 栈的压入、弹出序列
 */
public class ValidateStackSequences {
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();

        int index = 0;
        for (int push : pushed) {
            stack.push(push);
            while (!stack.isEmpty() && stack.peek() == popped[index]) {
                stack.pop();
                index++;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        int[] pushed = new int[]{1, 2, 3, 4, 5};
        int[] popped = new int[]{4, 5, 3, 2, 1};
        System.out.println(validateStackSequences(pushed, popped));
        popped = new int[]{1, 2, 3, 4, 5};
        System.out.println(validateStackSequences(pushed, popped));
        popped = new int[]{1, 2, 3, 5, 4};
        System.out.println(validateStackSequences(pushed, popped));
        popped = new int[]{1, 2, 5, 4, 3};
        System.out.println(validateStackSequences(pushed, popped));
        popped = new int[]{1, 5, 4, 3, 2};
        System.out.println(validateStackSequences(pushed, popped));

        popped = new int[]{4, 5, 1, 2, 3};
        System.out.println(validateStackSequences(pushed, popped));
        popped = new int[]{1, 4, 5, 2, 3};
        System.out.println(validateStackSequences(pushed, popped));
        popped = new int[]{1, 2, 5, 3, 4};
        System.out.println(validateStackSequences(pushed, popped));
    }
}
