package code.Array;

import java.util.Arrays;

/**
 * @author Jevis Hoo
 * @date 2021/4/8 16:42
 * @description 顺时针打印矩阵
 */
public class SpiralOrder {
    public static int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }

        int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1, index = 0;
        int[] res = new int[(right + 1) * (bottom + 1)];
        while (true) {
            // left to right.
            for (int i = left; i <= right; i++) {
                res[index++] = matrix[top][i];
            }
            if (++top > bottom) {
                break;
            }
            // top to bottom.
            for (int i = top; i <= bottom; i++) {
                res[index++] = matrix[i][right];
            }
            if (left > --right) {
                break;
            }
            // right to left.
            for (int i = right; i >= left; i--) {
                res[index++] = matrix[bottom][i];
            }
            if (top > --bottom) {
                break;
            }
            // bottom to top.
            for (int i = bottom; i >= top; i--) {
                res[index++] = matrix[i][left];
            }
            if (++left > right) {
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        System.out.println(Arrays.toString(spiralOrder(matrix)));
    }
}
