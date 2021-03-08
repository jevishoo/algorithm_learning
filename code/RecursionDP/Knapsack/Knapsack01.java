package code.RecursionDP.Knapsack;

/**
 * @author Jevis Hoo
 * @date 2021/3/4 10:58
 * @description 01 背包问题
 * <p>
 * 有 n 件物品和一个容量是 v 的背包。每件物品只能使用一次。
 * 第 i 件物品的体积是 vi，价值是 wi。
 * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
 */
public class Knapsack01 {


    public static int solution(int n, int v, int[] value, int[] weight, int way) {
        if (way == 0) {
            return process(n, v, value, weight);
        } else {
            return process1(n, v, value, weight);
        }
    }

    /**
     * @description 动态规划
     * f[i][j] : 只看前 i 个物品，总体积是 j 的情况下，总价值最大是多少。
     * result : max{f[n][0-v]}
     * <p>
     * f[i][j] :
     * >>> 1. 不选第 i 个物品时，f[i][j] = f[i-1][j]
     * >>> 2. 选第 i 个物品时，f[i][j] = f[i-1][j-weight[i]] + value[i]
     * f[i][j] = max{1,2}
     * <p>
     * >>>初始化 : f[0][0] = 0
     */
    private static int process(int n, int v, int[] value, int[] weight) {
        // 二维数组方法
        int[][] func = new int[n + 1][v + 1];

        //注意边界问题，i是从1开始的，j是从0开始的
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= v; j++) {
                //如果容量为j的背包放得下第i个物体
                if (j >= weight[i]) {
                    func[i][j] = Math.max(func[i - 1][j - weight[i]] + value[i], func[i - 1][j]);
                } else {
                    //放不下，只能选择不放第i个物体
                    func[i][j] = func[i - 1][j];
                }
            }
        }

        //打印所有结果，我们要求的是F[N][V]
        return func[n][v];
    }

    /**
     * @description 动态规划
     * f[j] : 总体积是 j 的情况下，总价值最大是多少。
     */
    private static int process1(int n, int v, int[] value, int[] weight) {
        // 一维数组方法
        int[] func = new int[v + 1];

        //注意边界问题，i是从1开始的，j是从总容量v开始递减
        for (int i = 1; i <= n; i++) {
            for (int j = v; j >= weight[i]; j--) {
                //如果容量为 j 的背包放得下第 i 个物体
                func[j] = Math.max(func[j - weight[i]] + value[i], func[j]);
            }
        }

        //打印所有结果，我们要求的是F[N]
        return func[v];
    }


    public static void main(String[] args) {
        int[] weight = new int[]{0, 1, 2, 3, 4};
        int[] value = new int[]{0, 2, 4, 4, 5};

        System.out.println(solution(4, 5, value, weight, 0));
        System.out.println(solution(4, 5, value, weight, 1));
    }
}

