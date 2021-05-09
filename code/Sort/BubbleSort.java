package code.Sort;

import java.util.Arrays;

/**
 * @author Jevis Hoo
 * @date 2021/4/7 21:27
 * @description 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int index = 0;
        boolean flag = true;
        long start = System.nanoTime();
        while (index++ < 500000) {
            int[] array = Utils.generateRandomArray(50, 100);
            int[] copyArray = Utils.copyArray(array);
            int[] copyArray1 = Utils.copyArray(array);

            biDirectionBubbleSort(array);
            Arrays.sort(copyArray);

            if (!Utils.isEqual(array, copyArray)) {
                System.out.println("origin array : " + Arrays.toString(copyArray1));
                System.out.println("incorrect sort : " + Arrays.toString(array));
                System.out.println("correct sort : " + Arrays.toString(copyArray));
                flag = false;
                break;
            }
        }
        long end = System.nanoTime();

        if (flag) {
            System.out.println("success and time use " + (end - start));
        }
    }

    public static void biDirectionBubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        boolean state = false;
        int index = 0;
        for (int i = arr.length - 1; i >= 1; i--) {
            for (int j = index; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    Utils.swap(arr, j, j + 1);
                    state = true;
                }
            }

            for (int j = i - 1; j >= index + 1; j--) {
                if (arr[j] < arr[j - 1]) {
                    Utils.swap(arr, j, j - 1);
                    state = true;
                }
            }
            index++;
            if (!state) {
                break;
            }
            state = false;
        }
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        boolean state = false;
        for (int i = arr.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    Utils.swap(arr, j, j + 1);
                    state = true;
                }
            }
            if (!state) {
                break;
            }
            state = false;
        }
    }
}
