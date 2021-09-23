package code.Array;

/**
 * @author Jevis Hoo
 * @since 2021/4/6 14:47
 * @description 连续子数组的最大和
 */
public class MaxSubArray {
    public static int maxSubArray(int[] nums) {
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            max = Math.max(max, nums[i]);

        }
        return max;
    }

    public static int maxSubArray1(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (int num : nums) {
            sum += num;
            max = Math.max(max, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(array));

        array = new int[]{-2};
        System.out.println(maxSubArray(array));

        array = new int[]{1, 1, -2};
        System.out.println(maxSubArray(array));
    }
}
