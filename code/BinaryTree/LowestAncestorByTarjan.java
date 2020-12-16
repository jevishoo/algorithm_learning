package code.BinaryTree;

import code.Other.UnionFind;

import java.util.*;

/**
 * @Date 2020/12/16 15:04
 * @Created by Jevis_Hoo
 * @Description Tarjan 算法与并查集解决二叉树节点间最近公共祖先的批量查询问题
 * <p>
 * 给定一棵二叉树的头节点 head，并给定所有的查询语句，即一个 Query 类型的数组 Query[] ques，
 * 返回 TreeNode 类型的数组 TreeNode[] ans，ans[i] 代表 ques[i]这条查询的答案
 */
public class LowestAncestorByTarjan {
    /*
    一个 Query 类的实例表示一条查询语句，表示想要查询o1 节点和o2 节点的最近公共祖先节点。
     */
    public static class Query {
        public TreeNode o1;
        public TreeNode o2;

        public Query(TreeNode o1, TreeNode o2) {
            this.o1 = o1;
            this.o2 = o2;
        }
    }

    public static TreeNode[] tarJanQuery(TreeNode head, Query[] queries) {
        HashMap<TreeNode, LinkedList<TreeNode>> queryMap = new HashMap<>();
        HashMap<TreeNode, LinkedList<Integer>> indexMap = new HashMap<>();
        HashMap<TreeNode, TreeNode> ancestorMap = new HashMap<>();

        // get all sets
        UnionFind.UnionFindSet sets = new UnionFind.UnionFindSet(getAllNode(head));

        TreeNode[] ans = new TreeNode[queries.length];
        setQueriesAndSetEasyAnswers(queries, ans, queryMap, indexMap);

        setAnswers(head, ans, queryMap, indexMap, ancestorMap, sets);
        return ans;
    }

    private static void setAnswers(TreeNode head, TreeNode[] ans, HashMap<TreeNode, LinkedList<TreeNode>> queryMap, HashMap<TreeNode, LinkedList<Integer>> indexMap, HashMap<TreeNode, TreeNode> ancestorMap, UnionFind.UnionFindSet sets) {
        if (head == null) {
            return;
        }

        setAnswers(head.left, ans, queryMap, indexMap, ancestorMap, sets);
        sets.union(head.left, head);
        ancestorMap.put(sets.findHead(head), head);

        setAnswers(head.right, ans, queryMap, indexMap, ancestorMap, sets);
        sets.union(head.right, head);
        ancestorMap.put(sets.findHead(head), head);

        LinkedList<TreeNode> nList = queryMap.get(head);
        LinkedList<Integer> iList = indexMap.get(head);
        TreeNode node;
        TreeNode nodeFather;
        int index;

        while (nList != null && !nList.isEmpty()) {
            node = nList.poll();
            index = iList.poll();
            nodeFather = sets.findHead(node);
            if (ancestorMap.containsKey(nodeFather)) {
                ans[index] = ancestorMap.get(nodeFather);
            }
        }

    }

    private static void setQueriesAndSetEasyAnswers(Query[] queries, TreeNode[] ans, HashMap<TreeNode, LinkedList<TreeNode>> queryMap, HashMap<TreeNode, LinkedList<Integer>> indexMap) {
        TreeNode o1;
        TreeNode o2;
        for (int i = 0; i != ans.length; i++) {
            o1 = queries[i].o1;
            o2 = queries[i].o2;
            if (o1 == o2 || o1 == null || o2 == null) {
                ans[i] = o1 != null ? o1 : o2;
            } else {
                if (!queryMap.containsKey(o1)) {
                    queryMap.put(o1, new LinkedList<>());
                    indexMap.put(o1, new LinkedList<>());
                }
                if (!queryMap.containsKey(o2)) {
                    queryMap.put(o2, new LinkedList<>());
                    indexMap.put(o2, new LinkedList<>());
                }
                queryMap.get(o1).add(o2);
                indexMap.get(o1).add(i);

                queryMap.get(o2).add(o1);
                indexMap.get(o2).add(i);
            }
        }
    }

    private static List<TreeNode> getAllNode(TreeNode head) {
        List<TreeNode> res = new ArrayList<>();
        process(head, res);
        return res;
    }

    private static void process(TreeNode head, List<TreeNode> res) {
        if (head == null) {
            return;
        }
        res.add(head);
        process(head.left, res);
        process(head.right, res);
    }


    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node5.left = node7;
        node5.right = node8;
        node3.right = node6;
        node6.left = node9;

        Query query0 = new Query(node4, node7);
        Query query1 = new Query(node7, node8);
        Query query2 = new Query(node8, node9);
        Query query3 = new Query(node9, node4);
        Query query4 = new Query(node6, node6);
        Query query5 = new Query(null, node5);
        Query query6 = new Query(null, null);

        Query[] queries = new Query[]{query0, query1, query2, query3, query4, query5, query6};

        for (TreeNode node : tarJanQuery(node1, queries)) {
            if (node != null)
                System.out.print(node.value + " ");
        }
    }
}
