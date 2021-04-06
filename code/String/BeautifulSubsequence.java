package code.String;

import java.util.*;

/**
 * @author Jevis Hoo
 * @date 2021/4/4 10:09
 * @description 不重复的子序列（包含空串）
 */
public class BeautifulSubsequence {
    public static int getBeautifulSubsequence(String str) {
        if (str == null) {
            return 0;
        }

        char[] chars = str.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : chars) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }
        int res = 1;
        Set<Character> keySet = map.keySet();
        for (char key : keySet) {
            res *= map.get(key) + 1;
            res %= 20210101;
        }

        return res;
    }

    public static void main(String[] args) {
        String str = "aabc";
        System.out.println(getBeautifulSubsequence(str));
        str = "abac";
        System.out.println(getBeautifulSubsequence(str));
    }
}
