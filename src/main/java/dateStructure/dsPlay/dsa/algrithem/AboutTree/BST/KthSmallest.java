package dateStructure.dsPlay.dsa.algrithem.AboutTree.BST;


import dateStructure.dsPlay.dsa.algrithem.AboutTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class KthSmallest {
    /*
        BST
        中序遍历就天生的将BST 按照小到大的遍历顺序的
     */
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> inorder = inorder(root, new ArrayList<Integer>());
        return inorder.get(k - 1);
    }

    public List<Integer> inorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            inorder(root.left, list);
            list.add(root.val);
            inorder(root.right, list);
        }
        return list;
    }
}
