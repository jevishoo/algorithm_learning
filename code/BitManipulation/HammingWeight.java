package code.BitManipulation;

/**
 * @author Jevis Hoo
 * @date 2021/4/1 9:19
 * @description 二进制中1的个数
 */
public class HammingWeight {
    public static int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n &= n - 1;
        }
        return res;
    }

    public static int hammingWeight1(int n) {
        int res = 0;
        while (n != 0) {
            res += n & 1;
            n >>>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        //521
        int n = 00000000000000000000000000001011;
        System.out.println(hammingWeight(n));
        System.out.println(hammingWeight1(n));
    }
}
