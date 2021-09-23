package code.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jevis Hoo
 * @since 2021/4/10 11:34
 * @description 全排列
 */
public class PermuteAllNumber {
    private static List<List<Integer>> res;
    static boolean[] used;

    /**
     * @param nums array
     * @return List
     * @description 无重复值
     */
    public static List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        used = new boolean[nums.length];
        permute(nums, new ArrayList<>());
        return res;
    }

    public static void permute(int[] nums, ArrayList<Integer> list) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                list.add(nums[i]);
                used[i] = true;
                permute(nums, list);
                list.remove(list.size() - 1);
                used[i] = false;
            }
        }
    }


    /**
     * @param nums array
     * @return List
     * @description 有重复值
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        used = new boolean[nums.length];
        Arrays.sort(nums);
        permuteUnique(nums, new ArrayList<>());
        return res;
    }

    public static void permuteUnique(int[] nums, ArrayList<Integer> list) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }

                list.add(nums[i]);
                used[i] = true;
                permuteUnique(nums, list);
                list.remove(list.size() - 1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(permute(nums));

        nums = new int[]{1, 2, 1};
        System.out.println(permuteUnique(nums));
    }
}
