package dateStructure.dsPlay.dsa.algrithem.AboutTree;

import java.util.*;

// 113
public class PathSumII {

    public List<List<Integer>> ret = new LinkedList<List<Integer>>();
    public Deque<Integer> path = new LinkedList<Integer>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) return Collections.emptyList();
        dfs(root, targetSum);
        return ret;
    }

    private void dfs(TreeNode root, int targetSum) {
        if (root == null) return;

        path.addLast(root.val);
        targetSum -= root.val;
        if (root.left == null && root.right == null && targetSum == 0) {
            ret.add(new LinkedList<>(path));
        }

        dfs(root.right, targetSum);
        dfs(root.left, targetSum);
        path.pollLast();
    }

    public static void main(String[] args) {
        TreeNode treeNode4 = new TreeNode(7);
        TreeNode treeNode5 = new TreeNode(2);

        TreeNode treeNode3 = new TreeNode(11);
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;

        TreeNode treeNode1 = new TreeNode(4);
        treeNode1.left = treeNode3;

        TreeNode treeNode6 = new TreeNode(13);

        TreeNode treeNode8 = new TreeNode(1);
        TreeNode treeNode9 = new TreeNode(5);
        TreeNode treeNode7 = new TreeNode(4);
        treeNode7.right = treeNode8;
        treeNode7.left = treeNode9;


        TreeNode treeNode2 = new TreeNode(8);
        treeNode2.left = treeNode6;
        treeNode2.right = treeNode7;
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
    }
}
