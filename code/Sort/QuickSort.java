package code.Sort;

import java.util.Arrays;

/**
 * @author Jevis Hoo
 * @since 2021/4/3 13:48
 * @description QuickSort
 */
public class QuickSort {
    public static void main(String[] args) {
        int index = 0;
        boolean flag = true;
        while (index++ < 50000) {
            int[] array = generateRandomArray(10, 20);
            int[] copyArray = copyArray(array);
            int[] copyArray1 = copyArray(array);

            quickSort(array);
            Arrays.sort(copyArray);

            if (!isEqual(array, copyArray)) {
                System.out.println("origin array : " + Arrays.toString(copyArray1));
                System.out.println("incorrect sort : " + Arrays.toString(array));
                System.out.println("correct sort : " + Arrays.toString(copyArray));
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("success");
        }
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        // l = c = 0, r = length
        quickSort(arr, 0, arr.length);
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int[] scale = partition(arr, left, right);
            quickSort(arr, left, scale[0]);
            quickSort(arr, scale[1], right);
        }
    }

    private static int[] partition(int[] arr, int left, int right) {
        int standard = arr[left + (int) (Math.random() * (right - left))];

        int cur = left;
        while (cur < right) {
            if (arr[cur] == standard) {
                cur++;
            } else if (arr[cur] < standard) {
                if (cur != left) {
                    swap(arr, left, cur);
                }
                left++;
                cur++;
            } else {
                swap(arr, cur, --right);
            }
        }

        return new int[]{left, right};
    }

    private static void swap(int[] arr, int cur, int right) {
        int temp = arr[cur];
        arr[cur] = arr[right];
        arr[right] = temp;
    }

    /**
     * @description for test
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
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
        if (arr1 == null && arr2 != null) {
            return false;
        }

        if (arr1 != null && arr2 == null) {
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
}
