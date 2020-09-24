package code.LinkedList;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Date 2020/9/24 14:56
 * @Created by Jevis_Hoo
 * @Description NextGreaterElements
 * <p>
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
 * 数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，
 * 这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 * <p>
 * 输入: [1, 2, 1]
 * 输出: [2,-1, 2]
 */
public class NextGreaterElements {
    public static int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];
        //monotone increasing LinkedList
        LinkedList<Integer> list = new LinkedList<>();
        //monotone decreasing LinkedList
        LinkedList<Integer> help = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            // generate monotone increasing list
            while (!list.isEmpty() && nums[i] > nums[list.peekFirst()]) {
                int index = list.pollFirst();
                result[index] = nums[i];
                // !!! generate help list (monotone decreasing list)
                if (list.isEmpty() || (nums[i] <= nums[list.peekFirst()] && i < list.peekLast())) {
                    help.push(index);
                }
            }
            list.push(i);
        }

        // push help element to array
        int[] cycle = new int[help.size()];
        int j = 0;
        while (!help.isEmpty()) {
            cycle[j++] = help.pollLast();
        }
        // you can check the help list element which including index before MAX
        // System.out.println(Arrays.toString(cycle));


        while (!list.isEmpty()) {
            int index = list.pollFirst();
            boolean flag = false; // judge element is smaller than help list or not
            if (list.size() != 0) {
                for (int value : cycle) {
                    if (nums[index] < nums[value]) {
                        result[index] = nums[value];
                        flag = true;
                        break; // find --> break
                    }
                }

                if (!flag) { // Not find
                    // judge element is smaller than MAX
                    if (nums[index] < nums[list.peekLast()]) {
                        result[index] = nums[list.peekLast()];
                    } else result[index] = -1;
                }
            } else {
                result[index] = -1;// MAX (Last list element)
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 5, 4, 5, 1, 2, 3};

        System.out.println(Arrays.toString(nextGreaterElements(array)));
    }
}
