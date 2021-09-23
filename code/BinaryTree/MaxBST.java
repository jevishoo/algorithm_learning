package code.BinaryTree;

import code.StackQueue.PreorderTraversal;

/**
 * @since 2020/11/20 10:46
 * @Created by Jevis_Hoo
 * @Description 找到二叉树中的最大搜索二叉子树
 * <p>
 * 树形 DP 套路
 * <p>
 * 树形 DP 套路使用前提：
 * 如果题目求解目标是 S 规则，则求解流程可以定成以每一个节点为头节点的子树在 S 规则下的每一个答案，并且最终答案一定在其中。
 * <p>
 * 本体题解目标是：整棵二叉树中的最大搜索二叉子树，
 * 那么求解流程可以定成：在整棵二叉树中，求出每一个节点为头节点的子树的最大搜索二叉子树（对任何一棵子树都求出答案），
 * 并且最终答案（整棵二叉树的最大搜索二叉子树）一定在其中
 * <p>
 * 树形 DP 套路第一步：
 * >>> 以某个节点 X 为头节点的子树中，分析答案有哪些可能性，
 * >>> 并且这种分析是以 X 的左子树、X 的右子树和 X 整棵树的角度来考虑可能性的
 * 树形 DP 套路第二步：
 * >>> 根据第一步的可能性分析，列出所有需要的信息
 * 树形 DP 套路第三步：
 * >>> 合并第二步的信息，对左树和右树提出 !!!同样的要求!!!，并写出信息结构
 * 树形 DP 套路第四步：
 * >>> 设计递归函数，递归函数是处理以 X 为头节点的情况下的答案，包括 1.设计递归的base case，
 * >>> 2.默认直接得到左树和右树的所有信息，3.把可能性做整合，4.并且要返回第三步的信息结构
 */
public class MaxBST {
    /*
    一、
    以节点X 为头节点的子树中，最大的搜索二叉子树只可能是以下三种情况中可能性最大的那种。
    >>> 第一种：X 为头节点的子树中，最大的搜索二叉子树就是 X 的左子树中的最大搜索二叉子树。也就是说，答案可能来自左子树。
        比如，本例中，当 X 为节点12 时。
    >>> 第二种：X 为头节点的子树中，最大的搜索二叉子树就是 X 的右子树中的最大搜索二叉子树。也就是说，答案可能来自右子树。
        比如，本例中，当 X 为节点6 时。
    >>> 第三种：如果 X 左子树上的最大搜索二叉子树是 X 左子树的全体，X 右子树上的最大搜索二叉子树是 X 右子树的全体，
        并且 X 的值大于 X 左子树所有节点的最大值，但小于 X 右子树所有节点的最小值，那么 X 为头节点的子树中，
        最大的搜索二叉子树就是以 X 为头节点的全体。也就是说，答案可能是用 X 连起所有。
        比如，本例中，当 X 为节点10 时。
    二、
    为了分析第一、二种可能性，需要分别知道左子树和右子树上的最大搜索二叉子树的头部，记为 leftMaxBSTHead、rightMaxBSTHead，
    因为要比较大小，所以还需要分别知道左子树和右子树上的最大搜索二叉子树的大小，记为 leftBSTSize、rightBSTSize，
    并且有了这些信息还能帮助分析第三种可能性，因为如果知道了 leftMaxBSTHead，并且发现它正好是 X 的左孩子节点，
    则说明 X 左子树上的最大搜索二叉子树是 X 左子树的全体。同理，可以利用 rightMaxBSTHead 来判断 X 右子树上的最大搜索二叉子树是否为 X 右子树的全体。
    但是有这些还不够，因为第三种可能性还要求 X 的值大于 X 左子树所有节点的最大值，但小于 X 右子树所有节点的最小值。
    因此，需要从左子树上取得左子树的最大值 leftMax，从右子树上取得右子树的最小值 rightMin。
    汇总一下，为了分析所有的可能性，
    >>> 左树上需要的信息为：leftMaxBSTHead、leftBSTSize、leftMax；
    >>> 右树上需要的信息为：rightMaxBSTHead、rightBSTSize、rightMin。
    三、
    左树和右树都需要最大搜索二叉子树的头节点及其大小这两个信息，但是左树只需要最大值，右树只需要最小值，需要合并变成统一要求
    四、
     */

    public static class ReturnType {
        public TreeNode maxBSTHead;
        public int maxBSTSize;
        public int min;
        public int max;

        public ReturnType(TreeNode maxBSTHead, int maxBSTSize, int min, int max) {
            this.maxBSTHead = maxBSTHead;
            this.maxBSTSize = maxBSTSize;
            this.min = min;
            this.max = max;
        }
    }

    public static TreeNode getMaxBST(TreeNode root) {
        return process(root).maxBSTHead;
    }

    public static ReturnType process(TreeNode head) {
        if (head == null)
            return new ReturnType(null, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);

        ReturnType left = process(head.left);
        ReturnType right = process(head.right);

        int min = Math.min(head.value, Math.min(left.min, right.min));
        int max = Math.max(head.value, Math.max(left.max, right.max));

        int maxBSTSize = Math.max(left.maxBSTSize, right.maxBSTSize);
        TreeNode maxBSTHead = left.maxBSTSize >= right.maxBSTSize ? left.maxBSTHead : right.maxBSTHead;

        if (left.maxBSTHead == head.left && right.maxBSTHead == head.right
                && head.value > left.max && head.value < right.min) {
            maxBSTHead = head;
            maxBSTSize = left.maxBSTSize + right.maxBSTSize + 1;
        }

        return new ReturnType(maxBSTHead, maxBSTSize, min, max);
    }

    public static void main(String[] args) {
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

        System.out.println(PreorderTraversal.preorderTraversal(getMaxBST(root)));
    }
}