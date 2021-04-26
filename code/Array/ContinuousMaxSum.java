package code.Array;

/**
 * @author Jevis Hoo
 * @date 2021/4/26 11:40
 * @description 查找数组里连续两个数之和最大的数
 */
public class ContinuousMaxSum {

    public static int getMaxSum(int[] arr) {
        int slow = 0, fast = 1;
        int pre = arr[slow];
        int sum, max = 0;
        while (fast < arr.length) {
            if (arr[fast] > pre) {
                sum = arr[slow] + arr[fast];
                max = Math.max(sum, max);
            }
            pre = arr[slow];
            fast++;
            slow++;
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 6, 2, 4, 5, 3, 7};
        System.out.println(getMaxSum(arr));
    }
}
