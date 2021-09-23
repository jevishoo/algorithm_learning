package code.BinaryTree;

import java.util.HashMap;

/**
 * @since 2020/11/21 15:20
 * @Created by Jevis_Hoo
 * @Description
 */
public class MaxBSTTopoSize {
    public static int getMaxBSTTopoSize1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int max = maxTopo(root, root);
        max = Math.max(max, getMaxBSTTopoSize1(root.left));
        max = Math.max(max, getMaxBSTTopoSize1(root.right));
        return max;
    }

    public static int maxTopo(TreeNode h, TreeNode n) {
        if (n != null && isBSTNode(h, n, n.value)) {
//            System.out.println(n.value);
            return maxTopo(h, n.left) + maxTopo(h, n.right) + 1;
        }
        return 0;
    }

    public static boolean isBSTNode(TreeNode h, TreeNode n, int value) {
        if (h == null) {
            return false;
        }
        if (h == n) {
            return true;
        }
        return isBSTNode(h.value > value ? h.left : h.right, n, value);
    }


    public static class Record {
        public int left;
        public int right;

        public Record(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    /*
    拓扑贡献记录
     */
    public static int getMaxBSTTopoSize2(TreeNode root) {
        HashMap<TreeNode, Record> map = new HashMap<>();
        return posOrder(root, map);
    }

    public static int posOrder(TreeNode head, HashMap<TreeNode, Record> map) {
        if (head == null) {
            return 0;
        }
        int ls = posOrder(head.left, map);
        int rs = posOrder(head.right, map);

        modifyMap(head.left, head.value, map, true);
        modifyMap(head.right, head.value, map, false);

        Record lr = map.get(head.left);
        Record rr = map.get(head.right);
        int lBST = lr == null ? 0 : lr.left + lr.right + 1;
        int rBST = rr == null ? 0 : rr.left + rr.right + 1;

        map.put(head, new Record(lBST, rBST));

        return Math.max(lBST + rBST + 1, Math.max(ls, rs));
    }

    private static int modifyMap(TreeNode sub, int hValue, HashMap<TreeNode, Record> map, boolean isLeftChild) {
        if (sub == null || (!map.containsKey(sub))) {
            return 0;
        }
        Record r = map.get(sub);
        if ((isLeftChild && sub.value > hValue) || ((!isLeftChild) && sub.value < hValue)) {
            map.remove(sub);
            return r.left + r.right + 1;
        } else {
            int minus = modifyMap(isLeftChild ? sub.right : sub.left, hValue, map, isLeftChild);
            if (isLeftChild) {
                r.right = r.right - minus;
            } else {
                r.left = r.left - minus;
            }
            map.put(sub, r);
            return minus;
        }
    }


    public static void main(String[] args) {
//        TreeNode root = new TreeNode(21);
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(3);

        root.right = new TreeNode(12);
        root.right.left = new TreeNode(10);
        root.right.left.left = new TreeNode(4);
        root.right.left.left.left = new TreeNode(2);
        root.right.left.left.right = new TreeNode(5);
        root.right.left.right = new TreeNode(14);
        root.right.left.right.left = new TreeNode(11);
        root.right.left.right.right = new TreeNode(15);
        root.right.right = new TreeNode(13);
        root.right.right.left = new TreeNode(20);
        root.right.right.right = new TreeNode(16);

        int size1 = getMaxBSTTopoSize1(root);
        System.out.println("max size1 is " + size1);
        int size2 = getMaxBSTTopoSize2(root);
        System.out.println("max size2 is " + size2);
    }
}
