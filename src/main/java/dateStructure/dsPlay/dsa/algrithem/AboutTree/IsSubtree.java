package dateStructure.dsPlay.dsa.algrithem.AboutTree;

public class IsSubtree {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;
        if (subRoot == null) return true;

        return isSubtreeInner(root, subRoot);
    }

    private boolean isSubtreeInner(TreeNode root, TreeNode subRoot) {

        return false;
    }
}
