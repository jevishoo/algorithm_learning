package code.StackQueue;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Date 2020/8/23 20:13
 * @Created by Jevis_Hoo
 * @Description 求最大子矩阵的大小
 * 给定一个整型矩阵mat，其中的值只有0 和1 两种，求其中全是1
 * 的所有矩形区域中，最大的矩形区域为1 的数量。
 * <p>
 * 例如：
 * 1 1 1 0
 * 其中，最大的矩形区域有3 个1，所以返回3。
 * 再如：
 * 1 0 1 1
 * 1 1 1 1
 * 1 1 1 0
 * 其中，最大的矩形区域有6 个1，所以返回6。
 * <p>
 * 【要求】
 * 如果矩阵的大小为O(N×M)，可以做到时间复杂度为O(N×M)
 */
public class MaxRecSize {

    public static int getMaxRecSize(int[][] mat) {
        int result = 0;

        int[] height = new int[mat[0].length];

        for (int[] ints : mat) {
            for (int j = 0; j < mat[0].length; j++) {
                /*
                构建height数组，数组每列数值代表该列自第 1 层到 i 层含有 1 的个数
                 */
                height[j] = ints[j] == 0 ? 0 : height[j] + 1;
            }
            result = Math.max(getMaxHeterogramRec(height), result);
        }
        return result;
    }

    public static int getMaxHeterogramRec(int[] het) {
        Stack<Integer> note = new Stack<>();
        int maxRec = 0;

        /*
        构建单调无重复值栈
         */
        for (int m = 0; m < het.length; m++) {
            /*
            只要栈不为空，且栈顶值大于新值，进入循环
             */
            while (!note.isEmpty() && het[note.peek()] > het[m]) {
                int outStackIndex = note.pop();
                int temp;
                if (note.isEmpty()) {
                    temp = het[outStackIndex] * m;//栈如果为空时，表示栈最后元素为最小。结果即为此值乘上列数
                } else {
                    temp = het[outStackIndex];//栈如果不为空，表示栈顶最大值出栈。最大值即为矩形最大值
                }
                maxRec = Math.max(temp, maxRec);
            }

            /*
            只要栈不为空，栈顶元素等于新值，新值不入栈，直接计算此时该值对应的面积
            其他情况（栈为空或栈不为空但新值大于栈顶值）直接入栈
             */
            if (!note.isEmpty() && het[note.peek()] == het[m]) {
                int temp = het[m] * (m - note.peek());//该值对应的面积即为新值指引减去栈顶元素指引
                maxRec = Math.max(temp, maxRec);
            } else {
                note.push(m);
            }
        }

        /*
        最后计算栈内值对应的面积
        注意此时：栈顶元素为outStackIndex位置到最后位置的最小值
         */
        while (!note.isEmpty()) {
            int temp;
            int outStackIndex = note.pop();//取出栈顶元素并获得指引
            if (note.isEmpty()) {
                temp = het[outStackIndex] * het.length;//只要去除栈顶元素的栈为空栈，说明得到该值为数组最小值，对应的面积即为改值乘上列数
            } else {
                if (het[note.peek()] == 0) {//去除栈顶元素不为空栈时，如果前面一个元素为0，面积即为最后位置到元素为0的后面一位
                    temp = het[outStackIndex] * (het.length - note.peek() - 1);
                } else {//去除栈顶元素不为空栈时，如果前面一个元素不为0，面积即为最后位置到此位置
                    temp = het[outStackIndex] * (het.length - outStackIndex);
                }
            }
            maxRec = Math.max(temp, maxRec);
        }
        System.out.println(Arrays.toString(het));
        System.out.println(maxRec);
        System.out.println("============");
        return maxRec;
    }

    public static void main(String[] args) {

        int[][] mat = {
                {1, 0, 1, 1},
                {1, 1, 1, 1},
                {1, 0, 1, 1},
                {1, 0, 0, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1},
                {0, 1, 1, 1},
                {0, 1, 1, 1},
                {0, 0, 0, 0},
                {1, 1, 1, 0},
                {1, 1, 1, 0},
                {1, 1, 1, 0},
                {1, 1, 1, 0},
                {1, 1, 1, 0}
        };

        for (int[] ints : mat) {
            System.out.println(Arrays.toString(ints));
        }

        System.out.println("============");
        System.out.println(getMaxRecSize(mat));

    }
}
