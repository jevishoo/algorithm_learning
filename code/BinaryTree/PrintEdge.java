package code.BinaryTree;

/**
 * @Date 2020/11/11 9:25
 * @Created by Jevis_Hoo
 * @Description 打印二叉树的边界节点
 * <p>
 * 标准一：
 * 1．头节点为边界节点。
 * 2．叶节点为边界节点。
 * 3．如果节点在其所在的层中是最左的或最右的，那么该节点也是边界节点。
 * <p>
 * 标准二：
 * 1．头节点为边界节点。
 * 2．叶节点为边界节点。
 * 3．树左边界延伸下去的路径为边界节点。
 * 4．树右边界延伸下去的路径为边界节点。
 */
public class PrintEdge {
    public static void printEdge1(TreeNode root) {
        if (root == null)
            return;
        int height = getHeight(root, 0);
//        System.out.println(height);
        TreeNode[][] edgeMap = new TreeNode[height][2];
        getEdge(root, 0, edgeMap);

        // 打印左边界
        for (int i = 0; i != edgeMap.length; i++) {
            System.out.print(edgeMap[i][0].value + " ");
        }
//        System.out.println();
        // 打印非左非右边界
        getEdgeExceptLeftRight(root, 0, edgeMap);
//        System.out.println();
        // 打印右边界
        for (int i = edgeMap.length - 1; i != 0; i--) {
            System.out.print(edgeMap[i][1].value + " ");
        }

    }

    private static int getHeight(TreeNode root, int height) {
        if (root == null)
            return height;

        return Math.max(getHeight(root.left, height + 1), getHeight(root.right, height + 1));
    }

    private static void getEdge(TreeNode root, int h, TreeNode[][] map) {
        if (root == null)
            return;
        map[h][0] = map[h][0] == null ? root : map[h][0];
        map[h][1] = root;

        getEdge(root.left, h + 1, map);
        getEdge(root.right, h + 1, map);
    }

    private static void getEdgeExceptLeftRight(TreeNode root, int h, TreeNode[][] map) {
        if (root == null)
            return;

        if (root.left == null && root.right == null && root != map[h][0] && root != map[h][1])
            System.out.print(root.value + " ");

        getEdgeExceptLeftRight(root.left, h + 1, map);
        getEdgeExceptLeftRight(root.right, h + 1, map);
    }


    public static void printEdge2(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + " ");
        printLeftEdge(root.left, true);
        printRightEdge(root.right, true);
    }

    private static void printLeftEdge(TreeNode root, boolean extend) {
        if (root == null) {
            return;
        }

        if ((root.left == null && root.right == null) || extend)
            System.out.print(root.value + " ");
        printLeftEdge(root.left, extend);
        printLeftEdge(root.right, extend && root.left == null);
    }

    private static void printRightEdge(TreeNode root, boolean extend) {
        if (root == null) {
            return;
        }

        printRightEdge(root.left, extend && root.right == null);
        printRightEdge(root.right, extend);

        if ((root.left == null && root.right == null) || extend)
            System.out.print(root.value + " ");
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(8);
        root.left.right.right.right = new TreeNode(11);
        root.left.right.right.right.left = new TreeNode(13);
        root.left.right.right.right.right = new TreeNode(14);

        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(9);
        root.right.left.right = new TreeNode(10);
        root.right.left.left.left = new TreeNode(12);
        root.right.left.left.left.left = new TreeNode(15);
        root.right.left.left.left.right = new TreeNode(16);

        printEdge1(root);
        System.out.println();
        printEdge2(root);
    }
}
