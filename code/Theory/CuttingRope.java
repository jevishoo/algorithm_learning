package code.Theory;

/**
 * @author Jevis Hoo
 * @date 2021/3/31 17:34
 * @description 剪绳子
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 */
public class CuttingRope {
    public static int cuttingRope(int n) {
        int result = 1;
        for (int i = 2; i < n; i++) {
            int seg = n / i, remain = n % i;
            int max = (int) Math.pow(seg + 1, remain) * (int) Math.pow(seg, i - remain);
            result = Math.max(result, max);
        }
        return result;
    }

    public static int cuttingRope1(int n) {
        if (n <= 3) {
            return n - 1;
        }

        int a = n / 3, b = n % 3;
        if (b == 0) {
            return (int) Math.pow(3, a);
        }
        if (b == 1) {
            return (int) Math.pow(3, a - 1) * 4;
        }
        return (int) Math.pow(3, a) * 2;
    }

    public static int cuttingRope2(int n) {
        if (n <= 3) {
            return n - 1;
        }

        long res = 1L;
        int p = 1000000007;

        while (n > 4) {
            res = res * 3 % p;
            n -= 3;
        }

        return (int) (res * n % p);
    }


    public static void main(String[] args) {
        int n = 120;
        System.out.println(cuttingRope(n));
        System.out.println(cuttingRope1(n));
        System.out.println(cuttingRope2(n));
    }
}
