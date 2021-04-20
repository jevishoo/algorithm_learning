package code.StackQueue;

import java.util.Stack;

/**
 * @Date 2020/9/22 18:59
 * @Created by Jevis_Hoo
 * @Description Decode String
 * <p>
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets
 * is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 */
public class DecodeString {
    public static String decodeString(String s) {
        Stack<String> strStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = 10 * num + c - '0';
            } else if (c == '[') {
                numStack.push(num);
                strStack.push(sb.toString());
                sb = new StringBuilder();
                num = 0;
            } else if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                sb.append(c);
            } else {//c==']'
                StringBuilder pSb = new StringBuilder(strStack.pop());
                int repeatTimes = numStack.pop();
                for (int j = 0; j < repeatTimes; j++) {
                    pSb.append(sb);
                }
                sb = pSb;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s1 = "3[a]2[bc]";
        String s2 = "3[a2[c]]";
        String s3 = "2[abc]3[cd]ef";
        String s4 = "abc3[cd]xyz";
        String s5 = "3[a]2[b4[F]c]";

        System.out.println(decodeString(s1));
        System.out.println(decodeString(s2));
        System.out.println(decodeString(s3));
        System.out.println(decodeString(s4));
        System.out.println(decodeString(s5));
    }
}
