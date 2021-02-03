package code.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
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
        for (List<Integer> subList : list) {
            System.out.println(Arrays.toString(subList.toArray()));
        }
    }
}
