package code.BinaryTree;

/**
 * @author Jevis Hoo
 * @since 2021/4/16 16:08
 * @description 二叉搜索树与双向链表
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。
 * 要求不能创建任何新的节点，只能调整树中节点指针的指向。
 */
public class TreeToDoublyList {
    private static TreeNode head;
    private static TreeNode pre;

    public static TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }
        process(root);

        head.left = pre;
        pre.right = head;

        return head;
    }

    private static void process(TreeNode cur) {
        if (cur == null) {
            return;
        }
        process(cur.left);

        if (pre != null) {
            pre.right = cur;
        } else {
            head = cur;
        }
        cur.left = pre;
        pre = cur;

        process(cur.right);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(5);

        treeToDoublyList(root);
        TreeNode cur = head.right;
        System.out.println(head.value);
        while (cur != head) {
            System.out.println(cur.value);
            cur = cur.right;
        }
    }
}
