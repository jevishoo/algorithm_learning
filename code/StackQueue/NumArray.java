package code.StackQueue;

import java.util.LinkedList;

/**
 * @Date 2020/8/24 21:48
 * @Created by Jevis_Hoo
 * @Description 最大值减去最小值小于或等于num 的子数组数量
 * <p>
 * 给定数组arr 和整数num，共返回有多少个子数组满足如下情况：
 * max(arr[i..j]) - min(arr[i..j]) <= num
 * max(arr[i..j])表示子数组arr[i..j]中的最大值，min(arr[i..j])表示子数组arr[i..j]中的最小值。
 * <p>
 * 【要求】
 * 如果数组长度为N，请实现时间复杂度为O(N)的解法。
 */
public class NumArray {

    public static int getArrayNum(int[] arr, int num) {
        LinkedList<Integer> qmin = new LinkedList<>();
        LinkedList<Integer> qmax = new LinkedList<>();
        int i = 0;
        int j = 0;
        int res = 0;
        while (i < arr.length) {
            while (j < arr.length) {
                if (qmin.isEmpty() || qmin.peekLast() != j) {
                    while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[j]) {
                        qmin.pollLast();
                    }
                    qmin.addLast(j);
                    while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[j]) {
                        qmax.pollLast();
                    }
                    qmax.addLast(j);
                }
                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
                    break;
                }
                j++;
            }
            res += j - i;
            if (qmin.peekFirst() == i) {
                qmin.pollFirst();
            }
            if (qmax.peekFirst() == i) {
                qmax.pollFirst();
            }
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] array = {2, 5, 6, 6, 1, 7, 8, 2};
        int num = 3;

        int result = getArrayNum(array, num);
        System.out.println(result);
    }
}
