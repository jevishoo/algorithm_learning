package code.String;

/**
 * @author Jevis Hoo
 * @since 2021/3/22 11:45
 * @description 找到指定的新类型字符
 * 新类型字符的定义如下：
 * 新类型字符是长度为 1 或者 2 的字符串。表现形式为：
 * >>> 小写字母，例如，"e"；
 * >>> 大写字母 + 小写字母，例如，"Ab"；
 * >>> 大写字母 + 大写字母，例如，"DC"。
 * <p>
 * 现在给定一个字符串 str，str一定是若干新类型字符正确组合的结果。比如 "eaCCBi"，由新类型字符 "e"、"a"、"CC"和 "Bi"拼成。
 * 再给定一个整数 k，代表 str中的位置。请返回被 k位置指定的新类型字符。
 */
public class PointNewChar {
    public static String pointNewChar(String str, int k) {
        char[] chars = str.toCharArray();

        int index = 0;
        while (k > 1) {
            if (chars[index] > 96) {
                index++;
                k--;
            } else {
                k -= 2;
                index += 2;
            }
        }


        if (chars[index] > 96) {
            return String.valueOf(chars[index]);
        } else {
            return String.valueOf(chars[index]) + chars[index + 1];
        }
    }

    public static void main(String[] args) {
        String str = "aaABCDEcBCg";

        System.out.println(pointNewChar(str, 4));
        System.out.println(pointNewChar(str, 7));
        System.out.println(pointNewChar(str, 10));
    }
}
