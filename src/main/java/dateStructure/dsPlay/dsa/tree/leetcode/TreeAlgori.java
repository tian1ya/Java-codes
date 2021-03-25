package dateStructure.dsPlay.dsa.tree.leetcode;

public class TreeAlgori {

    public static void main(String[] args) {
//        TreeNode treeNode = sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
//        System.out.println("a");

//        TreeNode treeNode3 = new TreeNode(15);
//        TreeNode treeNode4 = new TreeNode(7);
//
//        TreeNode treeNode2 = new TreeNode(20);
//        treeNode2.right = treeNode4;
//        treeNode2.left = treeNode3;
//
//        TreeNode treeNode1 = new TreeNode(9);
//
//        TreeNode treeNode = new TreeNode(3);
//        treeNode.right = treeNode2;
//        treeNode.left = treeNode1;
//
//        System.out.println(maxDepth(treeNode));

        TreeNode treeNode3 = new TreeNode(1);
        TreeNode treeNode4 = new TreeNode(3);

        TreeNode treeNode2 = new TreeNode(2);
        treeNode2.right = treeNode4;
        treeNode2.left = treeNode3;

        TreeNode treeNode1 = new TreeNode(9);
        TreeNode treeNode12 = new TreeNode(6);

        TreeNode treeNode = new TreeNode(7);
        treeNode.right = treeNode1;
        treeNode.left = treeNode12;

        TreeNode treeNode222 = new TreeNode(4);
        treeNode222.right = treeNode;
        treeNode222.left = treeNode2;

        TreeNode treeNode5 = mirrorTree(treeNode222);
        System.out.println("a");

    }

    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) return root;

        mirrorTreeInner(root);
        return root;
    }

    public static TreeNode mirrorTreeInner(TreeNode root) {
        if (root == null)
            return root;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        mirrorTreeInner(root.left);
        mirrorTreeInner(root.right);
        return root;
    }

    public static int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return maxDepthInner(root);
    }

    public static int maxDepthInner(TreeNode node) {
        if (node == null)
            return 0;

        return 1 + Math.max(maxDepthInner(node.left), maxDepthInner(node.right));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return sortedArrayToBSTInner(nums, 0, nums.length - 1);
    }

    public static TreeNode sortedArrayToBSTInner(int[] nums, int left, int right) {
        if (left < 0 || right >= nums.length || left > right) {
            return null;
        }

        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBSTInner(nums, left, mid - 1);
        node.right = sortedArrayToBSTInner(nums, mid + 1, right);

        return node;
    }

}
