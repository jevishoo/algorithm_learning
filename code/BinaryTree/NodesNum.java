package code.BinaryTree;

/**
 * @author Jevis_Hoo
 * @date 2021/1/4 10:06
 * @description 统计完全二叉树的节点数
 */
public class NodesNum {
    public static int getNodesNum(TreeNode head) {
        if (head == null) {
            return 0;
        }

        int height = mostLeftLevel(head, 0);
        return bs(head, 1, height);
    }

    private static int bs(TreeNode head, int level, int height) {
        if (level == height) {
            return 1;
        }

        if (mostLeftLevel(head.right, level) == height) {
            return (1 << (height - level)) + bs(head.right, level + 1, height);
        } else {
            return (1 << (height - level - 1)) + bs(head.left, level + 1, height);
        }
    }

    private static int mostLeftLevel(TreeNode cur, int level) {
        while (cur != null) {
            cur = cur.left;
            level++;
        }

        return level;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        root.left.left.right = new TreeNode(7);
        root.left.right.left = new TreeNode(8);
        root.left.right.right = new TreeNode(9);

        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(11);
        root.right.left.left = new TreeNode(12);

        System.out.println(getNodesNum(root));
    }
}
