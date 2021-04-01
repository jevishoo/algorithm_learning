package code.BitManipulation;

/**
 * @author Jevis Hoo
 * @date 2021/4/1 10:51
 * @description 数值的整数次方（快速幂算法）
 */
public class Pow {
    public static double myPow(double x, int n) {
        if (x == 0) {
            return 0.0;
        }

        if (x == 1) {
            return 1.0;
        }

        long m = n;
        if (m < 0) {
            x = 1 / x;
            m = -m;
        }

        double result = 1.0;
        while (m > 0) {
            if ((m & 1) == 1) {
                result *= x;
            }

            x *= x;
            m >>= 1;
        }

        return result;
    }

    public static void main(String[] args) {
        int x = 2, n = -2147483648;
        System.out.println(myPow(x, n));
        x = 2;
        n = 4;
        System.out.println(myPow(x, n));
        x = 2;
        n = -4;
        System.out.println(myPow(x, n));
    }
}
