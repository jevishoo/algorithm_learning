package code.StackQueue;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Date 2020/9/25 15:02
 * @Created by Jevis_Hoo
 * @Description Daily Temperatures
 * <p>
 * Given a list of daily temperatures T, return a list such that, for each day in the input,
 * tells you how many days you would have to wait until a warmer temperature.
 * If there is no future day for which this is possible, put 0 instead.
 */
public class DailyTemperatures {

    public static int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length - 1; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            result[stack.pop()] = 0;
        }

        result[T.length - 1] = 0;
        return result;
    }

    public static void main(String[] args) {
        int[] array = new int[]{73, 74, 75, 71, 69, 72, 76, 73};

        System.out.println(Arrays.toString(dailyTemperatures(array)));
    }
}
