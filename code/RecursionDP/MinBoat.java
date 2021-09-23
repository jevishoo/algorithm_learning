package code.RecursionDP;

import java.util.Arrays;

/**
 * @author Jevis Hoo
 * @since 2021/3/18 9:06
 * @description 最小船只问题
 * 给定一个数组 arr,长度为 N且每个值都是正数，代表 N个人的体重。再给定一个正数 limit，代表一艘船的重量。
 * 坐船规则如下：
 * >>> 每艘船最多只能坐两人
 * >>> 乘客的体重和不能超过 limit
 * 返回同时让这 N个人过河最少需要的船数。
 */
public class MinBoat {
    public static int getMinBoat(int[] arr, int limit) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);

        // 找到小于等于 limit的最后一个数（二分）
        int lessR = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= (limit >> 1)) {
                lessR = i;
                break;
            }
        }

        if (lessR == -1) {
            return arr.length;
        }

        // 左侧部分最后一个
        int lessIndex = lessR;
        // 右侧部分第一个
        int moreIndex = lessR + 1;
        // 左侧不能与右侧匹配的个数
        int lessUnused = 0;

        while (lessIndex >= 0) {
            int solved = 0;
            while (moreIndex < arr.length && arr[lessIndex] + arr[moreIndex] <= limit) {
                moreIndex++;
                solved++;
            }

            if (solved == 0) {
                lessUnused++;
                lessIndex--;
            } else {
                lessIndex = Math.max(-1, lessIndex - solved);
            }
        }

        // 左侧部分的总个数
        int lessAll = lessR + 1;
        // 左侧可以与右侧匹配共同坐船的个数
        int lessUsed = lessAll - lessUnused;
        // 右侧不能与左侧匹配的个数
        int moreUnsolved = arr.length - lessR - 1 - lessUsed;

        return lessUsed + ((lessUnused + 1) >> 1) + moreUnsolved;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 9, 5, 3, 1, 6, 8, 7, 6, 5, 7, 3, 8, 9, 9, 2, 5, 6, 6, 6, 2};
        System.out.println(arr.length);
        System.out.println(getMinBoat(arr, 10));
        System.out.println(getMinBoat(arr, 11));
        System.out.println(getMinBoat(arr, 12));
    }
}
