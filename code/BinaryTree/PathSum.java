package code.BinaryTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author Jevis Hoo
 * @date 2021/2/2 12:10
 * @description Path Sum
 * <p>
 * Given the root of a binary tree and an integer targetSum,
 * >>> 1
 * return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 * <p>
 * >>> 2
 * return all root-to-leaf paths where each path's sum equals targetSum.
 * <p>
 * >>> 3
 * Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf, but it must go downwards
 * (traveling only from parent nodes to child nodes).
 */
public class PathSum {
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.value == targetSum && root.left == null && root.right == null) {
            return true;
        }

        boolean lData = hasPathSum(root.left, targetSum - root.value);
        boolean rData = hasPathSum(root.right, targetSum - root.value);

        return lData || rData;
    }

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> list = new ArrayList<>();
        ArrayList<Integer> subList = new ArrayList<>();
        getPathSum(root, targetSum, subList, list);
        return list;
    }

    public static void getPathSum(TreeNode root, int targetSum, ArrayList<Integer> subList, List<List<Integer>> list) {
        if (root == null) {
            return;
        }
        subList.add(root.value);
        if (root.value == targetSum && root.left == null && root.right == null) {
            ArrayList<Integer> copyList = new ArrayList<>(subList);
            list.add(copyList);
            return;
        }
        getPathSum(root.left, targetSum - root.value, subList, list);
        if (root.left != null) {
            subList.remove(subList.size() - 1);
        }
        getPathSum(root.right, targetSum - root.value, subList, list);
        if (root.right != null) {
            subList.remove(subList.size() - 1);
        }
    }

    public static int sumNumber;

    static class ReturnType {

        public HashMap<Integer, Integer> sumMap;

        public ReturnType(HashMap<Integer, Integer> sumMap) {
            this.sumMap = sumMap;
        }
    }

    public static int pathSum3(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        sumNumber = 0;
//        process(root, sum);

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        process(root, map, sum, 0);
        return sumNumber;
    }

    public static void process(TreeNode root, HashMap<Integer, Integer> map, int sum, int currentSum) {
        if (root == null) {
            return;
        }

        currentSum += root.value;
        sumNumber += map.getOrDefault(currentSum - sum, 0);

        map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        process(root.left, map, sum, currentSum);
        process(root.right, map, sum, currentSum);
        map.put(currentSum, map.getOrDefault(currentSum, 1) - 1);
    }

    public static HashMap<Integer, Integer> process(TreeNode root, int sum) {
        if (root == null) {
            return new HashMap<>();
        }

        HashMap<Integer, Integer> lData = process(root.left, sum);
        HashMap<Integer, Integer> rData = process(root.right, sum);

        if (root.value == sum) {
            sumNumber++;
        }

        if (lData.containsKey(sum - root.value)) {
            sumNumber += lData.get(sum - root.value);
        }

        if (rData.containsKey(sum - root.value)) {
            sumNumber += rData.get(sum - root.value);
        }


        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(root.value, 1);

        if (root.left != null) {
            for (int key : lData.keySet()) {
                int newKey = key + root.value;
                if (map.containsKey(newKey)) {
                    map.put(newKey, map.get(newKey) + lData.get(key));
                } else {
                    map.put(newKey, lData.get(key));
                }
            }
        }

        if (root.right != null) {
            for (int key : rData.keySet()) {
                int newKey = key + root.value;

                if (map.containsKey(newKey)) {
                    map.put(newKey, map.get(newKey) + rData.get(key));
                } else {
                    map.put(newKey, rData.get(key));
                }
            }
        }
        return map;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(0);
        root.right = new TreeNode(9);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        System.out.println(hasPathSum(root, 15));

        TreeNode head = new TreeNode(5);
        head.left = new TreeNode(4);
        head.left.left = new TreeNode(11);
        head.left.left.left = new TreeNode(7);
        head.left.left.right = new TreeNode(2);

        head.right = new TreeNode(8);
        head.right.left = new TreeNode(13);
        head.right.right = new TreeNode(4);
        head.right.right.left = new TreeNode(5);
        head.right.right.right = new TreeNode(1);

        int targetSum = 22;
        List<List<Integer>> list = pathSum(head, targetSum);
        System.out.println(Collections.singletonList(list));

        head = new TreeNode(10);
        head.left = new TreeNode(5);
        head.left.left = new TreeNode(3);
        head.left.right = new TreeNode(2);
        head.left.right.right = new TreeNode(1);
        head.left.left.left = new TreeNode(3);
        head.left.left.right = new TreeNode(-2);

        head.right = new TreeNode(-3);
        head.right.right = new TreeNode(11);

        System.out.println(pathSum3(head, 8));
//        System.out.println(pathSum3(head, 10));

        head = new TreeNode(1);
        head.left = new TreeNode(-2);
        head.right = new TreeNode(-3);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(3);
        head.right.left = new TreeNode(-2);
        head.left.left.left = new TreeNode(-1);
        System.out.println(pathSum3(head, -1));

        head = new TreeNode(1);

        head.left = new TreeNode(0);
        head.right = new TreeNode(1);

        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(2);
        head.right.left = new TreeNode(0);
        head.right.right = new TreeNode(-1);

        head.left.left.left = new TreeNode(0);
        head.left.left.right = new TreeNode(1);
        head.left.right.left = new TreeNode(-1);
        head.left.right.right = new TreeNode(0);
        head.right.left.left = new TreeNode(-1);
        head.right.left.right = new TreeNode(0);
        head.right.right.left = new TreeNode(1);
        head.right.right.right = new TreeNode(0);
        System.out.println(pathSum3(head, 2));

    }
}
