package code.String;

import java.util.Arrays;

/**
 * @author Jevis Hoo
 * @since 2021/5/2 9:56
 * @description 交错字符串
 * S3 是否是 S1,S2 交错组成的
 * 交错：字符串打乱，但不改变原字符的前后顺序
 */
public class InterMixedString {
    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "ABCDE";
        String s3 = "aABCbcdDeE";

        System.out.println(isInterMixed(s1, s2, s3));
    }

    private static boolean isInterMixed(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;

        for (int i = 1; i <= s1.length(); i++) {
            if (s3.charAt(i - 1) == s1.charAt(i - 1)) {
                dp[i][0] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= s2.length(); i++) {
            if (s3.charAt(i - 1) == s2.charAt(i - 1)) {
                dp[0][i] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (dp[i][j - 1] && s3.charAt(i + j - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = true;
                    continue;
                }

                if (dp[i - 1][j] && s3.charAt(i + j - 1) == s1.charAt(i - 1)) {
                    dp[i][j] = true;
                }
            }
        }

        printMatrix(dp);

        return dp[s1.length()][s2.length()];
    }


    private static void printMatrix(boolean[][] dp) {
        for (boolean[] arr : dp) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
