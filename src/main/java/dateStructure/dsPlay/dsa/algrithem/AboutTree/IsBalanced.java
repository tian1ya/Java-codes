package dateStructure.dsPlay.dsa.algrithem.AboutTree;

public class IsBalanced {
    public static boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int heightR = height(root.right);
        int heightL = height(root.left);
        // 不明白为什么需要 && 后面的判断，
        return Math.abs(heightL - heightR) <= 1 && isBalanced(root.left) && isBalanced(root.right);

    }

    // 顶向下的判断，会有重复计算高度的问题
    // 低向上计算，那么 函数 height 值需要调用一次


    public static int height(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public static boolean isBalancedV2(TreeNode root) {
        if (root == null) return true;
        return heightV2(root) > 0;
    }

    public static int heightV2(TreeNode node) {
        // 如果有高度差大于1，那么直接就返回-1
        if (node == null) return 0;
        int rightH = heightV2(node.right);
        int leftH = heightV2(node.left);

        if (rightH == -1 || leftH == -1 || Math.abs(rightH - leftH) > 1) return -1;
        return Math.max(rightH, leftH) + 1;
    }
    public static void main(String[] args) {
//        TreeNode treeNode9 = new TreeNode(9);
//
//        TreeNode treeNode7 = new TreeNode(7);
//        TreeNode treeNode15 = new TreeNode(15);
//        TreeNode treeNode20 = new TreeNode(20, treeNode15, treeNode7);
//        TreeNode treeNode3 = new TreeNode(3, treeNode9, treeNode20);

        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode44 = new TreeNode(4);
        TreeNode treeNode233 = new TreeNode(3, treeNode4, treeNode44);
        TreeNode treeNode23 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2, treeNode233, treeNode23);
        TreeNode treeNode22 = new TreeNode(2);
        TreeNode treeNode = new TreeNode(1, treeNode2, treeNode22);

        System.out.println(isBalanced(treeNode));
    }
}
