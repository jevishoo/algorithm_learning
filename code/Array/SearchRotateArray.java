package code.Array;

/**
 * @author Jevis Hoo
 * @since 2021/4/6 11:03
 * @description 搜索旋转数组
 */
public class SearchRotateArray {

    public static int search(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int mid;

        while (left <= right) {
            if (arr[left] == target) {
                //找到结果, 因为找的是最小的索引, 所以当left符合时直接返回
                return left;
            }

            mid = left + ((right - left) >> 1);
            System.out.println("===========");
            System.out.println("mid: " + mid);

            if (arr[mid] == target) {
                //mid的值是目标的值, 更新右边界, 继续查找左边是否有重复值
                right = mid;
            } else if (arr[mid] > arr[left]) {
                //左边是升序的
                if (arr[left] <= target && target <= arr[mid]) {
                    //目标在left和mid中间，去掉右边的数据
                    right = mid;
                } else {
                    //目标值大于mid元素值，去掉左边的数据
                    left = mid + 1;
                }
            } else if (arr[mid] < arr[left]) {
                //右边是升序的
                if (arr[mid] <= target && target <= arr[right]) {
                    //目标在mid和right中间，去掉左边的数据
                    left = mid;
                } else {
                    //目标小于mid元素值，去掉右边的数据
                    right = mid - 1;
                }
            } else {
                // 中间值等于左边值时，说明只剩两个元素
                // 左边元素不相等，直接看右边元素
                if (mid == left) {
                    left++;
                } else {
                    // 中间值等于左边元素时
                    // >>> 左边到中间全为该元素，如果该值仍大于目标值，说明此序列不存在目标值
                    // >>> 或者左边与右边值相等，为旋转后的如：2,2,1,2,2,2,2,2
                    if (arr[mid] > target) {
                        // 如果该值大于目标值，说明目标值肯定在mid前
                        right = mid - 1;
                    } else {
                        // 如果该值小于目标值，说明目标值肯定在mid后
                        left = mid + 1;
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
//        int[] array = new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        int[] array = new int[]{1, -2};

        System.out.println(search(array, 1));
//        System.out.println(search(array, 10));
//        System.out.println(search(array, 25));
        System.out.println(search(array, -2));
    }

}
