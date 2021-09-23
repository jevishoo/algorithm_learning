package code.Array;

/**
 * @author Jevis Hoo
 * @since 2021/3/30 17:25
 * @description 矩阵中的路径
 */
public class MatrixPath {
    public static boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, words, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, char[] words, int i, int j, int k) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != words[k]) {
            return false;
        }

        if (k == words.length - 1) {
            return true;
        }
        board[i][j] = '\\';
        boolean result = dfs(board, words, i + 1, j, k + 1) ||
                dfs(board, words, i, j - 1, k + 1) ||
                dfs(board, words, i - 1, j, k + 1) ||
                dfs(board, words, i, j + 1, k + 1);
        board[i][j] = words[k];
        return result;
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        System.out.println(exist(matrix, "ABCCES"));
    }
}
