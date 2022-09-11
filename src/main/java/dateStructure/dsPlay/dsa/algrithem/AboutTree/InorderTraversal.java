package dateStructure.dsPlay.dsa.algrithem.AboutTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class InorderTraversal {
    public static List<Integer> inorderTraversal(TreeNode root) {
        return Objects.isNull(root) ? Collections.emptyList() : inorderTraversal(root, new ArrayList<>());
    }

    public static List<Integer> inorderTraversal(TreeNode root, List<Integer> list) {
        if (Objects.isNull(root)) return list;

        inorderTraversal(root.left, list);
        list.add(root.val);
        inorderTraversal(root.right, list);

        return list;
    }

    public static void main(String[] args) {
        TreeNode treeNode72 = new TreeNode(1);
        TreeNode treeNode152 = new TreeNode(3);

        TreeNode treeNode9 = new TreeNode(2, treeNode72, treeNode152);

        TreeNode treeNode7 = new TreeNode(9);
        TreeNode treeNode15 = new TreeNode(6);
        TreeNode treeNode20 = new TreeNode(7, treeNode15, treeNode7);

        TreeNode treeNode3 = new TreeNode(4, treeNode9, treeNode20);

        System.out.println(inorderTraversal(treeNode3));
    }
}
