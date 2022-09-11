package dateStructure.dsPlay.dsa.algrithem.AboutTree;

// 654
public class ConstructMaximumBinaryTree {
    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length == 0) return null;
        return build(nums, 0, nums.length-1);
    }

    private static TreeNode build(int[] nums, int startIndex, int endIndex) {

        if (startIndex > endIndex) return null;

        int maxIndex = startIndex;
        int temMaxVal = nums[maxIndex];
        for (int i = startIndex; i <= endIndex; i++) {
            if (nums[i] > temMaxVal) {
                temMaxVal = nums[i];
                maxIndex = i;
            }
        }

        TreeNode treeNode = new TreeNode(temMaxVal);
        treeNode.left = build(nums, startIndex, maxIndex - 1);
        treeNode.right = build(nums, maxIndex + 1, endIndex);
        return treeNode;
    }

    public static void main(String[] args) {
//        TreeNode treeNode4 = new TreeNode(1);
//        TreeNode treeNode3 = new TreeNode(2);
//        treeNode3.right = treeNode4;
//        TreeNode treeNode1 = new TreeNode(3);
//        treeNode1.right = treeNode3;
//
//        TreeNode treeNode5 = new TreeNode(0);
//        TreeNode treeNode2 = new TreeNode(5);
//        treeNode2.left = treeNode5;
//
//        TreeNode treeNode = new TreeNode(6, treeNode1, treeNode2);

        TreeNode treeNode6 = constructMaximumBinaryTree(new int[]{3,2,1,6,0,5});
        System.out.println("a");
    }
}
