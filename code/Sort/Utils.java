package code.Sort;

/**
 * @author Jevis Hoo
 * @date 2021/5/9 10:02
 * @description
 */
public class Utils {
    public static void swap(int[] arr, int cur, int right) {
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
