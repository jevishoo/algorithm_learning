package code.StackQueue;

import java.util.HashMap;
import java.util.Stack;

/**
 * @since 2020/10/7 9:04
 * @Created by Jevis_Hoo
 * @Description Maximum Frequency Stack
 * <p>
 * Implement FreqStack, a class which simulates the operation of a stack-like data structure.
 * <p>
 * FreqStack has two functions:
 * <p>
 * >>> push(int x), which pushes an integer x onto the stack.
 * >>> pop(), which removes and returns the most frequent element in the stack.
 * If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.
 */
public class FreqStack {
    HashMap<Integer, Integer> freq; // x 到 x 的出现次数的映射
    HashMap<Integer, Stack<Integer>> group; // 作为从频率到具有该频率的元素的映射
    int maxFreq; // 当前最大频率

    public FreqStack() {
        freq = new HashMap<>();
        group = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int x) {
        int f = freq.getOrDefault(x, 0) + 1;
        freq.put(x, f);
        if (f > maxFreq)
            maxFreq = f;

        group.computeIfAbsent(f, z -> new Stack<>()).push(x);
    }

    public int pop() {
        int x = group.get(maxFreq).pop();
        freq.put(x, freq.get(x) - 1);
        if (group.get(maxFreq).size() == 0)
            maxFreq--;
        return x;
    }

    /*
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    private HashMap<Integer, Integer> map;

    public FreqStack() {
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
        this.map = new HashMap<>();
    }

    // 时间复杂度高
    public void push(int x) {
        map.put(x, map.getOrDefault(x, 0) + 1);
        while (!stack1.isEmpty() && map.get(stack1.peek()) > map.get(x)) {
            stack2.push(stack1.pop());
            map.put(stack2.peek(), map.get(stack2.peek()) - 1);
        }
        stack1.push(x);
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
            map.put(stack1.peek(), map.get(stack1.peek()) + 1);
        }
    }

    public int pop() {
        int res = stack1.pop();
        map.put(res, map.get(res) - 1);
        return res;
    }


    // 时间复杂度太高
    public void push(int x) {
        if (map.containsKey(x)) {
            map.put(x, map.get(x) + 1);
        } else map.put(x, 1);

        stack1.push(x);
    }

    public int pop() {
        int max = Integer.MIN_VALUE;
        for (Integer value : map.values()) {
            max = value > max ? value : max;
        }
        while (!stack1.isEmpty() && map.get(stack1.peek()) != max) {
            stack2.push(stack1.pop());
        }
        int res = stack1.pop();
        map.put(res, map.get(res) - 1);

        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return res;
    }
     */


    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(4);
        freqStack.push(5);

        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
    }

}
