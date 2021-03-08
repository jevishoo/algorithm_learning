package code.String;

import java.util.HashMap;

/**
 * @author Jevis Hoo
 * @date 2021/3/8 10:55
 * @description 判断两个字符串是否互为变形词
 * <p>
 * 给定两个字符串 str1 和 str2，如果 str1 和 str2 中出现的字符种类一样且每种字符出现的次数也一样，
 * 那么 str1 与 str2 互为变形词。请实现函数判断两个字符串是否互为变形词。
 */
public class IsDeformation {
    public static boolean isDeformation(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        }

        if (str1.length() != str2.length()) {
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<>();

        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        for (char ch : chars1) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }

        for (char ch : chars2) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) - 1);
            } else {
                return false;
            }
        }

        for (char ch : map.keySet()) {
            if (map.get(ch) != 0) {
                return false;
            }
        }

        return true;
    }


    public static boolean isDeformation2(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        char[] chas1 = str1.toCharArray();
        char[] chas2 = str2.toCharArray();
        int[] map = new int[256];

        for (char c : chas1) {
            map[c]++;
        }

        for (char c : chas2) {
            if (map[c]-- == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String a = "cdabas1";
        String b = "abcdsa2";

        System.out.println(isDeformation(a, b));
        System.out.println(isDeformation2(a, b));

        a = "1ab21";
        b = "ab122";
        System.out.println(isDeformation(a, b));
        System.out.println(isDeformation2(a, b));

        a = "2ab1";
        b = "ab12";
        System.out.println(isDeformation(a, b));
        System.out.println(isDeformation2(a, b));
    }
}
