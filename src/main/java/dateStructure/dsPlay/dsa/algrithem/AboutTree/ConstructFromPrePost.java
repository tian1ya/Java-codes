package dateStructure.dsPlay.dsa.algrithem.AboutTree;

import java.util.HashMap;
import java.util.Map;

public class ConstructFromPrePost {

    static Map<Integer, Integer> preOrderValToIdx = new HashMap<>();
    static Map<Integer, Integer> postOrderValToIdx = new HashMap<>();

    public static TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        if (preorder.length < 1 || postorder.length < 1) return null;

        for (int i = 0; i < preorder.length; i++) {
            preOrderValToIdx.put(preorder[i], i);
            postOrderValToIdx.put(postorder[i], i);
        }

        return constructFromPrePostInner(
                preorder, 0, preorder.length - 1,
                postorder, 0, postorder.length - 1
        );
    }

    public static TreeNode constructFromPrePostInner(int[] preorder, int preStartIdx, int preEndIdx,
                                                     int[] postorder, int poStartIdx, int poEndIdx) {
        if ((preStartIdx > preEndIdx) || (poStartIdx > poEndIdx)) return null;

        // 目前root节点，是 preorder[preStartIdx] 或者  postorder[poEndIdx]
        // root 的右节点=postorder[poEndIdx-1]
        // root 的左节点=preorder[preStartIdx+1]

        TreeNode node = new TreeNode(preorder[preStartIdx]);
        if ((preStartIdx == preEndIdx) || (poStartIdx == poEndIdx)) return node;
        // 这里不能在往下了，发生穿越

        Integer leftIndex = postOrderValToIdx.get(preorder[preStartIdx + 1]);
        Integer leftSize = leftIndex - poStartIdx + 1;

        node.left = constructFromPrePostInner(
                preorder, preStartIdx + 1, preStartIdx + leftSize,
                postorder, poStartIdx, leftIndex
        );

        node.right = constructFromPrePostInner(
                preorder, preStartIdx + 1 + leftSize, preEndIdx,
                postorder, leftIndex + 1, poEndIdx - 1
        );

        return node;

    }

    public static void main(String[] args) {
        TreeNode treeNode = constructFromPrePost(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 5, 2, 6, 7, 3, 1});
//        TreeNode treeNode = constructFromPrePost(new int[]{2, 1}, new int[]{1, 2});
        System.out.println("a");
    }
}
