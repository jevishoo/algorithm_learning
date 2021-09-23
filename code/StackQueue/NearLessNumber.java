package code.StackQueue;

import java.util.*;

/**
 * @since 2020/8/19 19:02
 * @Created by Jevis_Hoo
 * @Description 给定一个数组arr，找到每一个i位置左边和右边离i位置最近且值比arr[i]小的位置。返回所有位置相应的信息。
 * <p>
 * 简单：不含有重复值的
 * 复杂：含有重复值的
 * <p>
 * arr = {3,4,1,5,6,2,7}
 * 返回如下二维数组作为结果：
 * {
 * {-1, 2},
 * { 0, 2},
 * {-1,-1},
 * { 2, 5},
 * { 3, 5},
 * { 2,-1},
 * { 5,-1}
 * }
 * <p>
 * 【要求】
 * 如果 arr 长度为N，实现原问题和进阶问题的解法，时间复杂度都达到O(N)。
 */
public class NearLessNumber {

    public static int[][] comparatorNoRep(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    public static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] result = new int[arr.length][2];
        Stack<Integer> note = new Stack<>();
        //{3,4,1,5,6,2,7}
        for (int i = 0; i < arr.length; i++) {
            while (!note.isEmpty() && arr[note.peek()] > arr[i]) {
                int outStackIndex = note.pop();
                if (note.isEmpty()) {
                    result[outStackIndex][0] = -1;
                } else {
                    result[outStackIndex][0] = note.peek();
                }
                result[outStackIndex][1] = i;
            }
            note.push(i);
        }

        while (!note.isEmpty()) {
            int outStackIndex = note.pop();
            result[outStackIndex][0] = note.isEmpty() ? -1 : note.peek();
            result[outStackIndex][1] = -1;
        }

        return result;
    }

    public static int[][] getNearLessRepeat(int[] arr) {
        int[][] result = new int[arr.length][2];
        Stack<HashMap<Integer, Integer>> note = new Stack<>();
        //{3, 1, 3, 4, 3, 1, 5, 4, 6, 2, 2, 7}
        for (int i = 0; i < arr.length; i++) {

            while (!note.isEmpty() && arr[(int) note.peek().keySet().toArray()[0]] > arr[i]) {
                HashMap<Integer, Integer> outStackMap = note.pop();
                int outStackIndex = (int) outStackMap.keySet().toArray()[0];
                if (note.isEmpty()) {
                    result[outStackIndex][0] = -1;
                } else {
                    result[outStackIndex][0] = (int) outStackMap.values().toArray()[0];
                }
                result[outStackIndex][1] = i;
            }
            HashMap<Integer, Integer> map = new HashMap<>();

            if (note.isEmpty()) {
                map.put(i, -1);
            } else {
                if (arr[i] == arr[(int) note.peek().keySet().toArray()[0]]) {
                    map.put(i, (Integer) note.peek().values().toArray()[0]);
                } else {
                    map.put(i, (Integer) note.peek().keySet().toArray()[0]);
                }
            }
            note.push(map);
        }

        while (!note.isEmpty()) {
            HashMap<Integer, Integer> outStackMap = note.pop();
            int outStackIndex = (int) outStackMap.keySet().toArray()[0];
            if (note.isEmpty()) {
                result[outStackIndex][0] = -1;
            } else {
                result[outStackIndex][0] = (int) outStackMap.values().toArray()[0];
            }
            result[outStackIndex][1] = -1;
        }

        return result;
    }

    public static int[][] comparatorRep(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> popIs = stack.pop();
                // 取位于下面位置的列表中，最晚加入的那个
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(
                        stack.peek().size() - 1);
                for (Integer popi : popIs) {
                    res[popi][0] = leftLessIndex;
                    res[popi][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(Integer.valueOf(i));
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> popIs = stack.pop();
            // 取位于下面位置的列表中，最晚加入的那个
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(
                    stack.peek().size() - 1);
            for (Integer popi : popIs) {
                res[popi][0] = leftLessIndex;
                res[popi][1] = -1;
            }
        }
        return res;
    }

    public static int[] generateRandomArray(boolean isRep, int minSize, int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize - minSize + 1) * Math.random()) + minSize];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }

        if (isRep) {
            Set<Integer> set = new TreeSet<>();//新建一个set集合
            for (int a : arr) {
                set.add(a);
            }
            Integer[] arr2 = set.toArray(new Integer[0]);
            int[] result = new int[arr2.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = arr2[i];
            }
            return result;
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, arr.length);
        return res;
    }

    public static boolean isEqual(int[][] arr1, int[][] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            String str1 = Arrays.toString(arr1[i]);
            String str2 = Arrays.toString(arr2[i]);
            if (!str1.equals(str2)) {
                System.out.println(i);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 1, 5, 6, 2, 7};
        int[][] res = comparatorNoRep(arr);

        for (int[] r : res) {
            System.out.println(Arrays.toString(r));
        }
        System.out.println("==================");

        int[][] resNoRep = getNearLessNoRepeat(arr);
        for (int[] r : resNoRep) {
            System.out.println(Arrays.toString(r));
        }
        System.out.println("==================");

        int[] repArr = new int[]{3, 1, 3, 4, 3, 1, 5, 4, 6, 2, 2, 7};

        int[][] resRep = comparatorRep(repArr);

        for (int[] r : resRep) {
            System.out.println(Arrays.toString(r));
        }
        System.out.println("==================");

        resRep = getNearLessRepeat(repArr);

        for (int[] r : resRep) {
            System.out.println(Arrays.toString(r));
        }
        System.out.println("==================");

        int testTime = 5000;
        int maxSize = 100;
        int minSize = 3;
        int maxValue = 100;
        boolean succeed = true;
        boolean isRepeat = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(isRepeat, minSize, maxSize, maxValue);

            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            int[][] res1;
            int[][] res2;
            if (isRepeat) {
                res1 = comparatorNoRep(arr1);
                res2 = getNearLessNoRepeat(arr2);
            } else {
                res1 = comparatorRep(arr1);
                res2 = getNearLessRepeat(arr2);
            }
            if (!isEqual(res1, res2)) {
                succeed = false;
                System.out.println(Arrays.toString(arr3));
                System.out.println("=============================");
                for (int[] r : res1) {
                    System.out.println(Arrays.toString(r));
                }
                System.out.println("=============================");
                for (int[] r : res2) {
                    System.out.println(Arrays.toString(r));
                }
                System.out.println("=============================");
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Terrible!");

    }
}
