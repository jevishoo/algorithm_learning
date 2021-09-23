package code.Sort;

import java.util.Arrays;

/**
 * @author Jevis Hoo
 * @since 2021/5/9 10:01
 * @description
 */
public class MergeSort {

    public static void main(String[] args) {
        int index = 0;
        boolean flag = true;
        long start = System.nanoTime();
        while (index++ < 500000) {
            int[] array = Utils.generateRandomArray(50, 100);
            int[] copyArray = Utils.copyArray(array);
            int[] copyArray1 = Utils.copyArray(array);

            mergeSort(array);
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

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];

        int index = 0, p1 = l, p2 = m + 1;

        while (p1 <= m && p2 <= r) {
            help[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= m) {
            help[index++] = arr[p1++];
        }
        while (p2 <= r) {
            help[index++] = arr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }
}
