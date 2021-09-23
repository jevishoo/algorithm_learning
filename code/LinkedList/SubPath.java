package code.LinkedList;

/**
 * @since 2020/11/10 8:10
 * @Created by Jevis_Hoo
 * @Description
 */
public class SubPath {
    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int value) {
            this.value = value;
        }

        public TreeNode(int value, TreeNode left, TreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static boolean isSubPath(Node head, TreeNode root) {
        if (head == null)
            return true;

        if (root == null)
            return false;

        return checkRoot(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    public static boolean checkRoot(Node head, TreeNode root) {
        if (head == null)
            return true;

        if (root == null)
            return false;

        if (head.value != root.value) {
            return false;
        } else {
            return checkRoot(head.next, root.right) || checkRoot(head.next, root.left);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(1);

        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.left.left = new TreeNode(6);
        root.right.left.right = new TreeNode(8);
        root.right.left.right.left = new TreeNode(1);
        root.right.left.right.right = new TreeNode(3);

        Node node = new Node(4);
        node.next = new Node(2);
        node.next.next = new Node(8);

        System.out.println(isSubPath(node, root));

    }
}
