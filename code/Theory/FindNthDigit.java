package code.Theory;

/**
 * @author Jevis Hoo
 * @since 2021/4/23 15:09
 * @description 数字序列中某一位的数字
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 * 请写一个函数，求任意第n位对应的数字。
 */
public class FindNthDigit {
    public static void main(String[] args) {
        System.out.println(findNthDigit(3));
        System.out.println(findNthDigit(10));
        System.out.println(findNthDigit(11));
        System.out.println(findNthDigit(19));
    }

    public static int findNthDigit(int n) {
        if (n < 10) {
            return n;
        }

        int digit = 1;
        long start = 1;
        long count = 9;

        while (n > count) {
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }

        long num = start + (n - 1) / digit;

        return String.valueOf(num).charAt((n - 1) % digit) - '0';
    }

    public static int comparator(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;

        while (n > count) {
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit;
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }
}
