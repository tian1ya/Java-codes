package dateStructure.dsPlay.dsa.algrithem.AboutTree;

// 112
public class HasPathSum {
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        return hasPathSumInner(root, root.val, targetSum);
    }

    private static boolean hasPathSumInner(TreeNode root, int currentSum, int targetSum) {
        if (root.right == null && root.left == null) return currentSum == targetSum;

        if (root.left != null && hasPathSumInner(root.left, currentSum + root.left.val, targetSum)) {
            return true;
        }
        return root.right != null && hasPathSumInner(root.right, currentSum + root.right.val, targetSum);
    }

    public static void main(String[] args) {
//        TreeNode treeNode4 = new TreeNode(7);
//        TreeNode treeNode5 = new TreeNode(2);
//
//        TreeNode treeNode3 = new TreeNode(11);
//        treeNode3.left = treeNode4;
//        treeNode3.right = treeNode5;
//
//        TreeNode treeNode1 = new TreeNode(4);
//        treeNode1.left = treeNode3;
//
//        TreeNode treeNode6 = new TreeNode(13);
//
//        TreeNode treeNode8 = new TreeNode(1);
//        TreeNode treeNode7 = new TreeNode(4);
//        treeNode7.right = treeNode8;
//
//        TreeNode treeNode2 = new TreeNode(8);
//        treeNode2.left = treeNode6;
//        treeNode2.right = treeNode7;
//        TreeNode treeNode = new TreeNode(5);
//        treeNode.left = treeNode1;
//        treeNode.right = treeNode2;

        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        treeNode.left = treeNode1;

        System.out.println(hasPathSum(treeNode, 0));
    }
}
