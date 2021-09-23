package code.BitManipulation;

/**
 * @author Jevis Hoo
 * @since 2021/4/1 9:30
 * @description 不用做任何比较判断找出两个数中较大的数
 */
public class GetMax {
    public static int getMax(int a, int b) {
        int c = a - b;
        // 得到 a-b值的符号
        int cSign = sign(c);
        // 对 a-b值取反
        int cSignCon = flip(cSign);
        return a * cSign + b * cSignCon;
    }

    public static int sign(int n) {
        // n >> 31：获得符号位 正数和 0返回 0，负数则返回1
        // & 1：正数和 0返回 0，负数则返回1
        // flip：数取反
        return flip((n >> 31) & 1);
    }

    private static int flip(int n) {
        return n ^ 1;
    }

    public static int getMax1(int a, int b) {
        // c 可能有越界错误
        int c = a - b;
        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);
        // a，b同符号（正、负），返回 0，否则返回 1
        int difSab = sa ^ sb;
        // a，b同符号（正、负），返回 1，否则返回 0（对difSab取反）
        int sameSab = flip(difSab);
        // 不同符号时，difSab=1，sameSab=0，sa为 1则返回 a；
        // 同符号时，difSab=0，sameSab=1，sc为 1则返回 a（a-b>0）
        int returnA = difSab * sa + sameSab * sc;
        // 对returnA取反
        int returnB = flip(returnA);
        return a * returnA + b * returnB;
    }

    public static void main(String[] args) {
        System.out.println(getMax(1, 4));
        System.out.println(getMax(1, -4));
        System.out.println(getMax1(1, 4));
        System.out.println(getMax1(1, -4));
    }
}
