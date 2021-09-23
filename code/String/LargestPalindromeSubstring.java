package code.String;

import java.util.Arrays;

/**
 * @author Jevis Hoo
 * @since 2021/4/2 11:27
 * @description 最长回文子串
 */
public class LargestPalindromeSubstring {
    public static int getLargestPalindromeSubstring(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        char[] chars = str.toCharArray();
        int[][] dp = new int[chars.length][chars.length];

        for (int i = 0; i < chars.length; i++) {
            dp[i][i] = 1;
        }

        for (int j = 1; j < chars.length; j++) {
            for (int i = j - 1; i >= 0; i--) {
                if (chars[i] == chars[j] && dp[i + 1][j - 1] == j - i - 1) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        for (int[] arr : dp) {
            System.out.println(Arrays.toString(arr));
        }

        int m = 0;
        int n = chars.length - 1;
        char[] res = new char[dp[0][n]];
        int lIndex = 0;
        int rIndex = res.length - 1;

        while (rIndex > lIndex) {
            if (n > 0 && dp[m][n] == dp[m][n - 1]) {
                n--;
            } else if (m < chars.length && dp[m][n] == dp[m + 1][n]) {
                m++;
            } else {
                res[rIndex--] = chars[n];
                res[lIndex++] = chars[n];
                m++;
                n--;
            }
        }
        System.out.println(String.valueOf(res));

        return dp[0][chars.length - 1];
    }

    public static void main(String[] args) {
        String str = "1XQXT122151XT77TX";
        System.out.println(getLargestPalindromeSubstring(str));

        str = "XQX1221X";
        System.out.println(getLargestPalindromeSubstring(str));
    }
}
