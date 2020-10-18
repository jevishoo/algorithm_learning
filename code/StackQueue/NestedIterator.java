package code.StackQueue;

import java.util.*;

/**
 * @Date 2020/9/19 10:11
 * @Created by Jevis_Hoo
 * @Description 扁平化嵌套列表迭代器
 * <p>
 * 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 * <p>
 * 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
 */
public class NestedIterator implements Iterator<Integer> {
    private LinkedList<Integer> list = new LinkedList<>();

    public NestedIterator(List<NestedInteger> nestedList) {
        traversalList(nestedList);
    }

    public void traversalList(List<NestedInteger> nestedList) {
        for (NestedInteger item :nestedList) {
            if (item.isInteger()) {
                list.addLast(item.getInteger());
            } else {
                traversalList(item.getList());
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !list.isEmpty();
    }

    @Override
    public Integer next() {
        return list.pollFirst();
    }

}

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
//interface NestedInteger {
//
//    // @return true if this NestedInteger holds a single integer, rather than a nested list.
//    public boolean isInteger();
//
//    // @return the single integer that this NestedInteger holds, if it holds a single integer
//    // Return null if this NestedInteger holds a nested list
//    public Integer getInteger();
//
//    // @return the nested list that this NestedInteger holds, if it holds a nested list
//    // Return null if this NestedInteger holds a single integer
//    public List<NestedInteger> getList();
//}