package dateStructure.dsPlay.dsa.algrithem.AboutTree;

import java.util.HashMap;
import java.util.Map;

public class BuildTree2 {
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length < 1 || postorder.length < 1) return null;
        Map<Integer, Integer> valueToIndex = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            valueToIndex.put(inorder[i], i);
        }

        return buildTreeInner(valueToIndex,
                inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1
        );
    }

    public static TreeNode buildTreeInner(Map<Integer, Integer> valueToIndex,
                                          int[] inorder, int inStartIdx, int inEndIdx,
                                          int[] postorder, int poStartIdx, int poEndIdx) {

        if ((inStartIdx > inEndIdx) || (poStartIdx > poEndIdx)) return null;
        int rootVal = postorder[poEndIdx];

        Integer rootValIndxInInOrderArray = valueToIndex.get(rootVal);
        Integer leftSize = rootValIndxInInOrderArray - inStartIdx;

        TreeNode node = new TreeNode(rootVal);

        node.left = buildTreeInner(valueToIndex,
                inorder, inStartIdx, rootValIndxInInOrderArray - 1,
                postorder, poStartIdx, poStartIdx + leftSize-1);

        node.right = buildTreeInner(valueToIndex,
                inorder, rootValIndxInInOrderArray + 1, inEndIdx,
                postorder, poStartIdx + leftSize, poEndIdx - 1);

        return node;
    }

    public static void main(String[] args) {
        TreeNode treeNode = buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3});
        System.out.println("a");
    }
}
