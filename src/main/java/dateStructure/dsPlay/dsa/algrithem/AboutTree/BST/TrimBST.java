package dateStructure.dsPlay.dsa.algrithem.AboutTree.BST;

import dateStructure.dsPlay.dsa.algrithem.AboutTree.TreeNode;

public class TrimBST {
    public static TreeNode trimBST(TreeNode root, int low, int high) {
        return trimBSTInner(root, low, high);
    }

    private static TreeNode trimBSTInner(TreeNode root, int low, int high) {
        if (root == null) return root;
        if (root.val < low) // 处理右边，左边直接删除
            return trimBSTInner(root.right, low, high);

        if (root.val > high) // 处理左边，右边直接删除
            return trimBSTInner(root.left, low, high);

        root.left = trimBSTInner(root.left, low, high);
        root.right = trimBSTInner(root.right, low, high);
        return root;
    }

    public static void main(String[] args) {
        /*
            trimBST(TreeNode root, int low, int high)
                1. 如果是右边的结点是 > high, 那么该结点剩下的所有结点都应该删除
                2. 如果是右边的结点是 < low， 不会出现这种情况，因为右边结点始终是大于它的父结点，如果右侧出现这种情况，那么意味着整颗树都 < low
                3. 如果是左边的结点是 < low, 那么删除该结点
                    1. 该结点存在左右子树，左字数全部都需要删除的，右子树直接赋值
                    2. 不存在左字数，右子树直接赋值
         */

        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2,treeNode2, null);

        TreeNode treeNode0 = new TreeNode(0, null, treeNode1);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode = new TreeNode(3, treeNode0,  treeNode4);

//        TreeNode treeNode1 = new TreeNode(0);
//        TreeNode treeNode2 = new TreeNode(2);
//        TreeNode treeNode = new TreeNode(1, treeNode1, treeNode2);


        TreeNode x = trimBST(treeNode, 1, 3);
        System.out.println("");
    }
}
