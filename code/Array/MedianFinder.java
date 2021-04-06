package code.Array;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Jevis Hoo
 * @date 2021/4/6 13:28
 * @description MedianFinder
 */
public class MedianFinder {
    Queue<Integer> bigHeap;
    Queue<Integer> smallHeap;

    public MedianFinder() {
        bigHeap = new PriorityQueue<>((v1, v2) -> v2 - v1);
        smallHeap = new PriorityQueue<>();
    }

    public void addNum1(int num) {
        if (bigHeap.size() == 0) {
            bigHeap.offer(num);
        } else {
            if (bigHeap.peek() > num) {
                if (bigHeap.size() - smallHeap.size() == 1) {
                    smallHeap.offer(bigHeap.poll());
                }
                bigHeap.offer(num);
            } else {
                if (smallHeap.size() < bigHeap.size()) {
                    smallHeap.offer(num);
                } else if (smallHeap.peek() > num) {
                    bigHeap.offer(num);
                } else {
                    bigHeap.offer(smallHeap.poll());
                    smallHeap.offer(num);
                }
            }
        }
    }

    public void addNum(int num) {
        if (bigHeap.size() == smallHeap.size()) {
            smallHeap.offer(num);
            bigHeap.offer(smallHeap.poll());
        } else {
            bigHeap.offer(num);
            smallHeap.offer(bigHeap.poll());
        }
    }

    public double findMedian() {
        return smallHeap.size() != bigHeap.size() ? bigHeap.peek() : (smallHeap.peek() + bigHeap.peek()) / 2.0;
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(40);
        mf.addNum(12);
        System.out.println(mf.findMedian());
        mf.addNum(16);
        System.out.println(mf.findMedian());


    }
}
