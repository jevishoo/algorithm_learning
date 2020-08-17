package code.Stack_Queue;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Date 2020/8/17 17:53
 * @Created by Jevis_Hoo
 * @Description 生成窗口最大值数组
 * 有一个整型数组arr 和一个大小为w 的窗口从数组的最左边滑到最右边，窗口每次向右边滑一个位置。
 * 如果数组长度为n，窗口大小为w，则一共产生n-w+1 个窗口的最大值。
 * 例如，数组为[4,3,5,4,3,3,6,7]，窗口大小为3 时：
 * [4 3 5] 4 3 3 6 7 窗口中最大值为5
 * 4 [3 5 4] 3 3 6 7 窗口中最大值为5
 * 4 3 [5 4 3] 3 6 7 窗口中最大值为5
 * 4 3 5 [4 3 3] 6 7 窗口中最大值为4
 * 4 3 5 4 [3 3 6] 7 窗口中最大值为6
 * 4 3 5 4 3 [3 6 7] 窗口中最大值为7
 * <p>
 * 输入：整型数组arr，窗口大小为w。
 * 输出：一个长度为n-w+1 的数组res，res[i]表示每一种窗口状态下的最大值。
 * <p>
 * 对数器的使用
 */
public class WindowMax {
    public static int[] getWindowMax(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }

        int[] res = new int[arr.length - w + 1];
        LinkedList<Integer> maxL = new LinkedList<>();
        /*
        容易想到用时间复杂度为O(n×w)的方法来解决问题，
        但是在考虑时间复杂度低的情况下，用空间复杂度替代时间复杂度
        实现时间复杂度为O(n)的方法
         */
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!maxL.isEmpty() && arr[maxL.peekLast()] <= arr[i]) {
                maxL.pollLast();
            }
            maxL.addLast(i);

            if (maxL.peekFirst() == i - w) {
                maxL.pollFirst();
            }

            if (i >= w - 1) {
                res[index++] = arr[maxL.peekFirst()];
            }
        }
        return res;
    }

    public static int[] comparator(int[] arr, int w) {
        int[] res = new int[arr.length - w + 1];

        for (int i = 0; i < arr.length - w + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + w; j++) {
                if (arr[j] > max) {
                    max = arr[j];
                }
            }
            res[i] = max;
        }
        return res;
    }

    public static int[] generateRandomArray(int minSize, int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize - minSize + 1) * Math.random()) + minSize];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, arr.length);
        return res;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int w = 3;

        int testTime = 5000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(w, maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            int[] res1 = getWindowMax(arr1, w);
            int[] res2 = comparator(arr2, w);

            if (!isEqual(res1, res2)) {
                succeed = false;
                System.out.println(Arrays.toString(arr3));
                System.out.println("=============================");
                System.out.println(Arrays.toString(res1));
                System.out.println(Arrays.toString(res2));
                System.out.println("=============================");
                System.out.println(i);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Terrible!");

        int[] arr = generateRandomArray(w, maxSize, maxValue);
        System.out.println(Arrays.toString(arr));
        int[] winMax = getWindowMax(arr, w);
        System.out.println(Arrays.toString(winMax));
    }
}
