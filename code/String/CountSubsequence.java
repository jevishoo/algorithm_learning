package code.String;

import java.util.Arrays;

/**
 * @author Jevis Hoo
 * @since 2021/5/2 14:13
 * @description 不同的子序列
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 * <p>
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
 * 例如，"ACE"是"ABCDE"的一个子序列，而"AEC"不是
 */
public class CountSubsequence {
    public static void main(String[] args) {
        String str1 = "hooooJ";
        String str2 = "hooJ";

        System.out.println(getCountSubsequence(str1, str2));
    }

    private static int getCountSubsequence(String str, String sub) {
        if (str.equals(sub)) {
            return 1;
        }

        int[][] dp = new int[str.length()][sub.length()];

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == sub.charAt(0)) {
                dp[i][0] = i == 0 ? 1 : dp[i - 1][0] + 1;
            } else {
                dp[i][0] = i == 0 ? 0 : dp[i - 1][0];
            }
        }

        for (int j = 1; j < sub.length(); j++) {
            for (int i = 1; i < str.length(); i++) {
                if (str.charAt(i) == sub.charAt(j)) {
                    // 如果str第i+1个字符等于sub第j+1个字符
                    // 则 总匹配个数为
                    // 前i个字符匹配sub前j个字符的个数+前i-1个字符匹配sub前j个字符的个数
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        for (int[] arr : dp) {
            System.out.println(Arrays.toString(arr));
        }

        return dp[str.length() - 1][sub.length() - 1];
    }
}
