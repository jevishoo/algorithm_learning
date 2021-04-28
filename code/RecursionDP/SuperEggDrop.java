package code.RecursionDP;

import java.util.Arrays;

/**
 * @author Jevis Hoo
 * @date 2021/4/26 22:30
 * @description 鸡蛋掉落
 * 给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
 * <p>
 * 已知存在楼层 f ，满足0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
 * <p>
 * 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足1 <= x <= n）。
 * 如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中重复使用这枚鸡蛋。
 * <p>
 * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
 */
public class SuperEggDrop {
    public static void main(String[] args) {
        System.out.println(superEggDrop(10, 100));
    }

    public static int superEggDrop(int n, int t) {
        int[][] dp = new int[t + 1][n + 1];
        dp[1][1] = 1;
        for (int i = 2; i <= t; i++) {
            dp[i][1] = i;
        }

        for (int i = 2; i <= n; i++) {
            dp[1][i] = 1;
        }

        // 几个鸡蛋
        for (int j = 2; j <= n; j++) {
            // 几层楼
            for (int i = 2; i <= t; i++) {
                int res = Integer.MAX_VALUE;
                for (int k = 1; k <= i; k++) {
                    int max = Math.max(dp[k - 1][j - 1], dp[i - k][j]) + 1;
                    res = Math.min(max, res);
                }
                dp[i][j] = res;
            }
        }

        for (int[] arr : dp) {
            System.out.println(Arrays.toString(arr));
        }

        return dp[t][n];
    }
}
