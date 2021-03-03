package code.RecursionDP;

import java.util.HashMap;

/**
 * @author Jevis Hoo
 * @date 2021/3/2 10:06
 * @description 换钱的方法数
 * <p>
 * 给定数组 arr，arr 中所有的值都为正数且不重复。每个值代表一种面值的货币，每种面值的货币可以使用任意张，
 * 再给定一个整数 aim，代表要找的钱数，求换钱有多少种方法。
 */
public class ChangeCoins {
    public static int getCoins(int[] arr, int aim, int way) {
        if (aim == 0) {
            return 0;
        }
        if (way == 0) {
            return process(arr, 0, aim);
        } else if (way == 1) {
            HashMap<String, Integer> map = new HashMap<>();
            return process(arr, 0, aim, map);
        } else if (way == 2) {
            return process(arr, aim);
        } else if (way == 3) {
            return process1(arr, aim);
        } else {
            return process2(arr, aim);
        }
    }

    /**
     * @param arr   面值数组
     * @param index 可以任意自由使用 index 及其之后的钱
     * @param aim   目标金额
     * @return 方法数
     * @description 暴力递归法
     */
    private static int process(int[] arr, int index, int aim) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int i = 0; arr[index] * i <= aim; i++) {
                res += process(arr, index + 1, aim - arr[index] * i);
            }
        }
        return res;
    }

    /**
     * @param arr   面值数组
     * @param index 第几张
     * @param aim   目标金额
     * @param map   缓存映射表
     * @return 方法数
     * @description 记忆化搜索方法
     */
    private static int process(int[] arr, int index, int aim, HashMap<String, Integer> map) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int i = 0; arr[index] * i <= aim; i++) {
                int nextAim = aim - arr[index] * i;
                String key = (index + 1) + "_" + nextAim;
                if (map.containsKey(key)) {
                    res += map.get(key);
                } else {
                    res += process(arr, index + 1, nextAim, map);
                }
            }
        }
        map.put(index + "_" + aim, res);
        return res;
    }

    /**
     * @param arr 面值数组
     * @param aim 目标金额
     * @return 方法数
     * @description 动态规划 (枚举)
     */
    private static int process(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];

        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }

        for (int j = 0; arr[0] * j <= aim; j++) {
            dp[0][arr[0] * j] = 1;
        }
        int num;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                num = 0;
                for (int k = 0; j - arr[i] * k >= 0; k++) {
                    num += dp[i - 1][j - arr[i] * k];
                }
                dp[i][j] = num;
            }
        }
        return dp[arr.length - 1][aim];
    }


    /**
     * @param arr 面值数组
     * @param aim 目标金额
     * @return 方法数
     * @description 动态规划 (非枚举)
     */
    private static int process1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];

        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }

        for (int j = 0; arr[0] * j <= aim; j++) {
            dp[0][arr[0] * j] = 1;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                //dp[i][j] = dp[i-1][j] + dp[i][j-arr[i]]
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
            }
        }
        return dp[arr.length - 1][aim];
    }

    /**
     * @param arr 面值数组
     * @param aim 目标金额
     * @return 方法数
     * @description 动态规划 + 空间压缩
     */
    private static int process2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[] dp = new int[aim + 1];
        for (int j = 0; arr[0] * j <= aim; j++) {
            dp[arr[0] * j] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[j] += j - arr[i] >= 0 ? dp[j - arr[i]] : 0;
            }
        }
        return dp[aim];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 3};
        int aim = 20;

        System.out.println(getCoins(arr, aim, 0));
        System.out.println(getCoins(arr, aim, 1));
        System.out.println(getCoins(arr, aim, 2));
        System.out.println(getCoins(arr, aim, 3));
        System.out.println(getCoins(arr, aim, 4));
    }
}
