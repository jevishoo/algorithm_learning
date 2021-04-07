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
            int[] array = generateRandomArray(50, 100);
            int[] copyArray = copyArray(array);
            int[] copyArray1 = copyArray(array);

            biDirectionBubbleSort(array);
            Arrays.sort(copyArray);

            if (!isEqual(array, copyArray)) {
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
                    swap(arr, j, j + 1);
                    state = true;
                }
            }

            for (int j = i - 1; j >= index + 1; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
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
                    swap(arr, j, j + 1);
                    state = true;
                }
            }
            if (!state) {
                break;
            }
            state = false;
        }
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
