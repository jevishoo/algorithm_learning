package code.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Jevis Hoo
 * @date 2021/4/23 14:50
 * @description 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 */
public class StringPermutation {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(permutation("abb")));
        System.out.println(Arrays.toString(permutation("abc")));
        System.out.println(Arrays.toString(permutation("abbbb")));
    }

    public static String[] permutation(String s) {
        ArrayList<String> res = new ArrayList<>();
        backtrack(s.toCharArray(), "", new boolean[s.length()], res);
        return res.toArray(new String[0]);
    }

    private static void backtrack(char[] chars, String tmp, boolean[] used, ArrayList<String> res) {
        if (tmp.length() == chars.length) {
            //一些逻辑操作（可有可无，视情况而定）
            res.add(tmp);
            return;
        }
        HashSet<Character> pre = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            if (!used[i] && !pre.contains(chars[i])) {
                used[i] = true;
                backtrack(chars, tmp + chars[i], used, res);
                pre.add(chars[i]);
                used[i] = false;
            }
        }
    }
}
