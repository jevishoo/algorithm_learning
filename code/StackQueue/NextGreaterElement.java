package code.StackQueue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * @Date 2020/9/23 21:01
 * @Created by Jevis_Hoo
 * @Description Next Greater Element I
 * <p>
 * 给定两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
 * 找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
 * <p>
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。
 * 如果不存在，对应位置输出 -1 。
 */
public class NextGreaterElement {
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peek()) {
                hashMap.put(stack.pop(), num);
            }
            stack.push(num);
        }

        for (int i = 0; i < nums1.length; i++) {
//            result[i] = hashMap.getOrDefault(nums1[i], -1);
            if (hashMap.containsKey(nums1[i])) {
                result[i] = hashMap.get(nums1[i]);
            } else result[i] = -1;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] array1 = new int[]{4, 1, 2};
        int[] array2 = new int[]{1, 3, 4, 2};

        int[] res = nextGreaterElement(array1, array2);
        System.out.println(Arrays.toString(res));
    }
}
