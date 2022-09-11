package dateStructure.dsPlay.dsa.algrithem.AboutTree;

import cats.kernel.Hash;

import java.util.HashMap;
import java.util.Map;

/*
    105
    根据一棵树的前序遍历与中序遍历构造二叉树。
        前序遍历 preorder = [3,9,20,15,7]
        中序遍历 inorder = [9,3,15,20,7]

   对于任意一颗树而言，前序遍历的形式总是
        [ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]
   即根节点总是前序遍历中的第一个节点。而中序遍历的形式总是
        [ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]

   只要我们在中序遍历中定位到根节点，那么我们就可以分别知道左子树和右子树中的节点数目
 */
public class BuildTree {
    private static Map<Integer, Integer> indexMap;

    public static TreeNode myBuildTree(int[] preorder, int[] inorder, int preorderLeftIndex, int preorderRightIndex, int inorderLeftIndex, int inorderRightIndex) {
        if (preorderLeftIndex > preorderRightIndex) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorderRoot = preorderLeftIndex;
        // 在中序遍历中定位根节点
        int inorderRoot = indexMap.get(preorder[preorderRoot]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorderRoot]);
        // 得到左子树中的节点数目
        int sizeLeftSubtree = inorderRoot - inorderLeftIndex;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 sizeLeftSubtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorderLeftIndex + 1, preorderLeftIndex + sizeLeftSubtree, inorderLeftIndex, inorderRoot - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorderLeftIndex + sizeLeftSubtree + 1, preorderRightIndex, inorderRoot + 1, inorderRightIndex);
        return root;
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    public static void main(String[] args) {
        TreeNode treeNode = buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println("a");
    }
}
