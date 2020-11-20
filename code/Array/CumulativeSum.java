package code.Array;

import java.util.HashMap;

/**
 * @Date 2020/11/18 10:12
 * @Created by Jevis_Hoo
 * @Description 累加和为给定值的最长子数组长度
 */
public class CumulativeSum {
    //未排序正数数组中累加和为给定值的最长子数组长度
    public static int getMaxLengthWithPositiveSortedArray(int[] arr, int k) {
        int left = 0;
        int right = 0;
        int maxLength = 0;
        int sum = arr[0];

        while (right < arr.length) {
            if (sum == k) {
                maxLength = Math.max(maxLength, right - left + 1);
                sum -= arr[left++];
            } else if (sum < k) {
                right++;
                if (right == arr.length)
                    break;
                sum += arr[right];
            } else {
                sum -= arr[left++];
            }
        }

        return maxLength;
    }

    //未排序数组中累加和为给定值的最长子数组长度
    public static int getMaxLengthWithSortedArray(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxLength = 0;
        map.put(0, -1);// 注意
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                maxLength = Math.max(maxLength, i - map.get(sum - k));
            }
            map.putIfAbsent(sum, i);
//            if (!map.containsKey(sum)) {
//                map.put(sum, i);
//            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 3, -1, 3, 1, 5, 6, -3, -5, 1, 5, 3, -2, -7, 1, 3};
        System.out.println(getMaxLengthWithPositiveSortedArray(array, 6));
        System.out.println(getMaxLengthWithSortedArray(array, 6));
    }
}
