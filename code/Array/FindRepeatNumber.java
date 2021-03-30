package code.Array;

/**
 * @author Jevis Hoo
 * @date 2021/3/29 9:34
 * @description 数组中重复的数字
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 */
public class FindRepeatNumber {
    public static int findRepeatNumber(int[] nums) {
        int[] test = new int[nums.length];

        for (int num : nums) {
            if (test[num] == 0) {
                test[num]++;
            } else {
                return num;
            }
        }
        return -1;
    }

    public static int findRepeatNumber2(int[] nums) {
        int help;
        // 一个萝卜一个坑
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                help = nums[i];
                nums[i] = nums[help];
                nums[help] = help;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = new int[]{
                2, 3, 1, 0, 2, 5, 3
        };

        System.out.println(findRepeatNumber(array));
        System.out.println(findRepeatNumber2(array));
    }
}
