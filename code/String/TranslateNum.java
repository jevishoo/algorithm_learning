package code.String;

import java.util.Arrays;

/**
 * @author Jevis Hoo
 * @since 2021/4/24 10:20
 * @description 把数字翻译成字符串
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 */
public class TranslateNum {
    public static int translateNum(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int[] dp = new int[chars.length];

        dp[0] = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i - 1] == '1' || (chars[i - 1] == '2' && chars[i] < '6')) {
                dp[i] = dp[i - 1] + (i == 1 ? 1 : dp[i - 2]);
            } else {
                dp[i] = dp[i - 1];
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[chars.length - 1];
    }

    public static int translateNumByArchive(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int pre = 1, res = 1;
        int tmp;
        for (int i = 1; i < chars.length; i++) {
            tmp = res;
            if (chars[i - 1] == '1' || (chars[i - 1] == '2' && chars[i] < '6')) {
                res += (i == 1 ? 1 : pre);
            }
            pre = tmp;
        }
        return res;
    }


    public static void main(String[] args) {
        int num = 32258;
        System.out.println(translateNum(num));
        System.out.println(translateNumByArchive(num));
    }
}
