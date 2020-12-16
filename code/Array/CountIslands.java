package code.Array;

/**
 * @Date 2020/12/16 14:30
 * @Created by Jevis_Hoo
 * @Description 岛屿数量
 */
public class CountIslands {
    public static int countIslands(int[][] m) {
        if (m == null)
            return 0;

        int result = 0;

        int height = m.length;
        int length = m[0].length;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                if (m[i][j] == 1) {
                    infect(m, i, j, height, length);
                    result++;
                }
            }
        }
        return result;
    }

    private static void infect(int[][] m, int i, int j, int N, int M) {
        if (i < 0 || i >= N || j < 0 || j >= M || m[i][j] != 1) {
            return;
        }
        m[i][j] = 2;
        infect(m, i - 1, j, N, M);
        infect(m, i + 1, j, N, M);
        infect(m, i, j - 1, N, M);
        infect(m, i, j + 1, N, M);
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                new int[]{1, 1, 1, 1, 0, 0, 0, 0},
                new int[]{1, 0, 0, 0, 1, 1, 1, 0},
                new int[]{1, 0, 1, 1, 1, 0, 0, 1},
                new int[]{0, 1, 1, 0, 1, 0, 1, 1},
        };

        System.out.println(countIslands(matrix));
    }
}
