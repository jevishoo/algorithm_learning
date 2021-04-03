package code.RecursionDP;

import java.util.Arrays;

/**
 * @author Jevis Hoo
 * @date 2021/4/3 20:40
 * @description 整数分割
 */
public class SplitNum {
    public static int getSplitNum(int n) {
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
            dp[i][0] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (i == j) {
                    dp[i][i] = dp[i][i - 1] + 1;
                } else if (i < j) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i - j - 1][j] + dp[i][j - 1];
                }
            }
        }

        for (int[] arr : dp) {
            System.out.println(Arrays.toString(arr));
        }

        return dp[n - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(getSplitNum(4));
//        System.out.println(getSplitNum(10));
    }
}
