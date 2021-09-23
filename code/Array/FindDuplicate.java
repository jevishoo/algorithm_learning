package code.Array;

/**
 * @author Jevis Hoo
 * @since 2021/4/11 15:32
 * @description 有 1亿个数字，其中有2个是重复的，快速找到它
 */
public class FindDuplicate {
    public static int findDuplicate(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int value : arr) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        byte[] bucket = new byte[(max - min) / 32 + 1];
        for (int num : arr) {
            int j = (num - min) / 8;
            int k = (num - min) % 8;
            if (((bucket[j] >> k) & 1) > 0) {
                return num;
            } else {
                bucket[j] |= (1 << k);
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        //1亿长度
        int[] array = new int[1000000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        array[99999999] = 2020;
        long start = System.currentTimeMillis();

        int duplicate = findDuplicate(array);
        System.out.println("Number of repeats：" + duplicate);

        long end = System.currentTimeMillis();
        System.out.println("millisecond:" + (end - start));
        System.out.println(
                Integer.MAX_VALUE
        );
    }
}
