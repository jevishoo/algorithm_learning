package code.String;

import java.util.Arrays;

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
        int[][] dp = new int[chars.length][chars.length];
        for (int i = 0; i < chars.length; i++) {
            dp[i][i] = 1;
        }

        for (int j = 1; j < chars.length; j++) {
            for (int i = j - 1; i >= 0; i--) {
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j] * 2 + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1] * 2;
                }
            }
        }


        for (int[] arr : dp) {
            System.out.println(Arrays.toString(arr));

        }
        return dp[0][chars.length - 1];
    }

    public static void main(String[] args) {
        String str = "aabc";
        System.out.println(getBeautifulSubsequence(str));
        str = "abac";
        System.out.println(getBeautifulSubsequence(str));
    }
}
