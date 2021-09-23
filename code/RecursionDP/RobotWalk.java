package code.RecursionDP;

/**
 * @author Jevis Hoo
 * @since 2021/3/2 10:14
 * @description 机器人达到指定位置方法数
 * <p>
 * 假设有排成一行的 N 个位置，记为 1~N，N 一定大于或等于 2。开始时机器人在其中的 M 位置上（ M 一定是 1～N 中的一个），
 * 机器人可以往左走或者往右走，如果机器人来到 1 位置，那么下一步只能往右来到 2 位置；如果机器人来到 N 位置，那么下一步只能往左来到 N-1 位置。
 * 规定机器人必须走 K 步，最终能来到 P 位置（ P 也一定是 1～N 中的一个）的方法有多少种。
 * 给定四个参数 N、M、K、P，返回方法数。
 */
public class RobotWalk {
    public static int walk(int n, int cur, int rest, int p) {
        if (rest == 0) {
            return cur == p ? 1 : 0;
        }

        if (cur == 1) {
            return walk(n, 2, rest - 1, p);
        }

        if (cur == n) {
            return walk(n, n - 1, rest - 1, p);
        }

        return walk(n, cur - 1, rest - 1, p) + walk(n, cur + 1, rest - 1, p);
    }

    /**
     * @param n 位置为 1 ~ n，固定参数
     * @param m 当前在 m 位置，可变参数
     * @param k 还剩 k 步没有走，可变参数
     * @param p 最终目标位置是 p，固定参数
     * @return 方法数
     * @description 暴力递归
     */
    public static int ways1(int n, int m, int k, int p) {
        if (n < 2 || k < 1 || m < 1 || m > n || p < 1 || p > n) {
            return 0;
        }
        return walk(n, m, k, p);
    }

    /**
     * @param n 位置为 1 ~ n，固定参数
     * @param m 当前在 m 位置，可变参数
     * @param k 还剩 k 步没有走，可变参数
     * @param p 最终目标位置是 p，固定参数
     * @return 方法数
     * @description 动态规划
     * 本题动态规划的解法就是把规模为 n×k 的表填好，填写每一个位置的值都是 O(1)的时间复杂度，
     * 所以总的时间复杂度为 O(n×k)，
     */
    public static int ways2(int n, int m, int k, int p) {
        // 参数无效直接返回0
        if (n < 2 || k < 1 || m < 1 || m > n || p < 1 || p > n) {
            return 0;
        }

        int[][] dp = new int[k + 1][n + 1];
        dp[0][p] = 1;

        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                if (j == 1) {
                    dp[i][j] = dp[i - 1][2];
                } else if (j == n) {
                    dp[i][j] = dp[i - 1][n - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                }
            }
        }
        return dp[k][m];
    }

    /**
     * @param n 位置为 1 ~ n，固定参数
     * @param m 当前在 m 位置，可变参数
     * @param k 还剩 k 步没有走，可变参数
     * @param p 最终目标位置是 p，固定参数
     * @return 方法数
     * @description 动态规划 + 空间压缩
     */
    public static int ways3(int n, int m, int k, int p) {
        // 参数无效直接返回0
        if (n < 2 || k < 1 || m < 1 || m > n || p < 1 || p > n) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[p] = 1;
        for (int i = 1; i <= k; i++) {
            // 左上角的值
            int leftUp = dp[1];
            for (int j = 1; j <= n; j++) {
                int tmp = dp[j];
                if (j == 1) {
                    dp[j] = dp[j + 1];
                } else if (j == n) {
                    dp[j] = leftUp;
                } else {
                    dp[j] = leftUp + dp[j + 1];
                }
                leftUp = tmp;
            }
        }
        return dp[m];
    }

    public static void main(String[] args) {
        int n = 5, m = 2, k = 3, p = 3;
        System.out.println(ways1(n, m, k, p));
        n = 100;
        m = 2;
        k = 100;
        p = 54;
        System.out.println(ways2(n, m, k, p));
        System.out.println(ways3(n, m, k, p));
//        System.out.println(ways1(n, m, k, p));
    }
}
