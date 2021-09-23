package code.StackQueue;

import java.util.Stack;

/**
 * @since 2020/10/5 10:01
 * @Created by Jevis_Hoo
 * @Description Backspace String Compare
 * <p>
 * Given two strings S and T, return if they are equal when both are typed into empty text editors.
 * # means a backspace character.
 * <p>
 * Note that after backspacing an empty text, the text will continue empty.
 */
public class BackspaceStringCompare {
    public static boolean backspaceCompare(String S, String T) {
        Stack<Character> sStack = new Stack<>();
        Stack<Character> tStack = new Stack<>();

        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == '#') {
                if (!sStack.isEmpty()) sStack.pop();
            } else sStack.push(c);
        }
        for (int i = 0; i < T.length(); i++) {
            char c = T.charAt(i);
            if (c == '#') {
                if (!tStack.isEmpty()) tStack.pop();
            } else tStack.push(c);
        }

        if (sStack.isEmpty() && tStack.isEmpty())
            return true;
        else {
            if (sStack.size() != tStack.size())
                return false;
            else {
                while (!sStack.isEmpty()) {
                    if (sStack.pop() != tStack.pop())
                        return false;
                }
                return true;
            }
        }
    }

    public static boolean backspaceCompare1(String S, String T) {
        return getFinalString(S).equals(getFinalString(T));
    }

    private static String getFinalString(String s) {
        int i = 0;
        char[] arr = s.toCharArray();
        for (char c : arr) {
            if (c == '#') {
                i--;
                i = Math.max(0, i);
            } else {
                arr[i] = c;
                i++;
            }
        }
        return new String(arr, 0, i);
    }

    public static void main(String[] args) {
//        String S = "ab#c", T = "ad#c";
//        String S = "ab##", T = "c#d#";
//        String S = "a##c", T = "#a#c";
        String S = "a#c", T = "b";

        System.out.println(backspaceCompare(S, T));
        System.out.println(backspaceCompare1(S, T));
    }
}
