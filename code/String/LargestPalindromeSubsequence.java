package code.String;

/**
 * @author Jevis Hoo
 * @date 2021/3/18 10:26
 * @description 最长回文子序列
 */
public class LargestPalindromeSubsequence {

    public static String getLargestPalindromeSubsequence(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }

        char[] chars = str.toCharArray();
        int[][] dp = new int[chars.length][chars.length];

        for (int i = 0; i < chars.length; i++) {
            dp[i][i] = 1;
        }

        for (int j = 1; j < chars.length; j++) {
            for (int i = 0; i < chars.length - j; i++) {
                dp[i][i + j] = Math.max(dp[i + 1][i + j], dp[i][i + j - 1]);

                if (chars[i] == chars[i + j]) {
                    dp[i][i + j] = Math.max(dp[i][i + j], dp[i + 1][i + j - 1] + 2);
                }
            }
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
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        String str1 = "1XQT122517X";

        // 方法一
        System.out.println(getLargestPalindromeSubsequence(str1));
        System.out.println(getLargestPalindromeSubsequence(str1).length());

        // 方法二
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = str1.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            stringBuilder.append(chars[i]);
        }
        String reverse = stringBuilder.toString();
        System.out.println(LargestCommonSubsequence.getLcSubsequence(str1, reverse).length());
    }
}
