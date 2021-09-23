package code.RecursionDP;

/**
 * @author Jevis Hoo
 * @since 2021/4/11 16:36
 * @description 小偷能偷的最大价值
 */
public class StealMaxValue {
    /**
     * @param nums array
     * @return max value
     * @description 非环形
     */
    public static int stealMaxValue(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int length = nums.length;

        int[] dp = new int[length];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0]);

        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[length - 1];
    }

    /**
     * @param nums array
     * @return max value
     * @description 非环形，空间压缩
     */
    public static int stealMaxValue1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }


        int pre = nums[0];
        int res = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            int lastMax = pre + nums[i];
            pre = res;
            res = Math.max(res, lastMax);
        }

        return res;
    }


    /**
     * @param nums array
     * @return max value
     * @description 环形
     */
    public static int stealMaxValueByCycle(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int length = nums.length;

        int[] dp0 = new int[length - 1];
        int[] dp1 = new int[length - 1];

        dp0[0] = nums[0];
        dp0[1] = Math.max(nums[1], nums[0]);

        for (int i = 2; i < length - 1; i++) {
            dp0[i] = Math.max(dp0[i - 1], dp0[i - 2] + nums[i]);
        }

        dp1[0] = nums[1];
        dp1[1] = Math.max(nums[1], nums[2]);

        for (int i = 2; i < length - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + nums[i + 1]);
        }

        return Math.max(dp0[length - 2], dp1[length - 2]);
    }


    public static void main(String[] args) {
        int[] nums = new int[]{
                5, 1, 2, 3, 4, 5, 6, 7
        };

        System.out.println(stealMaxValue(nums));
        System.out.println(stealMaxValue1(nums));
        System.out.println(stealMaxValueByCycle(nums));
    }
}
