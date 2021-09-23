package code.Array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Jevis Hoo
 * @since 2021/3/21 11:25
 * @description 找到无序数组中最小的 k个数
 * 如果数组 arr的长度为 N，排序之后自然可以得到最小的 k个数，此时时间复杂度与排序的时间复杂度相同，均为 O(NlogN)。
 * 本题要求实现时间复杂度为 O(NlogK)和 O(N)的方法。
 */
public class KthMinNums {
    /**
     * @param array int[]
     * @param k     min num
     * @return int[k]
     * @description O(NlogK) 大根堆
     */
    public int[] getLeastNumbers(int[] array, int k) {
        if (k == 0 || array.length == 0) {
            return new int[0];
        }
        // 默认是小根堆，实现大根堆需要重写一下比较器。
        PriorityQueue<Integer> pq = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int num : array) {
            if (pq.size() < k) {
                pq.offer(num);
            } else if (num < pq.peek()) {
                pq.poll();
                pq.offer(num);
            }
        }

        // 返回堆中的元素
        int[] res = new int[pq.size()];
        int idx = 0;
        for (int num : pq) {
            res[idx++] = num;
        }
        return res;
    }

    /**
     * @param array int[]
     * @param k     min num
     * @return int[k]
     * @description partition 小于 等于 大于
     * 利用 partition思路，利用随机概率选择 "standard"，使时间复杂度可收敛到 O(N)
     */
    public static int[] getKthMinNumsByPartition(int[] array, int k) {
        if (k < 1) {
            return new int[0];
        }
        if (k == array.length) {
            return array;
        }

        int left = 0, right = array.length - 1;
        partition(array, left, right, k);

        int[] res = new int[k];
        System.arraycopy(array, 0, res, 0, k);
        return res;
    }

    private static void partition(int[] array, int left, int right, int k) {
        int standard = array[left + (int) (Math.random() * (right - left + 1))];
        System.out.println("standard: " + standard);

        int cur = left, less = left, more = right + 1;

        while (cur < more) {
            if (array[cur] < standard) {
                swap(array, less++, cur++);
            } else if (array[cur] > standard) {
                swap(array, cur, --more);
            } else {
                cur++;
            }
        }
        System.out.println(Arrays.toString(array));

        if (k < less) {
            partition(array, left, less, k);
        } else if (k > more) {
            partition(array, more, right, k);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    /**
     * @param array int[]
     * @param k     min num
     * @return int[k]
     * @description BFPRT算法
     * 类似于 partition，不同点在于选择 "standard"，并非是随机选择
     */
    public static int[] getKthMinNumsByBfprt(int[] array, int k) {
        if (k < 1) {
            return null;
        }
        if (k == array.length) {
            return array;
        }

        int minKth = getMinKthByBfprt(array, k);
        int[] res = new int[k];
        int index = 0;
        for (int i = 0; i != array.length; i++) {
            if (array[i] < minKth) {
                res[index++] = array[i];
            }
        }
        for (; index != res.length; index++) {
            res[index] = minKth;
        }
        return res;
    }

    private static int getMinKthByBfprt(int[] array, int k) {
        int[] copyArr = copyArray(array);
        return bfprt(copyArr, 0, copyArr.length - 1, k - 1);
    }


    /**
     * @param array 数组
     * @param begin 开始位置
     * @param end   终止位置
     * @return 在 array[begin...end]中，如果排序，返回 i 位置的数
     */
    public static int bfprt(int[] array, int begin, int end, int i) {
        if (begin == end) {
            return array[begin];
        }
        // 分组 + 组内排序 + 组成中位数数组 + 选出该数组的上中位数 pivot
        int pivot = medianOfMedians(array, begin, end);
        int[] pivotRange = partition1(array, begin, end, pivot);
        int[] x = partition2(array, begin, end, pivot);

        System.out.println("-----------");
        System.out.println(Arrays.toString(pivotRange));
        System.out.println(Arrays.toString(x));
        System.out.println("-----------");

        if (i >= pivotRange[0] && i <= pivotRange[1]) {
            return array[i];
        } else if (i < pivotRange[0]) {
            return bfprt(array, begin, pivotRange[0] - 1, i);
        } else {
            return bfprt(array, pivotRange[1] + 1, end, i);
        }
    }

    public static int[] partition2(int[] arr, int l, int r, int p) {
        int less = l - 1;
        int more = r + 1;
        while (l < more) {
            if (arr[l] < p) {
                swap(arr, ++less, l++);
            } else if (arr[l] > p) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    private static int[] partition1(int[] array, int left, int right, int standard) {
        System.out.println("standard: " + standard);
        int cur = left, less = left, more = right + 1;

        while (cur < more) {
            if (array[cur] < standard) {
                swap(array, less++, cur++);
            } else if (array[cur] > standard) {
                swap(array, cur, --more);
            } else {
                cur++;
            }
        }
        System.out.println(Arrays.toString(array));
        return new int[]{less, more - 1};
    }


    public static int medianOfMedians(int[] arr, int begin, int end) {
        int num = end - begin + 1;
        int offset = num % 5 == 0 ? 0 : 1;
        int[] mArr = new int[num / 5 + offset];
        for (int i = 0; i < mArr.length; i++) {
            int beginI = begin + i * 5;
            int endI = beginI + 4;
            mArr[i] = getMedian(arr, beginI, Math.min(end, endI));
        }
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    public static int getMedian(int[] arr, int begin, int end) {
        insertionSort(arr, begin, end);
        int sum = end + begin;
        int mid = (sum / 2) + (sum % 2);
        return arr[mid];
    }

    public static void insertionSort(int[] arr, int begin, int end) {
        for (int i = begin + 1; i != end + 1; i++) {
            for (int j = i; j != begin; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }


    public static int[] copyArray(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i != res.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }


    public static int[] generateArray(int num, int max) {
        int[] arr = new int[num];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] test = generateArray(10, 5);
        int[] copyArr = copyArray(test);

        System.out.println(Arrays.toString(test));
        System.out.println("==============================");

        int[] result = getKthMinNumsByPartition(test, 4);
        System.out.println("==============================");
        System.out.println(Arrays.toString(result));

        System.out.println("==================================");
        System.out.println(Arrays.toString(copyArr));
        result = getKthMinNumsByBfprt(copyArr, 4);
        System.out.println("==================================");
        System.out.println(Arrays.toString(result));
    }
}
