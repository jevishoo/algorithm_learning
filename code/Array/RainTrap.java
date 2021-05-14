package code.Array;

import java.util.Stack;

/**
 * @author Jevis Hoo
 * @date 2021/5/10 19:47
 * @description 接雨水
 */
public class RainTrap {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trapByStack(arr));
        System.out.println(trapByArr(arr));
    }

    public static int trapByStack(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;

        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int ind = stack.pop();

                if (!stack.isEmpty()) {
                    int left = height[stack.peek()];
                    int h = Math.min(left, height[i]) - height[ind];
                    int w = i - stack.peek() - 1;
                    res += w * h;
                }
            }
            stack.push(i);
        }
        return res;
    }

    public static int trapByArr(int[] height) {
        int[] lHelp = new int[height.length];
        int[] rHelp = new int[height.length];
        int res = 0;

        int max = 0;
        for (int i = 0; i < height.length; i++) {
            max = Math.max(max, height[i]);
            lHelp[i] = max;
        }

        max = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            max = Math.max(max, height[i]);
            rHelp[i] = max;
        }

        for (int i = 0; i < height.length; i++) {
            res += Math.min(lHelp[i], rHelp[i]) - height[i];
        }
        return res;
    }
}
