package code.String;

/**
 * @author Jevis Hoo
 * @since 2021/3/19 20:02
 * @description 回文最少分割数
 */
public class MinCut {
    public static int getMinCut(String str) {
        if (str == null || str.length() < 2) {
            return 0;
        }

        char[] chars = str.toCharArray();

        int[][] dp = new int[chars.length][chars.length];

        for (int j = 1; j < chars.length; j++) {
            for (int i = j - 1; i >= 0; i--) {
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = 1;
                }
            }
        }

        int res = 0, index = chars.length - 1;
        int row = 0;
        while (index >= 0) {
            if (dp[row][index] == 0) {
                if (index == chars.length - 1) {
                    break;
                }

                res++;
                row = index + 1;
                index = chars.length - 1;
            } else {
                index--;
            }
        }

        return res;
    }

    public static int minCut(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int len = chars.length;
        int[] dp = new int[len + 1];
        dp[len] = -1;
        boolean[][] p = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = i; j < len; j++) {
                if (chars[i] == chars[j] && (j - i < 2 || p[i + 1][j - 1])) {
                    p[i][j] = true;
                    dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        String str1 = "ABA";
        String str2 = "ACDCDCDAD";
        String str3 = "ABCDEFG";

        System.out.println(getMinCut(str1));
        System.out.println(minCut(str1));
        System.out.println(getMinCut(str2));
        System.out.println(minCut(str2));
        System.out.println(getMinCut(str3));
        System.out.println(minCut(str3));
    }
}
