package dateStructure.dsPlay.dsa.algrithem.AboutTree;

public class SearchBST {
    public TreeNode searchBST(TreeNode root, int val) {
        return searchInner(root, val);
    }

    public TreeNode searchInner(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
        return root.val > val? searchInner(root.left, val) : searchInner(root.right, val);
    }



    public static void main(String[] args) {

        TreeNode treeNode3 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(1, null, treeNode3);
        TreeNode treeNode0 = new TreeNode(1);
        TreeNode treeNode9 = new TreeNode(1, treeNode0, treeNode2);

        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode7 = new TreeNode(7, null, treeNode8);
        TreeNode treeNode15 = new TreeNode(5);
        TreeNode treeNode20 = new TreeNode(6, treeNode15, treeNode7);

        TreeNode treeNode4 = new TreeNode(4, treeNode9, treeNode20);

        SearchBST bst = new SearchBST();
        TreeNode treeNode = bst.searchBST(treeNode4, 4);
        System.out.println("");
    }
}
