package code.String;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Jevis Hoo
 * @since 2021/3/20 13:35
 * @description 拼接所有字符串产生字典顺序最小的大写字符串
 * 给定一个字符串类型的数组 strings，请找到一种拼接顺序，
 * 使得将所有的字符串拼接起来组成的大写字符串是所有可能性中字典顺序最小的，并返回这个大写字符串。
 */
public class LowestString {
    public static String getLowestString(String[] strings) {
        if (strings == null || strings.length == 0) {
            return "";
        }
        // 根据新的比较方式排序
        Arrays.sort(strings, new MyComparator());
        StringBuilder res = new StringBuilder();
        for (String str : strings) {
            res.append(str);
        }
        return res.toString();
    }


    public static void main(String[] args) {
        String[] strings = new String[]{
                "BA", "B"
        };

        System.out.println(getLowestString(strings));
    }
}

class MyComparator implements Comparator<String> {
    @Override
    public int compare(String a, String b) {
        return (a + b).compareTo(b + a);
    }
}