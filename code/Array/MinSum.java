package code.Array;

/**
 * @author Jevis Hoo
 * @since 2021/5/9 10:27
 * @description 数组小和
 * <p>
 * 在一个数组中，每个数左边比当前数小的数累加起来，叫作这个数组的小和。
 * 求一个数组的小和。
 */
public class MinSum {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 4, 2, 5};
        System.out.println(getMinSum(arr));
    }

    public static int getMinSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return getMinSum(arr, 0, arr.length - 1);
    }

    private static int getMinSum(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }

        int mid = l + ((r - l) >> 1);

        return getMinSum(arr, l, mid)
                + getMinSum(arr, mid + 1, r)
                + merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int sum = 0;
        int index = 0, p1 = l, p2 = m + 1;

        while (p1 <= m && p2 <= r) {
            if (arr[p1] > arr[p2]) {
                help[index++] = arr[p2++];
            } else {
                sum += arr[p1] * (r - p2 + 1);
                help[index++] = arr[p1++];
            }
        }

        while (p1 <= m) {
            help[index++] = arr[p1++];
        }
        while (p2 <= r) {
            help[index++] = arr[p2++];
        }

        if (help.length >= 0) {
            System.arraycopy(help, 0, arr, l, help.length);
        }

        return sum;
    }
}
