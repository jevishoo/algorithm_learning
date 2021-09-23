package code.Array;

import java.util.Stack;

/**
 * 最短无序连续子数组
 * <p>
 * 给你一个整数数组 nums，你需要找出一个 连续子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序.
 * 请找出符合题意的最短子数组，并输出它的长度.
 *
 * @author Jevis Hoo
 * @since 2021/9/23 10:54
 */
public class FindUnsortedSubarray {
    private static int findUnsortedSubarray(int[] nums) {
        int length = nums.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int left = -1, right = -1;
        for (int i = 0; i < length; i++) {
            if (nums[i] < max) {
                right = i;
            } else {
                max = nums[i];
            }

            if (nums[length - i - 1] > min) {
                left = length - i - 1;
            } else {
                min = nums[length - i - 1];
            }
        }

        return left == -1 ? 0 : right - left + 1;
    }

    private static int findUnsortedSubarrayWithStack(int[] nums) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        int length = nums.length;
        int left = length, right = -1;
        for (int i = 0; i < length; i++) {
            while (!stack1.isEmpty() && nums[stack1.peek()] > nums[i]) {
                int ind = stack1.pop();
                if (ind < left) {
                    left = ind;
                }
            }
            stack1.add(i);

            while (!stack2.isEmpty() && nums[stack2.peek()] < nums[length - i - 1]) {
                int ind = stack2.pop();
                if (ind > right) {
                    right = ind;
                }
            }
            stack2.add(length - i - 1);
        }

        return left == nums.length ? 0 : right - left + 1;
    }

    public static void main(String[] args) {
        int[] array = {2, 6, 4, 8, 10, 9, 15};
        System.out.println(findUnsortedSubarray(array));
        System.out.println(findUnsortedSubarrayWithStack(array));

        array = new int[]{1, 2, 3, 4};
        System.out.println(findUnsortedSubarray(array));
        System.out.println(findUnsortedSubarrayWithStack(array));

        array = new int[]{1};
        System.out.println(findUnsortedSubarray(array));
        System.out.println(findUnsortedSubarrayWithStack(array));

        array = new int[]{1, 3, 2, 2, 2};
        System.out.println(findUnsortedSubarray(array));
        System.out.println(findUnsortedSubarrayWithStack(array));

        array = new int[]{1, 2, 3, 3, 3};
        System.out.println(findUnsortedSubarray(array));
        System.out.println(findUnsortedSubarrayWithStack(array));
    }
}
