package code.Stack_Queue;

import java.util.Stack;

/**
 * @Date 2020/8/14 20:01
 * @Created by Jevis_Hoo
 * @Description 用递归实现栈逆序
 * 【描述】
 * 一个栈依次压入1、2、3、4、5，那么从栈顶到栈底分别为5、4、3、2、1。将这个栈转置
 * 后，从栈顶到栈底为1、2、3、4、5，也就是实现栈中元素的逆序，但是只能用递归函数来实
 * 现，不能用其他数据结构。
 */
public class RecursionReverseStack {
    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        /*
        由reverseStack递归不断深入，getBottom()作用：stack减少一个栈顶元素，并返回栈底元素
         */
        int value = getBottom(stack);
        /*
        栈元素不断减少直至为空
         */
        reverseStack(stack);
        /*
        递归到最内部时，stack为空，value是栈顶元素
        不断向上回溯过程中，stack不断加入栈从上往下的元素
         */
        stack.push(value);
    }

    //stack减少一个栈顶元素，并返回栈底元素
    private static int getBottom(Stack<Integer> stack) {
        int peek = stack.pop();//取出栈顶元素
        if (stack.isEmpty()) {
            return peek;
        } else {
            int bottom = getBottom(stack);//不断向下直至stack为空时返回即为bottom（栈底元素）
            stack.push(peek);//stack由空栈不断压入peek元素
            return bottom;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        reverseStack(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
