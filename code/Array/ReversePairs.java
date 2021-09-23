package code.Array;

/**
 * @author Jevis Hoo
 * @since 2021/5/9 10:12
 * @description 数组中的逆序对
 * <p>
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组，求出这个数组中的逆序对的总数。
 */
public class ReversePairs {
    public static void main(String[] args) {
        int[] arr = new int[]{7, 5, 6, 4};
        System.out.println(getReversePairs(arr));
    }

    public static int getReversePairs(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return getReversePairs(arr, 0, arr.length - 1);
    }

    private static int getReversePairs(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }

        int mid = l + ((r - l) >> 1);

        return getReversePairs(arr, l, mid)
                + getReversePairs(arr, mid + 1, r)
                + merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int count = 0;
        int index = 0, p1 = l, p2 = m + 1;

        while (p1 <= m && p2 <= r) {
            if (arr[p1] > arr[p2]) {
                count += m - p1 + 1;
                help[index++] = arr[p2++];
            } else {
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

        return count;
    }
}
