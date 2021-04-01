package code.BitManipulation;

/**
 * @author Jevis Hoo
 * @date 2021/4/1 10:12
 * @description 四则运算
 */
public class Arithmetic {
    public static int plus(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }

        return sum;
    }

    public static int minus(int a, int b) {
        // 一个数的相反数就是这个数的二进制数表达取反加 1（补码）
        int negB = plus(~b, 1);
        return plus(a, negB);
    }

    public static int multi(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res = plus(res, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    public static int divide(int a, int b) {
        int x = a < 0 ? plus(~a, 1) : a;
        int y = b < 0 ? plus(~b, 1) : b;
        int res = 0;
        for (int i = 31; i > -1; i = minus(i, 1)) {
            if ((x >> i) >= y) {
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return a < 0 ^ b < 0 ? plus(~res, 1) : res;
    }

    public static void main(String[] args) {
        int a = 25, b = 5;
        System.out.println(plus(a, b));
        System.out.println(minus(a, b));
        System.out.println(multi(a, b));
        System.out.println(divide(a, b));
    }
}
