package dateStructure.dsPlay.dsa.algrithem.AboutTree;

public class MaxDepth {

    private static int maxDepth(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(maxDepth(node.left), maxDepth(node.right));
    }

    public static void main(String[] args) {
        TreeNode treeNode9 = new TreeNode(9);

        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode15 = new TreeNode(15);
        TreeNode treeNode20 = new TreeNode(20, treeNode15, treeNode7);

        TreeNode treeNode3 = new TreeNode(3, treeNode9, treeNode20);

        System.out.println(maxDepth(treeNode3));
    }
}
