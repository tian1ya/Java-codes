package dateStructure.dsPlay.dsa.algrithem.AboutTree;

public class DiameterOfBinaryTree {
    private int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        /*
            任意一个节点的左高度 + 右高度
            这里也是从低向上的一种方式
         */
        if (root == null) return 0;
        maxDiameter(root);
        return diameter;
    }

    private int maxDiameter(TreeNode root) {
        if (root == null) return 0;
        int heightL = maxDiameter(root.right);
        int heightR = maxDiameter(root.left);
        this.diameter = Math.max(this.diameter, heightL + heightR);
        return Math.max(heightR, heightL) + 1;
    }


}
