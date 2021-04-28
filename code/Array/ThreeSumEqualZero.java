package code.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jevis Hoo
 * @date 2021/4/26 21:38
 * @description 三数之和为0
 */
public class ThreeSumEqualZero {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 0, 4, 1, 2, -1, -3, -2, -4};
        List<List<Integer>> lists = threeSumEqualZero(nums);
        System.out.println(lists);
    }

    public static List<List<Integer>> threeSumEqualZero(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        if (nums == null || nums.length < 3) {
            return ans;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int target = -nums[i];
            int left = i + 1, right = nums.length - 1;

            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    ArrayList<Integer> tmp = new ArrayList<>();
                    tmp.add(nums[i]);
                    tmp.add(nums[left]);
                    tmp.add(nums[right]);
                    ans.add(tmp);

                    // 去重
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 去重
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;

                } else if (nums[left] + nums[right] < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return ans;
    }
}
