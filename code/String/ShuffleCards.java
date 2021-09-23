package code.String;

import java.util.Arrays;

/**
 * @author Jevis Hoo
 * @since 2021/3/11 9:52
 * @description 完美洗牌问题
 * >>> 1
 * 给定一个长度为偶数的数组 arr，长度记为 2×N。前 N 个为左部分，后 N 个为右部分。
 * arr就可以表示为 {L1,L2,..,Ln,R1,R2,..,Rn}，请将数组调整成 {R1,L1,R2,L2,..,Rn,Ln}的样子。
 * <p>
 * 要求时间复杂度为 O(N)，额外空间复杂度为 O(1)。
 * <p>
 * >>> 2
 * 进阶问题：给定一个数组 arr，请将数组调整为依次相邻的数字总是先 <=、再 >=的关系，
 * 并交替下去。比如数组中有五个数字，调整成 {a,b,c,d,e}，使之满足 a<=b>=c<=d>=e
 * <p>
 * 要求时间复杂度为 O(NlogN)，额外空间复杂度为 O(1)。
 */
public class ShuffleCards {
    /**
     * @param arr 数组
     * @description O(logN)
     */
    public static void shuffleCards(int[] arr) {
        int length = arr.length >> 1;
        int start = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i; j++) {
                swap(arr, start + i + j, length + j);
            }
        }
    }


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * @param arr 数组
     * @description O(logN)
     */
    public static void shuffle(int[] arr) {
        if (arr != null && arr.length != 0 && (arr.length & 1) == 0) {
            int start = 0;
            shuffle(arr, start, arr.length - 1);
        }
    }

    private static void shuffle(int[] arr, int left, int right) {
        // 切成一块一块的解决，每一块的长度满足(3^k)-1
        while (right - left + 1 > 0) {
            int len = right - left + 1;
            int base = 3;
            int k = 1;
            // 计算小于或等于len 且距离len 最近的，满足(3^k)-1 的数
            // 也就是找到最大的k，满足3^k <= len+1
            while (base <= (len + 1) / 3) {
                base *= 3;
                k++;
            }
            // 当前要解决长度为base-1 的块，一半就是再除以2
            int half = (base - 1) / 2;
            // [left..right]的中点位置
            int mid = (left + right) / 2;
            // 要旋转的左部分为[left+half...mid], 右部分为arr[mid+1..mid+half]
            // 注意，这里arr 下标是从0 开始的
            rotate(arr, left + half, mid, mid + half);
            //旋转完成后，从L 开始算起，长度为base-1 的部分进行下标连续推
            cycles(arr, left, base - 1, k);
            // 解决了前base-1 的部分，剩下的部分继续处理
            left = left + base - 1;
        }
    }


    public static void cycles(int[] arr, int start, int len, int k) {
        // 从start 位置开始，往右len 的长度这一段做下标连续推
        // 出发位置依次为1,3,9...
        // 找到每一个出发位置trigger，一共k 个
        // 每一个trigger 都进行下标连续推
        // 出发位置是从1 开始算的，而数组下标是从0 开始算的
        for (int i = 0, trigger = 1; i < k; i++, trigger *= 3) {
            int preValue = arr[trigger + start - 1];
            int cur = modifyIndex(trigger, len);
            while (cur != trigger) {
                int tmp = arr[cur + start - 1];
                arr[cur + start - 1] = preValue;
                preValue = tmp;
                cur = modifyIndex(cur, len);
            }
            arr[cur + start - 1] = preValue;
        }
    }

    private static int modifyIndex(int i, int len) {
        return (2 * i) % (len + 1);
    }

    public static void rotate(int[] arr, int left, int mid, int right) {
        // [left..mid]为左部分，[mid+1..right]为右部分，左右两部分互换
        reverse(arr, left, mid);
        reverse(arr, mid + 1, right);
        reverse(arr, left, right);
    }

    public static void reverse(int[] arr, int left, int right) {
        // [left..right]做逆序调整
        while (left < right) {
            int tmp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = tmp;
        }
    }


    /**
     * @param arr 数组
     * @description 进阶问题
     */
    public static void wiggleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        //需改用堆排序
        //heapSort(arr);
        Arrays.sort(arr);
        if ((arr.length & 1) == 1) {
            // [1,2,3,4,5] --> [1,4,2,5,3]
            shuffle(arr, 1, arr.length - 1);
        } else {
            // [1,2,3,4,5,6] --> [4,1,5,2,6,3]
            shuffle(arr, 0, arr.length - 1);
            // [4,1,5,2,6,3] --> [1,4,2,5,3,6]
            for (int i = 0; i < arr.length; i += 2) {
                int tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 6, 8, 1, 3, 5, 7};
        System.out.println(Arrays.toString(arr));
        shuffleCards(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[]{2, 4, 6, 8, 1, 3, 5, 7};
        shuffle(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[]{2, 4, 6, 8, 1, 3, 5, 7};
        wiggleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
