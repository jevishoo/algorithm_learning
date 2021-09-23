package code.Theory;

/**
 * @author Jevis Hoo
 * @since 2021/4/6 15:26
 * @description 1～n 整数中 1 出现的次数
 */
public class CountDigitOne {
    public static int countDigitOne(int n) {
        int res = 0;

        // 位次，高位，当前位，低位
        int digit = 1, high = n / 10, cur = n % 10, low = 0;

        while (high != 0 || cur != 0) {
            if (cur == 0) {
                // 当前位为0时，如205，所有可能为 110-119，11-19
                res += high * digit;
            } else if (cur == 1) {
                // 当前位为1时，如215，所有可能为 110-119，11-19，210-215
                res += high * digit + low + 1;
            } else {
                // 当前位为其他数(3)时，如235，所有可能为 210-219，110-119，11-19
                res += (high + 1) * digit;
            }

            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(countDigitOne(12));
        System.out.println(countDigitOne(13));
//        System.out.println(countDigitOne(1201));
//        System.out.println(countDigitOne(2304));
    }
}
