package code.Array;

/**
 * @author Jevis Hoo
 * @date 2021/4/13 10:14
 * @description 在排序数组中查找数字 I
 */
public class SearchNumberBySortedArray {
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0, right = nums.length - 1;
        int mid = 0;
        boolean exist = false;
        while (left <= right) {
            mid = (right + left) >> 1;
            if (nums[mid] == target) {
                exist = true;
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (exist) {
            int count = 1, cur = mid - 1;
            while (cur >= 0 && nums[cur--] == target) {
                count++;
            }
            cur = mid + 1;
            while (cur < nums.length && nums[cur++] == target) {
                count++;
            }
            return count;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1};
        System.out.println(search(array, 2));

        array = new int[]{5, 7, 7, 8, 8, 10};
        System.out.println(search(array, 5));
        System.out.println(search(array, 6));
        System.out.println(search(array, 8));
        System.out.println(search(array, 10));

    }
}
