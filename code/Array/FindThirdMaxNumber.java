package code.Array;

/**
 * @author Jevis Hoo
 * @since 2021/9/23 10:30
 * @description 第三大的数
 */
public class FindThirdMaxNumber {
    private static int findThirdMaxNumber(int[] nums) {
        long max = Long.MIN_VALUE, second = Long.MIN_VALUE, third = Long.MIN_VALUE;
        for (int num : nums) {
            if (num == max || num == second || num == third) {
                continue;
            }
            if (num > max) {
                third = second;
                second = max;
                max = num;
            } else if (num > second) {
                third = second;
                second = num;
            } else if (num > third) {
                third = num;
            }
        }
        return third == Long.MIN_VALUE ? (int) max : (int) third;
    }

    public static void main(String[] args) {
        int[] array = {2, 2, 3, 1};
        System.out.println(findThirdMaxNumber(array));
    }
}
