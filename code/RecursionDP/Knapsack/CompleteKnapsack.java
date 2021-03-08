package code.RecursionDP.Knapsack;

/**
 * @author Jevis Hoo
 * @date 2021/3/4 10:58
 * @description 完全背包问题
 * <p>
 * 有 N 种物品和一个容量是 V 的背包，每种物品都有无限件可用。
 * 第 i 种物品的体积是 vi，价值是 wi。
 * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
 */
public class CompleteKnapsack {
    private static int solution(int n, int v, int[] value, int[] weight) {
        return process1(n, v, value, weight);
    }

    /**
     * @description 动态规划
     * f[i][j] : 前 i 种物品,总体积是 j 的情况下，总价值最大是多少。
     * f[i][j] = max{f[i-1][j-k*weight[i]] + k * value[i] | 0 <= k * weight[i] <= v}
     * f[i][j] = max{f[i-1][j], f[i][j-weight[i]] + value[i]}
     * <p>
     * 优化：
     * f[j] : 总体积是 j 的情况下，总价值最大是多少。
     */
    private static int process1(int n, int v, int[] value, int[] weight) {
        // 一维数组方法
        int[] func = new int[v + 1];

        //注意边界问题，i是从1开始的，j是从总容量i背包的重量开始递增
        for (int i = 1; i <= n; i++) {
            for (int j = weight[i]; j <= v; j++) {
                //如果容量为j的背包放得下第i个物体
                func[j] = Math.max(func[j - weight[i]] + value[i], func[j]);
            }
        }
        //打印所有结果，我们要求的是F[N]
        return func[v];
    }

    public static void main(String[] args) {
        int[] weight = new int[]{0, 1, 2, 3, 4};
        int[] value = new int[]{0, 2, 4, 4, 5};

        int n = 4, v = 5;
        System.out.println(solution(n, v, value, weight));
    }


}
