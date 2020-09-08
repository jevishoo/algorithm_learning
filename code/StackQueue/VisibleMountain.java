package code.StackQueue;

import java.util.Stack;

/**
 * @Date 2020/9/8 19:34
 * @Created by Jevis_Hoo
 * @Description 可见的山峰对数量
 * <p>
 * 一个不含有负数的数组可以代表一圈环形山，每个位置的值代表山的高度。
 * <p>
 * 比如，
 * {3,1,2,4,5}、{4,5,3,1,2}或{1,2,4,5,3}都代表同样结构的环形山。
 * 3->1->2->4->5->3 方向叫作next方向（逆时针）
 * 3->5->4->2->1->3 方向叫作last方向（顺时针）
 * <p>
 * 山峰A 和山峰B 能够相互看见的条件为：
 * 1．如果A 和B 是同一座山，认为不能相互看见。
 * 2．如果A 和B 是不同的山，并且在环中相邻，认为可以相互看见。相邻的山峰对有(1,2)(2,4)(4,5)(3,5)(1,3)。
 * 3．如果A 和B 是不同的山，并且在环中不相邻，假设两座山高度的最小值为min。
 * 如果A通过next 方向到B 的途中没有高度比min 大的山峰，
 * 或者A 通过last 方向到B 的途中没有高度比min 大的山峰，认为A 和B 可以相互看见。
 * 比如 高度为3 的山和高度为4 的山，两座山的高度最小值为3。
 * 3 从last 方向走向4，中途会遇见5，所以last 方向走不通；
 * 3 从next方向走向4，中途会遇见1 和2，但是都不大于两座山高度的最小值3，所以next 方向可以走通。
 * <p>
 * 有一个能走通就认为可以相互看见。再如，高度为2 的山和高度为5 的山，两个方向上都走不通，所以不能相互看见。
 * <p>
 * 所有在环中不相邻，并且能看见的山峰对有(2,3)(3,4)。
 * <p>
 * 给定一个不含有负数且没有重复值的数组arr，请返回有多少对山峰能够相互看见。
 * <p>
 * 进阶问题：给定一个不含有负数但可能含有重复值的数组arr，返回有多少对山峰能够相互看见。
 * <p>
 * 【要求】
 * 如果 arr 长度为N，没有重复值的情况下时间复杂度达到O(1)，可能有重复值的情况下时间复杂度请达到O(N)。
 */
public class VisibleMountain {
    /*
    简单问题：2i-1（i为节点个数）个可见山峰，可由数学逻辑证明（小找大）
    进阶问题较为复杂：运用单调栈解决
    >>> 注意 该设定的等高的山峰可以穿过，如：
    >>> 5 4 4 4 5 其中中间的 4 可以穿过相邻的 4 看到 5
     */
    public static class Record {
        public int value;
        public int times;

        public Record(int value) {
            this.value = value;
            this.times = 1;
        }
    }

    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int size = arr.length;
        int maxIndex = 0;
        // 先在环中找到其中一个最大值的位置，哪一个都行
        for (int i = 0; i < size; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
        }
        Stack<Record> stack = new Stack<Record>();
        // 先把(最大值,1)这个记录放入stack 中
        stack.push(new Record(arr[maxIndex]));
        // 从最大值位置的下一个位置开始沿next 方向遍历
        int index = nextIndex(maxIndex, size);
        // 用“小找大”的方式统计所有可见山峰对
        int res = 0;
        // 遍历阶段开始，当index 再次回到maxIndex 的时候，说明转了一圈，遍历阶段就结束
        while (index != maxIndex) {
            // 当前数字arr[index]要进栈，判断会不会破坏第一维的数字从顶到底依次变大
            // 如果破坏了，就依次弹出栈顶记录，并计算山峰对数量
            while (stack.peek().value < arr[index]) {
                int k = stack.pop().times;
                // 弹出记录为(X,K)，如果K==1，产生2 对
                // 如果K>1，产生2*K + C(2,K)对
                res += getInternalSum(k) + 2 * k;
            }
            // 当前数字arr[index]要进入栈了，如果和当前栈顶数字一样就合并
            // 不一样就把记录(arr[index],1)放入栈中
            if (stack.peek().value == arr[index]) {
                stack.peek().times++;
            } else {
                stack.push(new Record(arr[index]));
            }
            index = nextIndex(index, size);
        }
        // 清算阶段开始
        // 清算阶段的第1 小阶段
        while (stack.size() > 2) {
            int times = stack.pop().times;
            res += getInternalSum(times) + 2 * times;
        }
        // 清算阶段的第2 小阶段
        if (stack.size() == 2) {
            int times = stack.pop().times;
            res += getInternalSum(times)
                    + (stack.peek().times == 1 ? times : 2 * times);
        }
        // 清算阶段的第3 小阶段
        res += getInternalSum(stack.pop().times);
        return res;
    }

    // 如果k==1，返回0；如果k>1，返回C(2,k)
    public static int getInternalSum(int k) {
        return k == 1 ? 0 : (k * (k - 1) / 2);
    }

    // 环形数组中当前位置为i，数组长度为size，返回i 的下一个位置
    public static int nextIndex(int i, int size) {
        return i < (size - 1) ? (i + 1) : 0;
    }


    public int getVisibleNum(int[] arr) {
        return 0;
    }

    public static void main(String[] args) {
        int[] array = {4, 2, 4, 5, 3, 4, 5, 2, 3, 5, 4};

        int res = comparator(array);
        System.out.println(res);
    }
}
