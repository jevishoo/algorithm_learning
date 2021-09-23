package code.StackQueue;

import java.util.Stack;

/**
 * @since 2020/10/5 10:22
 * @Created by Jevis_Hoo
 * @Description Score of Parentheses
 * <p>
 * Given a balanced parentheses string S, compute the score of the string based on the following rule:
 * <p>
 * >>> () has score 1
 * >>> AB has score A + B, where A and B are balanced parentheses strings.
 * >>> (A) has score 2 * A, where A is a balanced parentheses string.
 */
public class ParenthesesScore {
    public static int scoreOfParentheses1(String S) {
        int i = 0, n = S.length(), open = -1, ans = 0;
        while (i < n) {
            while (i < n && S.charAt(i++) == '(') open++;

            ans += Math.pow(2, open);

            while (i < n && S.charAt(i++) == ')') open--;
        }

        return ans;
    }

    public static int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack<>();
        char[] chars = S.toCharArray();

        for (char c : chars) {
            if (c == '(')
                stack.push(0);
            else {
                if (stack.peek() == 0) {
                    stack.pop();
                    stack.push(1);
                } else {
                    int temp = 0;
                    while (!stack.isEmpty() && stack.peek() != 0) {
                        temp = temp + stack.pop();
                    }
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                    stack.push(temp * 2);
                }
            }
        }

        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        String s1 = "()";
        String s2 = "(())";
        String s3 = "()()";
        String s4 = "(()(()))";
        String s5 = "((((((())))()())))";
        System.out.println(scoreOfParentheses1(s1));
        System.out.println(scoreOfParentheses1(s2));
        System.out.println(scoreOfParentheses1(s3));
        System.out.println(scoreOfParentheses1(s4));
        System.out.println(scoreOfParentheses1(s5));
    }
}
