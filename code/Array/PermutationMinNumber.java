package code.Array;

import java.util.Arrays;

/**
 * @author Jevis Hoo
 * @since 2021/4/24 9:25
 * @description 把数组排成最小的数
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 */
public class PermutationMinNumber {
    public static String getMinNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        quickSort(strs, 0, nums.length);
        System.out.println(Arrays.toString(strs));
        StringBuilder res = new StringBuilder();
        for (String str : strs) {
            res.append(str);
        }
        return res.toString();
    }

    private static void quickSort(String[] strs, int left, int right) {
        if (left < right) {
            int[] scale = partition(strs, left, right);
            quickSort(strs, left, scale[0]);
            quickSort(strs, scale[1], right);
        }
    }

    private static int[] partition(String[] strs, int left, int right) {
        String standard = strs[left + (int) (Math.random() * (right - left))];

        int cur = left;
        while (cur < right) {
            if (strs[cur].equals(standard)) {
                cur++;
            } else if ((strs[cur] + standard).compareTo(standard + strs[cur]) < 0) {
                if (cur != left) {
                    swap(strs, left, cur);
                }
                left++;
                cur++;
            } else {
                swap(strs, cur, --right);
            }
        }
        return new int[]{left, right};
    }

    private static void swap(String[] strs, int left, int right) {
        String tmp = strs[left];
        strs[left] = strs[right];
        strs[right] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 30, 34, 5, 29, 22};
        System.out.println(getMinNumber(nums));
    }
}
