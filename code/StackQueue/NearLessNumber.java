package code.StackQueue;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Date 2020/8/19 19:02
 * @Created by Jevis_Hoo
 * @Description 给定一个数组arr，找到每一个i位置左边和右边离i位置最近且值比arr[i]小的位置。返回所有位置相应的信息。
 * <p>
 * 简单：不含有重复值的
 * 复杂：含有重复值的
 * <p>
 * arr = {3,4,1,5,6,2,7}
 * 返回如下二维数组作为结果：
 * {
 * {-1, 2},
 * { 0, 2},
 * {-1,-1},
 * { 2, 5},
 * { 3, 5},
 * { 2,-1},
 * { 5,-1}
 * }
 * <p>
 * 【要求】
 * 如果 arr 长度为N，实现原问题和进阶问题的解法，时间复杂度都达到O(N)。
 */
public class NearLessNumber {

    public static int[][] comparator(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    public static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] result = new int[arr.length][2];
        Stack<Integer> note = new Stack<>();
        //{3,4,1,5,6,2,7}
        for (int i = 0; i < arr.length; i++) {
            while (!note.isEmpty() && arr[note.peek()] > arr[i]) {
                int outStackIndex = note.pop();
                if (note.isEmpty()) {
                    result[outStackIndex][0] = -1;
                } else {
                    result[outStackIndex][0] = note.peek();
                }
                result[outStackIndex][1] = i;
            }
            note.push(i);
        }
        while (!note.isEmpty()) {
            int outStackIndex = note.pop();
//            if (note.isEmpty()) {
//                result[outStackIndex][0] = -1;
//            } else {
//                result[outStackIndex][0] = note.peek();
//            }
            result[outStackIndex][0] = note.isEmpty() ? -1 : note.peek();
            result[outStackIndex][1] = -1;
        }

        return result;
    }

    public static int[][] getNearLessRepeat(int[] arr) {
        return null;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 1, 5, 6, 2, 7};
        int[][] res = comparator(arr);

        for (int[] r : res) {
            System.out.println(Arrays.toString(r));
        }
        System.out.println("==================");

        int[][] res1 = getNearLessNoRepeat(arr);

        for (int[] r : res1) {
            System.out.println(Arrays.toString(r));
        }

    }
}
