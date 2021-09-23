package code.StackQueue;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author Jevis Hoo
 * @since 2020/9/10 13:40
 * @Description Valid Parentheses
 * <p>
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * >>> Open brackets must be closed by the same type of brackets.
 * >>> Open brackets must be closed in the correct order.
 */
public class ValidParentheses {
    private HashMap<Character, Character> mappings;

    public ValidParentheses() {
        this.mappings = new HashMap<>();
        this.mappings.put(')', '(');
        this.mappings.put('}', '{');
        this.mappings.put(']', '[');
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            //新加入元素为 右括号
            if (this.mappings.containsKey(s.charAt(i))) {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    if (!stack.pop().equals(this.mappings.get(s.charAt((i))))) {
                        return false;
                    }
                }
            } else {
                stack.push(s.charAt(i));
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();
        String s1 = "()[]{}";
        String s2 = "(]";
        String s3 = "([)]";
        String s4 = "{[]}";
        String s5 = "[";

        System.out.println(validParentheses.isValid(s1));
        System.out.println(validParentheses.isValid(s2));
        System.out.println(validParentheses.isValid(s3));
        System.out.println(validParentheses.isValid(s4));
        System.out.println(validParentheses.isValid(s5));

    }
}
