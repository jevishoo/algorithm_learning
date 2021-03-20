package code.String;

import java.util.HashMap;

/**
 * @author Jevis Hoo
 * @date 2021/3/20 13:58
 * @description 找到字符串的最长无重复字符子串
 * 如果 str的长度为 N，请实现时间复杂度为 O(N)的方法。
 */
public class MaxUnique {
    public static int getMaxUnique(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }

        char[] chars = str.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>(256);
        int len, pre = -1;
        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            pre = Math.max(pre, map.getOrDefault(chars[i], -1));
            len = i - pre;
            res = Math.max(res, len);
            map.put(chars[i], i);
        }
        return res;
    }

    public static void main(String[] args) {
        String str1 = "abcd";
        String str2 = "aabcb";
        String str3 = "aabcad";

        System.out.println(getMaxUnique(str1));
        System.out.println(getMaxUnique(str2));
        System.out.println(getMaxUnique(str3));
    }
}
