package code.Array;

/**
 * @author Jevis Hoo
 * @since 2021/5/9 22:06
 * @description 递增的三元子序列
 * <p>
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * <p>
 * 如果存在这样的三元组下标 (i, j, k)且满足 i < j < k ，
 * 使得nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false
 */
public class IncreasingTriplet {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 5, 0, 4, 6};
        System.out.println(increasingTriplet(arr));
    }

    public static boolean increasingTriplet(int[] nums) {
        int min = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= min) {
                min = num;
            } else if (num <= secondMin) {
                secondMin = num;
            } else {
                return true;
            }
        }
        return false;
    }
}
