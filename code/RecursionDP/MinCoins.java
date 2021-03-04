package code.RecursionDP;

/**
 * @author Jevis Hoo
 * @date 2021/3/3 21:46
 * @description 换钱的最少货币数
 */
public class MinCoins {
    public static int getMinCoins(int[] arr, int aim, int way) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }
        if (way == 0) {
            return process(arr, 0, aim);
        } else if (way == 1) {
            return process(arr, aim);
        } else {
            return process1(arr, aim);
        }
    }

    /**
     * @param arr   面值数组
     * @param index 可以任意自由使用 index 及其之后的钱
     * @param aim   目标金额
     * @return 最少货币数
     * @description 暴力递归法
     */
    private static int process(int[] arr, int index, int aim) {
        if (index == arr.length) {
            // 可以换成功 返回 0，否则返回 -1
            return aim == 0 ? 0 : -1;
        } else {
            int res = -1;
            for (int i = 0; arr[index] * i <= aim; i++) {
                // 取arr[index]面值 一共 i 张
                // 当 index 超出索引，且能换完 即最后一种面值 i 张 换完，next 返回 0
                // 当 index 超出索引，不能换完 即最后一种面值 i 张 不能换完，next 返回 -1
                // 当 index 未超出索引，应返回已经换过面值的 总张数，next 返回 res
                int next = process(arr, index + 1, aim - arr[index] * i);

                if (next != -1) {
                    res = res == -1 ? next + i : Math.min(res, next + i);
                }
            }
            return res;
        }
    }

    /**
     * @param arr 面值数组
     * @param aim 目标金额
     * @return 方法数
     * @description 动态规划 (枚举)
     */
    private static int process(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }
        int len = arr.length;
        int[][] dp = new int[len + 1][aim + 1];
        // 设置最后一排的值，除dp[N][0]为0 外，其他都是-1
        for (int col = 1; col <= aim; col++) {
            dp[len][col] = -1;
        }
        // 从底往上计算每一行
        for (int i = len - 1; i >= 0; i--) {
            // 每一行都从左往右
            for (int rest = 0; rest <= aim; rest++) {
                // 初始时先设置dp[i][rest]的值无效
                dp[i][rest] = -1;
                // 下面的值如果有效，先设置成下面的值
                if (dp[i + 1][rest] != -1) {
                    dp[i][rest] = dp[i + 1][rest];
                }
                // 如果左边的位置不越界且有效
                if (rest - arr[i] >= 0 && dp[i][rest - arr[i]] != -1) {
                    // 如果之前下面的值无效
                    if (dp[i][rest] == -1) {
                        dp[i][rest] = dp[i][rest - arr[i]] + 1;
                    } else {
                        // 如果下面和左边的值都有效，取最小的
                        dp[i][rest] = Math.min(dp[i][rest], dp[i][rest - arr[i]] + 1);
                    }
                }
            }
        }
        return dp[0][aim];
    }

    /**
     * @param arr 面值数组
     * @param aim 目标金额
     * @return 方法数
     * @description 动态规划 + 空间压缩
     */
    private static int process1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }
        int[] dp = new int[aim + 1];

        dp[0] = 0;
        for (int col = 1; col <= aim; col++) {
            dp[col] = -1;
        }

        for (int i = 1; i <= aim; i++) {
            for (int pre : arr) {
                if (pre <= i) {
                    if (dp[i] == -1) {
                        dp[i] = dp[i - pre] + 1;
                    } else {
                        dp[i] = Math.min(dp[i], dp[i - pre] + 1);
                    }
                }
            }
        }
        return dp[aim];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 3};
        int aim = 1;

        System.out.println(getMinCoins(arr, aim, 0));
        System.out.println(getMinCoins(arr, aim, 1));
        System.out.println(getMinCoins(arr, aim, 2));
    }
}
