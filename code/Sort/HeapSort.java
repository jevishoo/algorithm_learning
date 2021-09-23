package code.Sort;

import java.util.Arrays;

/**
 * @author Jevis Hoo
 * @since 2021/5/9 15:58
 * @description 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        int index = 0;
        boolean flag = true;
        long start = System.nanoTime();
        while (index++ < 500000) {
            int[] array = Utils.generateRandomArray(50, 100);
            int[] copyArray = Utils.copyArray(array);
            int[] copyArray1 = Utils.copyArray(array);

            heapSort(array);
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

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            // 0-i位置建立大根堆
            // 任何一个子树的最大值都是这个子树的根结点的值
            heapInsert(arr, i);
        }
        int size = arr.length;
        Utils.swap(arr, 0, --size);
        while (size > 1) {
            heapAdjust(arr, size);
            Utils.swap(arr, 0, --size);
        }
    }

    private static void heapInsert(int[] arr, int index) {
        // 2*index+1 左孩子
        // 2*index+2 右孩子
        // (index-1)/2 父结点
        // 只要当前结点数比父结点数大，就与父结点交换，同时索引变为父结点索引
        while (arr[index] > arr[(index - 1) / 2]) {
            Utils.swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static void heapAdjust(int[] arr, int size) {
        int index = 0;
        int left = 1;
        while (left < size) {
            // 判断孩子中较大的值的索引（要确保右孩子在size范围之内）
            int largest =
                    left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;

            // 比较父结点的值与孩子中较大的值，并确定最大值的索引
            if (arr[index] > arr[largest]) {
                largest = index;
            }

            // 如果父结点索引是最大值的索引，那已经是大根堆了，则退出循环
            if (index != largest) {
                // 父结点不是最大值，与孩子中较大的值交换
                Utils.swap(arr, largest, index);
                // 将索引指向孩子中较大的值的索引
                index = largest;
                // 重新计算交换之后的孩子的索引
                left = 2 * index + 1;
            } else {
                break;
            }
        }
    }
}
