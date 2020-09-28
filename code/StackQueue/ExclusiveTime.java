package code.StackQueue;

import java.util.*;

/**
 * @Date 2020/9/27 20:02
 * @Created by Jevis_Hoo
 * @Description Exclusive Time of Functions
 * <p>
 * 给出一个非抢占单线程CPU的 n 个函数运行日志，找到函数的独占时间。
 * <p>
 * 每个函数都有一个唯一的 Id，从 0 到 n-1，函数可能会递归调用或者被其他函数调用。
 * <p>
 * 日志是具有以下格式的字符串：function_id：start_or_end：timestamp。
 * 例如："0:start:0" 表示函数 0 从 0 时刻开始运行。"0:end:0" 表示函数 0 在 0 时刻结束。
 * <p>
 * 函数的独占时间定义是在该方法中花费的时间，调用其他函数花费的时间不算该函数的独占时间。
 * 你需要根据函数的 Id 有序地返回每个函数的独占时间。
 */
public class ExclusiveTime {
    public static int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        int preTime = 0;
        for (String log : logs) {
            String[] record = log.split(":");
            int id = Integer.parseInt(record[0]);
            String type = record[1];
            int time = Integer.parseInt(record[2]);

            if (type.equals("start")) {
                if (!stack.isEmpty()) {
                    result[stack.peek()] += time - preTime;
                }
                stack.push(id);
                preTime = time;
            } else {
                stack.pop();
                result[id] += time - preTime + 1;
                preTime = time + 1;
            }


        }
        return result;
    }


    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
//        list.add("0:start:0");
//        list.add("1:start:2");
//        list.add("1:end:5");
//        list.add("0:end:6");
//        list.add("0:start:0");
//        list.add("0:start:2");
//        list.add("0:end:5");
//        list.add("0:start:6");
//        list.add("0:end:6");
//        list.add("0:end:7");

        list.add("0:start:0");
        list.add("1:start:5");
        list.add("2:start:6");
        list.add("3:start:9");
        list.add("4:start:11");
        list.add("5:start:12");
        list.add("6:start:14");
        list.add("7:start:15");
        list.add("1:start:24");
        list.add("1:end:29");
        list.add("7:end:34");
        list.add("6:end:37");
        list.add("5:end:39");
        list.add("4:end:40");
        list.add("3:end:45");
        list.add("0:start:49");
        list.add("0:end:54");
        list.add("5:start:55");
        list.add("5:end:59");
        list.add("4:start:63");
        list.add("4:end:66");
        list.add("2:start:69");
        list.add("2:end:70");
        list.add("2:start:74");
        list.add("6:start:78");
        list.add("0:start:79");
        list.add("0:end:80");
        list.add("6:end:85");
        list.add("1:start:89");
        list.add("1:end:93");
        list.add("2:end:96");
        list.add("2:end:100");
        list.add("1:end:102");
        list.add("2:start:105");
        list.add("2:end:109");
        list.add("0:end:114");

        System.out.println(Arrays.toString(exclusiveTime(8, list)));
    }
}
