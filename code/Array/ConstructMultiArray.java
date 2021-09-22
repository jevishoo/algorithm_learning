package code.Array;

import java.util.Arrays;

/**
 * @author Jevis Hoo
 * @date 2021/9/22 17:26
 * @description 构建乘积数组
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积
 * 不能使用除法
 */
public class ConstructMultiArray {
    public static int[] constructArr(int[] a) {
        int[] res = new int[a.length];
        if (a.length == 0) {
            return res;
        }

        res[0] = 1;
        int tailMulti = 1;
        for (int i = 1; i < a.length; i++) {
            res[i] = res[i - 1] * a[i - 1];
        }

        for (int i = a.length - 1; i > 0; i--) {
            tailMulti *= a[i];
            res[i - 1] *= tailMulti;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(constructArr(array)));
    }
}
