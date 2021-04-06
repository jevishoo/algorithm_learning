package code.Array;

import java.util.Arrays;

/**
 * @author Jevis Hoo
 * @date 2021/4/6 12:47
 * @description 调整数组顺序使奇数位于偶数前面
 */
public class ExchangeArray {
    public static int[] exchange(int[] nums) {
        int[] res = new int[nums.length];
        int lIndex = 0, rIndex = nums.length - 1;
        for (int num : nums) {
            if (num % 2 == 1) {
                res[lIndex++] = num;
            } else {
                res[rIndex--] = num;
            }
        }

        return res;
    }

    // 首尾双指针
    public static int[] exchange1(int[] nums) {
        int lIndex = 0, rIndex = nums.length - 1;


        while (lIndex < rIndex) {
            if (nums[lIndex] % 2 != 0) {
                lIndex++;
                continue;
            }

            if (nums[rIndex] % 2 != 1) {
                rIndex--;
                continue;
            }

            swap(nums, lIndex, rIndex);
        }

        return nums;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5};

        System.out.println(Arrays.toString(exchange1(array)));
        array = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.toString(exchange1(array)));
    }
}
