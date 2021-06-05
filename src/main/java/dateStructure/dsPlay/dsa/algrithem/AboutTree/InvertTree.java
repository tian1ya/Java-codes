package dateStructure.dsPlay.dsa.algrithem.AboutTree;

public class InvertTree {
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        invertTreeInner(root);
        return root;
    }

    public static void invertTreeInner(TreeNode root) {
        if (root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTreeInner(root.right);
        invertTreeInner(root.left);
    }

    public static void main(String[] args) {
        TreeNode treeNode72 = new TreeNode(1);
        TreeNode treeNode152 = new TreeNode(3);

        TreeNode treeNode9 = new TreeNode(2, treeNode72, treeNode152);

        TreeNode treeNode7 = new TreeNode(9);
        TreeNode treeNode15 = new TreeNode(6);
        TreeNode treeNode20 = new TreeNode(7, treeNode15, treeNode7);

        TreeNode treeNode3 = new TreeNode(4, treeNode9, treeNode20);

        System.out.println(invertTree(treeNode3));
        System.out.println("");
    }
}
