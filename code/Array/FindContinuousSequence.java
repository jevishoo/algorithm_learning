package code.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jevis Hoo
 * @since 2021/5/14 10:32
 * @description 和为s的连续正数序列
 */
public class FindContinuousSequence {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(findContinuousSequence(9)));
        System.out.println(Arrays.deepToString(findContinuousSequence(36)));
    }

    public static int[][] findContinuousSequence(int target) {
        // 滑动窗口的左边界
        int i = 1;
        // 滑动窗口的右边界
        int j = 1;
        // 滑动窗口中数字的和
        int sum = 0;

        List<int[]> res = new ArrayList<>();

        while (i <= target / 2) {
            if (sum < target) {
                // 右边界向右移动
                sum += j;
                j++;
            } else if (sum > target) {
                // 左边界向右移动
                sum -= i;
                i++;
            } else {
                // 记录结果
                int[] arr = new int[j - i];
                for (int k = i; k < j; k++) {
                    arr[k - i] = k;
                }
                res.add(arr);
                // 左边界向右移动 向右移动一位必定排除
                sum -= 2 * i + 1;
                i += 2;
            }
        }

        return res.toArray(new int[res.size()][]);
    }


    public static int[][] findContinuousSequence1(int target) {
        // 滑动窗口的左边界
        int i = 1;
        // 滑动窗口的右边界
        int j = 1;
        // 滑动窗口中数字的和
        int sum;

        List<int[]> res = new ArrayList<>();

        while (i <= target / 2) {
            //等差数列求和公式求sum
            sum = (i + j) * (j - i + 1) / 2;
            if (sum < target) {
                // 右边界向右移动
                j++;
            } else if (sum > target) {
                // 左边界向右移动
                i++;
            } else {
                // 记录结果
                int[] arr = new int[j - i + 1];
                for (int k = i; k <= j; k++) {
                    arr[k - i] = k;
                }
                res.add(arr);
                // 左边界向右移动 向右移动一位必定排除
                i += 2;
            }
        }

        return res.toArray(new int[res.size()][]);
    }
}
