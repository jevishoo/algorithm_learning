package code.BinaryTree;

/**
 * @Date 2020/11/22 13:45
 * @Created by Jevis_Hoo
 * @Description 调整搜索二叉树中两个错误的节点
 */
public class TwoErrorNodes {
    public static TreeNode[] getTwoErrNodes(TreeNode head) {
        TreeNode[] errs = new TreeNode[2];
        if (head == null) {
            return errs;
        }
        TreeNode pre = null;
        TreeNode mostRight;
        while (head != null) {
            mostRight = head.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != head) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = head;
                    head = head.left;
                } else {
                    if (pre != null && pre.value > head.value) {
                        errs[0] = errs[0] == null ? pre : errs[0];
                        errs[1] = head;
                    }
                    System.out.print(head.value + " ");
                    mostRight.right = null;
                    pre = head;
                    head = head.right;
                }
            } else {
                if (pre != null && pre.value > head.value) {
                    errs[0] = errs[0] == null ? pre : errs[0];
                    errs[1] = head;
                }
                pre = head;
                System.out.print(head.value + " ");
                head = head.right;
            }
        }
        System.out.println();
        return errs;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        root.right = new TreeNode(6);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(7);

        TreeNode[] nodes = getTwoErrNodes(root);
        for (TreeNode node : nodes) {
            System.out.println(node.value);
        }
    }
}
