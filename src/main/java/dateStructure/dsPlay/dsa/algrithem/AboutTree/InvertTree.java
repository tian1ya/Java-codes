package dateStructure.dsPlay.dsa.algrithem.AboutTree;

/*
    // 二叉树遍历框架
    void traverse(TreeNode root) {
        // 前序遍历
        traverse(root.left);
        // 中序遍历
        traverse(root.right);
        // 后续遍历
    }
 */
public class InvertTree {
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        invertTreeInner(root);
        return root;
    }

    /*
        回到二叉树遍历框架中，恰恰是前序遍历
     */
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
