package code.RecursionDP;

/**
 * @author Jevis Hoo
 * @date 2021/3/2 10:03
 * @description 矩阵的最小路径和
 */
public class MinPathSumOfMatrix {
    /**
     * @param m matrix
     * @return pathSum
     * @description 经典动态规划方法
     */
    public static int minPathSum(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }

        int row = m.length;
        int column = m[0].length;

        int[][] dp = new int[row][column];

        dp[0][0] = m[0][0];

        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int i = 1; i < column; i++) {
            dp[0][i] = dp[0][i - 1] + m[0][i];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][column - 1];
    }


    /**
     * @param m matrix
     * @return pathSum
     * @description 经过空间压缩的动态规划方法
     */
    public static int getMinPathSumOfMatrix(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }

        int row = m.length;
        int column = m[0].length;

        int more = Math.max(row, column);
        int less = Math.min(row, column);
        // 行数是不是大于或等于列数
        boolean rowFlag = more == m.length;
        int[] dp = new int[less];
        dp[0] = m[0][0];
        for (int i = 1; i < less; i++) {
            dp[i] = dp[i - 1] + (rowFlag ? m[0][i] : m[i][0]);
        }

        for (int i = 1; i < more; i++) {
            dp[0] = dp[0] + (rowFlag ? m[i][0] : m[0][i]);

            for (int j = 1; j < less; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + (rowFlag ? m[i][j] : m[j][i]);
            }
        }

        return dp[less - 1];
    }


    public static void main(String[] args) {
        int[][] m = new int[][]{
                {1, 3, 5, 9},
                {8, 1, 3, 4},
                {5, 0, 6, 1},
                {8, 8, 4, 0}
        };

        System.out.println(minPathSum(m));
        System.out.println(getMinPathSumOfMatrix(m));
    }
}
