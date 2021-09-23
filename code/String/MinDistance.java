package code.String;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author Jevis Hoo
 * @since 2021/3/12 9:57
 * @description 数组中两个字符串的最小距离
 * 给定一个字符串数组 strings，再给定两个字符串 str1 和 str2，返回在 strings 中 str1 与 str2 的最小距离，
 * 如果 str1 或 str2 为 null，或不在 strings 中，返回-1。
 * <p>
 * 进阶问题：如果查询发生的次数有很多，如何把每次查询的时间复杂度降为 O(1)
 */
public class MinDistance {
    public static int minDistance(String[] strings, String str1, String str2) {
        if (strings == null || str1 == null || str2 == null) {
            return -1;
        }

        if (str1.equals(str2)) {
            return 0;
        }

        int index1 = -strings.length, index2 = -strings.length;
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < strings.length; i++) {
            if (str1.equals(strings[i])) {
                index1 = i;
                result = Math.min(result, index1 - index2);
            }
            if (str2.equals(strings[i])) {
                index2 = i;
                result = Math.min(result, index2 - index1);
            }
        }
        if (index1 == -strings.length || index2 == -strings.length) {
            return -1;
        }
        return result;
    }

    static HashMap<String, Integer> map = new HashMap<>();

    public static void setMinDistance(String[] strings) {
        HashSet<String> set = new HashSet<>(Arrays.asList(strings));

        Iterator<String> iterator = set.iterator();
        String[] iter = new String[set.size()];
        int index = 0;
        while (iterator.hasNext()) {
            iter[index++] = iterator.next();
        }


        for (int i = 0; i < set.size() - 1; i++) {
            for (int j = i + 1; j < set.size(); j++) {
                map.put(iter[i] + "_" + iter[j], Integer.MAX_VALUE);
            }
        }

        String str1, str2;
        for (String key : map.keySet()) {
            int index1 = -strings.length, index2 = -strings.length;

            for (int i = 0; i < strings.length; i++) {
                str1 = key.substring(0, 1);
                str2 = key.substring(2, 3);

                if (str1.equals(strings[i])) {
                    index1 = i;
                    map.put(key, Math.min(map.get(key), index1 - index2));
                }
                if (str2.equals(strings[i])) {
                    index2 = i;
                    map.put(key, Math.min(map.get(key), index2 - index1));
                }
            }
        }
    }


    public static int getMinDistance(String[] strings, String str1, String str2) {
        if (strings == null || str1 == null || str2 == null) {
            return -1;
        }

        if (str1.equals(str2)) {
            return 0;
        }

        if (map.containsKey(str1 + "_" + str2) || map.containsKey(str2 + "_" + str1)) {
            return map.containsKey(str1 + "_" + str2) ?
                    map.get(str1 + "_" + str2) : map.get(str2 + "_" + str1);
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        String[] strings = new String[]{"1", "3", "3", "3", "2", "3", "1"};
        String str1 = "1";
        String str2 = "2";

        System.out.println(minDistance(strings, str1, str2));
        setMinDistance(strings);
        System.out.println(getMinDistance(strings, str1, str2));

        strings = new String[]{"CD"};
        str1 = "AB";
        str2 = "CD";
        System.out.println(minDistance(strings, str1, str2));
        setMinDistance(strings);
        System.out.println(getMinDistance(strings, str1, str2));

    }
}
