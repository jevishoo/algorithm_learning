package code.BitManipulation;

import java.util.Arrays;

/**
 * @author Jevis Hoo
 * @date 2021/5/14 10:16
 * @description 数组中数字出现的次数
 */
public class SingleNumbers {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2, 4, 4, 5, 7, 7};
        System.out.println(Arrays.toString(singleNumbers(nums)));

        nums = new int[]{5, 2, 2, 2, 1, 1, 1};
        System.out.println(singleNumber(nums));
    }

    /**
     * @param nums array
     * @return 出现一次的数字
     * @description 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
     * 要求时间复杂度是O(n)，空间复杂度是O(1)
     */
    public static int[] singleNumbers(int[] nums) {
        //如果除了一个数字以外，其他数字都出现了两次,全员进行异或操作即可
        //成对出现的数字的所有位会两两抵消为 00，最终得到的结果就是那个出现了一次的数字

        //将所有的数异或起来
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        //获得k中最低位的1,分组操作
        int mask = 1;
        while ((xor & mask) == 0) {
            mask <<= 1;
        }

        int a = 0;
        int b = 0;

        //分组后 两组数异或
        for (int num : nums) {
            if ((num & mask) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }

        return new int[]{a, b};
    }

    /**
     * @param nums array
     * @return 出现一次的数字
     * @description 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。
     */
    public static int singleNumber(int[] nums) {
        int[] record = new int[32];

        for (int num : nums) {
            int index = 0;
            while (num != 0) {
                record[index++] += num & 1;
                num = num >>> 1;
            }
        }

        int res = 0;
        for (int i = 31; i >= 0; i--) {
            res <<= 1;
            res |= record[i] % 3;
        }

        return res;
    }
}
