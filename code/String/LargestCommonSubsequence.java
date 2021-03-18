package code.String;

/**
 * @author Jevis Hoo
 * @date 2021/3/18 9:32
 * @description
 */
public class LargestCommonSubsequence {
    public static String getLcSubsequence(String str1, String str2) {
        if (str1 == null || str2 == null || "".equals(str1) || "".equals(str2)) {
            return "";
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        int[][] dp = process(chars1, chars2);
        int m = chars1.length - 1;
        int n = chars2.length - 1;
        char[] res = new char[dp[m][n]];
        int index = res.length - 1;
        while (index >= 0) {
            if (n > 0 && dp[m][n] == dp[m][n - 1]) {
                n--;
            } else if (m > 0 && dp[m][n] == dp[m - 1][n]) {
                m--;
            } else {
                res[index--] = chars1[m];
                m--;
                n--;
            }
        }
        return String.valueOf(res);
    }

    private static int[][] process(char[] chars1, char[] chars2) {
        int[][] dp = new int[chars1.length][chars2.length];

        dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;
        for (int i = 1; i < chars1.length; i++) {
            dp[i][0] = dp[i - 1][0] == 1 ? 1 : chars2[0] == chars1[i] ? 1 : 0;
        }
        for (int i = 1; i < chars2.length; i++) {
            dp[0][i] = dp[0][i - 1] == 1 ? 1 : chars1[0] == chars2[i] ? 1 : 0;
        }

        for (int i = 1; i < chars1.length; i++) {
            for (int j = 1; j < chars2.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (chars1[i] == chars2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        String str1 = "A1BC2D3EFGH45I6JK7LMN";
        String str2 = "12OPQ3RST4U5V6W7XYZ";
        System.out.println(getLcSubsequence(str1, str2));
    }
}
