package code.String;

/**
 * @author Jevis Hoo
 * @date 2021/3/9 14:55
 * @description 判断字符数组中是否所有的字符都只出现过一次
 * <p>
 * 给定一个字符类型数组chas[]，判断chas 中是否所有的字符都只出现过一次。
 */
public class IsUnique {

    /**
     * @param chars char array
     * @return true or false
     * @description 时间复杂度为 O(N) 的方法
     */
    public static boolean isUnique1(char[] chars) {
        if (chars == null) {
            return true;
        }

        boolean[] map = new boolean[256];
        for (char ch : chars) {
            if (map[ch]) {
                return false;
            }
            map[ch] = true;
        }
        return true;
    }

    /**
     * @param chars char array
     * @return true or false
     * @description 额外空间复杂度为 O(1)的前提下，请实现时间复杂度尽量低的方法
     * <p>
     * 只有堆排序能实现时间复杂度为 O(logN)的情况下，空间复杂度为 O(1)
     * >>> 时间复杂度为 O(N)的排序算法（桶排序、基数排序、计数排序 ）做不到额外空间复杂度为O(1)
     * >>> 归并排序中有两个小组合并成一个大组的过程，这个过程需要辅助数组才能完成
     * >>> 快速排序无论选择递归实现还是非递归实现，快速排序的额外空间复杂度最低为 O(logN)
     * >>> 希尔排序的时间复杂度并不固定，成败完全在于步长的选择，如果选择不当，时间复杂度会变成 O(N^2)
     */
    public static boolean isUnique2(char[] chars) {
        if (chars == null) {
            return true;
        }

        heapSort(chars);
        System.out.println(chars);
        for (int i = 1; i < chars.length; i++) {
            if (chars[i - 1] == chars[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param chars char array
     * @description 数组实现的完全二叉树，其中（ i 为索引）：
     * >>> 左节点：2*i+1；右节点：2*i+2
     * >>> 父节点：(i-1)/2
     */
    private static void heapSort(char[] chars) {
        //1.构建大根堆
        for (int i = 0; i < chars.length; i++) {
            // 0-i 位置建立大根堆
            heapInsert(chars, i);
        }

        //2.构建大根堆
        for (int i = chars.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            heapInsert(chars, i, chars.length);
        }

        int size = chars.length;
        swap(chars, 0, --size);

        while (size > 0) {
            // 改变根节点，此值向下沉的结果
            heapify(chars, 0, size);
            swap(chars, 0, --size);
        }
    }

    public static void heapify(char[] chars, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            int largest = left + 1 < size && chars[left + 1] > chars[left]
                    ? left + 1 : left;

            if (chars[largest] <= chars[index]) {
                break;
            }

            swap(chars, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void heapInsert(char[] chars, int i) {
        while (chars[i] > chars[(i - 1) / 2]) {
            swap(chars, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private static void heapInsert(char[] chars, int i, int size) {
        //先取出当前元素 i
        char temp = chars[i];
        //从 i 结点的左子结点开始，也就是 2i+1 处开始
        for (int k = i * 2 + 1; k < size; k = k * 2 + 1) {
            //如果左子结点小于右子结点，k 指向右子结点
            if (k + 1 < size && chars[k] < chars[k + 1]) {
                k++;
            }
            //如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
            if (chars[k] > temp) {
                chars[i] = chars[k];
                i = k;
            } else {
                break;
            }
        }
        //将temp值放到最终的位置
        chars[i] = temp;
    }

    private static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    public static void main(String[] args) {
        String str = "abcdefghijklmszytd";
        char[] chars = str.toCharArray();
        heapSort(chars);
        System.out.println(isUnique1(chars));
        System.out.println(isUnique2(chars));
    }
}
