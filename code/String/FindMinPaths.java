package code.String;

import java.util.*;

/**
 * @author Jevis Hoo
 * @date 2021/3/12 11:14
 * @description 字符串的转换路径问题
 * 给定两个字符串，记为 start 和 to，再给定一个字符串列表 list，list 中一定包含to，list 中没有重复字符串。
 * 规定 start 每次只能改变一个字符，最终的目标是彻底变成 to，但是每次变成的新字符串必须在 list 中存在。
 * 请返回所有最短的变换路径。
 */
public class FindMinPaths {

    public static List<List<String>> findMinPaths(String start, String to,
                                                  List<String> list) {
        list.add(start);
        HashMap<String, ArrayList<String>> nextLists = getNextLists(list);
        HashMap<String, Integer> distances = getDistances(start, nextLists);
        LinkedList<String> pathList = new LinkedList<>();
        List<List<String>> res = new ArrayList<>();
        getShortestPaths(start, to, nextLists, distances, pathList, res);
        return res;
    }

    private static void getShortestPaths(String cur, String to, HashMap<String, ArrayList<String>> nextLists, HashMap<String, Integer> distances, LinkedList<String> pathList, List<List<String>> res) {
        pathList.add(cur);
        if (to.equals(cur)) {
            res.add(new LinkedList<>(pathList));
        } else {
            for (String next : nextLists.get(cur)) {
                if (distances.get(next) == distances.get(cur) + 1) {
                    getShortestPaths(next, to, nextLists, distances, pathList, res);
                }
            }
        }
        pathList.pollLast();
    }

    private static HashMap<String, Integer> getDistances(String start, HashMap<String, ArrayList<String>> nextLists) {
        HashMap<String, Integer> distances = new HashMap<>();
        distances.put(start, 0);
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        HashSet<String> set = new HashSet<>();
        set.add(start);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            for (String str : nextLists.get(cur)) {
                if (!set.contains(str)) {
                    distances.put(str, distances.get(cur) + 1);
                    queue.add(str);
                    set.add(str);
                }
            }
        }
        return distances;
    }

    private static HashMap<String, ArrayList<String>> getNextLists(List<String> words) {
        Set<String> dict = new HashSet<>(words);

        HashMap<String, ArrayList<String>> nextLists = new HashMap<>();
        for (String word : words) {
            nextLists.put(word, new ArrayList<>());
        }
        for (String word : words) {
            nextLists.put(word, getNext(word, dict));
        }
        System.out.println(nextLists);
        return nextLists;
    }

    private static ArrayList<String> getNext(String word, Set<String> dict) {
        ArrayList<String> res = new ArrayList<>();
        char[] chs = word.toCharArray();
        int score;
        for (String str : dict) {
            char[] chars = str.toCharArray();
            int[] diff = new int[str.length()];
            for (int i = 0; i < chars.length; i++) {
                diff[i] = Math.abs(chars[i] - chs[i]);
            }
            score = 0;
            for (int num : diff) {
                if (num == 0) {
                    score++;
                }
            }
            if (score == 2) {
                res.add(str);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        String start = "abc", end = "cab";
        String[] str = {"cab", "acc", "cbc", "ccc", "cac", "cbb", "aab", "abb"};
        List<String> list = new ArrayList<>(Arrays.asList(str));
        System.out.println(findMinPaths(start, end, list));
    }
}
