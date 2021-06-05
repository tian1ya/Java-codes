package dateStructure.dsPlay.dsa.algrithem.AboutTree;

public class MergeTrees {
    // 617
    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return root1;
        if (root1 != null && root2 != null) {
            // 都不为空
            root1.val = root1.val + root2.val;
        } else if (root1 == null) {
            // 左边为空，那么将右边值赋值到左边
            root1 = root2;
        }
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }

    public static void mergeInner(TreeNode root, TreeNode rootOther) {
//        if (root != null && rootOther != null) {
//            root.val = root.val + rootOther.val;
//        } else (root == null) {
//
//        }
    }

    public static void main(String[] args) {
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode1 = new TreeNode(3, treeNode5, null);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode = new TreeNode(1, treeNode1, treeNode2);

        TreeNode treeNode24 = new TreeNode(4);
        TreeNode treeNode21 = new TreeNode(1, null, treeNode24);
        TreeNode treeNode27 = new TreeNode(7);
        TreeNode treeNode22 = new TreeNode(3, null, treeNode27);
        TreeNode treeNode3 = new TreeNode(2, treeNode21, treeNode22);

        System.out.println(mergeTrees(treeNode, treeNode3));
    }
}
