package code.Array;

/**
 * @author Jevis Hoo
 * @since 2021/3/29 9:52
 * @description 二维数组中的查找
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class FindNumberIn2DArray {
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int m = 0;
        int n = matrix[0].length - 1;

        while (true) {
            while (matrix[m][n] > target) {
                n--;
                if (n < 0) {
                    return false;
                }
            }

            if (matrix[m][n] == target) {
                return true;
            }

            while (matrix[m][n] < target) {
                m++;
                if (m == matrix.length) {
                    return false;
                }
            }

            if (matrix[m][n] == target) {
                return true;
            }
        }
    }

    public static void main(String[] args) {
//        int[][] array = new int[][]{
//                {1, 4, 7, 11, 15},
//                {2, 5, 8, 12, 19},
//                {3, 6, 9, 16, 22},
//                {10, 13, 14, 17, 24},
//                {18, 21, 23, 26, 30}
//        };
        int[][] array = new int[][]{
                {-5},
        };
        System.out.println(findNumberIn2DArray(array, -5));
    }
}
