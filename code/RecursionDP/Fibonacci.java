package code.RecursionDP;

/**
 * @author Jevis Hoo
 * @date 2021/2/23 10:20
 * @description 斐波那契数列问题的递归和动态规划
 * <p>
 * 补充问题 1：给定整数 N，代表台阶数，一次可以跨 2 个或者 1 个台阶，返回有多少种走法。
 * <p>
 * 补充问题 2：假设农场中成熟的母牛每年只会生 1 头小母牛，并且永远不会死。第一年农场有 1 只成熟的母牛，
 * 从第二年开始，母牛开始生小母牛。每只小母牛 3 年之后成熟又可以生小母牛。给定整数 N，求出 N 年后牛的数量。
 */
public class Fibonacci {
    /**
     * @param n num
     * @return res
     * @description $O(2 ^ N)$
     */
    public static long getFibonacci1(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        return getFibonacci1(n - 1) + getFibonacci1(n - 2);
    }

    /**
     * @param n num
     * @return res
     * @description O(N)
     */
    public static long getFibonacci2(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }
        long res = 1;
        long pre = 1;
        long tmp;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    /**
     * @param n num
     * @return res
     * @description O(logN) 矩阵快速幂
     * F(N)=F(N-1)+F(N-2)，初始项为 F(1)==1，F(2)==1，F(3)==2，F(4)==3
     * <p>
     * $(F(n), F(n-1))=(F(n-1), F(n-2)) \times\left|\begin{array}{cc}1 & 1 \\ 1 & 0\end{array}\right|
     * =(1,1) \times\left|\begin{array}{cc}1 & 1 \\ 1 & 0\end{array}\right|^{n-2}$
     */
    public static long getFibonacci3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        long[][] base = {{1, 1}, {1, 0}};
        long[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }

    /**
     * @param n num
     * @return res
     * @description 补充问题 1
     * S(n)=S(n-1)+S(n-2)，初始项为 S(1)==1，S(2)==2，S(3)==3，S(4)==5
     * 状态矩阵为 2*2
     */
    public static long getFibonacci4(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        long[][] base = {{1, 1}, {1, 0}};
        long[][] res = matrixPower(base, n - 2);
        //状态矩阵改变 2*2
        return 2 * res[0][0] + res[1][0];
    }

    /**
     * @param n num
     * @return res
     * @description 补充问题 2
     * C(n)=C(n-1)+C(n-3)，初始项为 C(1)==1，C(2)==2，C(3)==3，C(4)==4，C(5)==6
     * 状态矩阵为 3*3
     */
    public static long getFibonacci5(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        long[][] base = {{1, 1, 0}, {0, 0, 1}, {1, 0, 0}};
        long[][] res = matrixPower(base, n - 3);
        //状态矩阵改变
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }


    private static long[][] matrixPower(long[][] m, int p) {
        long[][] res = new long[m.length][m[0].length];
        // 先把res 设为单位矩阵，相当于整数中的1
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        long[][] tmp = m;
        while (p != 0) {
            if ((p & 1) != 0) {
                res = multiMatrix(res, tmp);
            }
            tmp = multiMatrix(tmp, tmp);
            p >>= 1;
        }
        return res;
    }

    private static long[][] multiMatrix(long[][] m1, long[][] m2) {
        long[][] res = new long[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int num = 20;
        long start1 = System.currentTimeMillis();
//        System.out.println(getFibonacci1(num));
        long end1 = System.currentTimeMillis();
        System.out.println("getFibonacci1: " + (end1 - start1) + "ns");

        long start2 = System.nanoTime();
        System.out.println(getFibonacci2(num));
        long end2 = System.nanoTime();
        System.out.println("getFibonacci2: " + (end2 - start2) + "ns");

        long start3 = System.nanoTime();
        System.out.println(getFibonacci3(num));
        long end3 = System.nanoTime();
        System.out.println("getFibonacci3: " + (end3 - start3) + "ns");
    }
}
