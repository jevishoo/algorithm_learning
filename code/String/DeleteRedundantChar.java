package code.String;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Jevis Hoo
 * @since 2021/3/11 16:52
 * @description 删除多余字符得到字典序最小的字符串
 * 给定一个全是小写字母的字符串str，删除多余字符，使得每种字符只保留一个，并让最终结果字符串的字典序最小。
 */
public class DeleteRedundantChar {
    public static String deleteChar(String str) {
        char[] chars = str.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        for (char ch : chars) {
            map.putIfAbsent(ch, 0);
            map.put(ch, map.get(ch) + 1);
        }
        int[] index = new int[map.size()];
        int iter = 0;
        int maxIndex = 0;

        for (char cur : map.keySet()) {
            if (iter == 0) {
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] == cur) {
                        index[iter++] = i;
                        maxIndex = i;
                        break;
                    }
                }
            } else {
                boolean flag = true;
                for (int i = maxIndex + 1; i < chars.length; i++) {
                    if (chars[i] == cur) {
                        index[iter++] = i;
                        maxIndex = i;
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    for (int i = maxIndex - 1; i >= 0; i--) {
                        if (chars[i] == cur) {
                            index[iter++] = i;
                            break;
                        }
                    }
                }
            }
        }

        Arrays.sort(index);
        for (int i : index) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        String str = "bcabc";

        System.out.println(deleteChar(str));

        str = "dbcacbca";
        System.out.println(deleteChar(str));
    }
}
