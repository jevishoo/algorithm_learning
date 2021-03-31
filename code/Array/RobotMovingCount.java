package code.Array;

/**
 * @author Jevis Hoo
 * @date 2021/3/31 16:05
 * @description 机器人的运动范围
 * 地上有一个 m行 n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
 * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当 k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
 * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 */
public class RobotMovingCount {
    private static boolean[][] arrived;

    public static int movingCount(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }

        arrived = new boolean[m][n];
//        return dfs(0, 0, m, n, k);
        return dfs1(0, 0, 0, 0, m, n, k);
    }

    private static int dfs(int i, int j, int m, int n, int k) {
        if (i >= m || j >= n || getBitSum(i, j) > k || arrived[i][j]) {
            return 0;
        }
        arrived[i][j] = true;
        return 1 + dfs(i + 1, j, m, n, k) + dfs(i, j + 1, m, n, k);
    }

    private static int getBitSum(int i, int j) {
        int sum = 0;
        while (i > 0) {
            sum += i % 10;
            i = i / 10;
        }

        while (j > 0) {
            sum += j % 10;
            j = j / 10;
        }

        return sum;
    }


    private static int dfs1(int i, int j, int iSum, int jSum, int m, int n, int k) {
        if (i >= m || j >= n || iSum + jSum > k || arrived[i][j]) {
            return 0;
        }
        arrived[i][j] = true;
        return 1 + dfs1(i + 1, j, (i + 1) % 10 == 0 ? iSum - 8 : iSum + 1, jSum, m, n, k) +
                dfs1(i, j + 1, iSum, (j + 1) % 10 == 0 ? jSum - 8 : jSum + 1, m, n, k);
    }


    public static void main(String[] args) {
        int m = 2, n = 3, k = 1;
        System.out.println(movingCount(m, n, k));
    }
}
