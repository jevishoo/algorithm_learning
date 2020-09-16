package code.StackQueue;

import java.util.Stack;

/**
 * @Date 2020/9/16 19:43
 * @Created by Jevis_Hoo
 * @Description Evaluate Reverse Polish Notation
 * <p>
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * <p>
 * Valid operators are +, -, *, /.
 * Each operand may be an integer or another expression.
 * <p>
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid. That means the expression would always evaluate to a result and
 * there won't be any divide by zero operation.
 */
public class EvalRPN {

    public static int getEvalRPN(String[] arr) {
        Stack<Integer> stack = new Stack<>();

        for (String s : arr) {
            switch (s) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    stack.push(-(stack.pop() - stack.pop()));
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    int num = stack.pop();
                    stack.push(stack.pop() / num);
                    break;
                default:
                    stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String[] array1 = new String[]{"2", "1", "+", "3", "*"};
        System.out.println(getEvalRPN(array1));
        String[] array2 = new String[]{"4", "13", "5", "/", "+"};
        System.out.println(getEvalRPN(array2));
        String[] array3 = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(getEvalRPN(array3));
        String[] array4 = new String[]{"10"};
        System.out.println(getEvalRPN(array4));
    }
}
