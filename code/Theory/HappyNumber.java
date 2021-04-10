package code.Theory;

/**
 * @author Jevis Hoo
 * @date 2021/4/10 10:30
 * @description 快乐数
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果 可以变为 1，那么这个数就是快乐数。
 */
public class HappyNumber {
    public static boolean isHappy(int n) {
        int slow = n, fast = squareSum(n);

        while (slow != fast) {
            slow = squareSum(slow);
            fast = squareSum(squareSum(fast));
        }

        return slow == 1;
    }

    private static int squareSum(int n) {
        int sum = 0;
        while (n != 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(isHappy(16));
        System.out.println(isHappy(99999));
    }
}
