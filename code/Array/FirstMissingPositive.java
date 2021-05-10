package code.Array;

import java.util.Arrays;

/**
 * @author Jevis Hoo
 * @date 2021/5/10 19:10
 * @description 缺失的第一个正数
 */


public class FirstMissingPositive {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 0};
        System.out.println(firstMissingPositiveAdvisor((nums)));

        nums = new int[]{3, 4, -1, 1};
        System.out.println(firstMissingPositiveAdvisor((nums)));

        nums = new int[]{7, 8, 9, 11, 12};
        System.out.println(firstMissingPositiveAdvisor((nums)));

        nums = new int[]{1};
        System.out.println(firstMissingPositiveAdvisor((nums)));

        nums = new int[]{1, 1};
        System.out.println(firstMissingPositiveAdvisor((nums)));
    }

    public static int firstMissingPositive(int[] nums) {
        int[] res = new int[nums.length];

        for (int num : nums) {
            if (num <= nums.length && num > 0) {
                res[num - 1]++;
            }
        }

        System.out.println(Arrays.toString(res));

        for (int i = 0; i < nums.length; i++) {
            if (res[i] == 0) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    public static int firstMissingPositiveAdvisor(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        System.out.println(Arrays.toString(nums));

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
