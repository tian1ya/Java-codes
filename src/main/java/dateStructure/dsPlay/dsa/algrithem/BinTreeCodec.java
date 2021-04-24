package dateStructure.dsPlay.dsa.algrithem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BinTreeCodec {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "[]";
        if (root.right == null && root.left == null) return "[" + root.val + "]";

        LinkedList<TreeNode> queue = new LinkedList<>();
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode headNode = queue.pop();
            if (headNode != null) {
                sb.append(headNode.val);
                queue.add(headNode.left);
                queue.add(headNode.right);
            } else {
                sb.append("null");
            }
            if (!queue.isEmpty()) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() <= 2) return null;
        String substring = data.substring(1, data.length() - 1);
        if (substring.isEmpty()) return null;

        String[] split = substring.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(split[0]));
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty() && index < split.length) {
            TreeNode treeNode = queue.pop();
            if (!"null".equals(split[index])) {
                TreeNode node = new TreeNode(Integer.parseInt(split[index]));
                treeNode.left = node;
                queue.add(node);
            }
            index++;

            if (!"null".equals(split[index])) {
                TreeNode node = new TreeNode(Integer.parseInt(split[index]));
                treeNode.right = node;
                queue.add(node);
            }
            index++;
        }
        return root;
    }

    public static void main(String[] args) {
//        TreeNode treeNode_1 = new TreeNode(1);
//        TreeNode treeNode_2 = new TreeNode(2);
////        TreeNode treeNode_3 = new TreeNode(3);
////        TreeNode treeNode_4 = new TreeNode(4);
////        TreeNode treeNode_5 = new TreeNode(5);
////
//        treeNode_1.left = treeNode_2;
////        treeNode_1.right = treeNode_3;
////
////        treeNode_3.left = treeNode_4;
////        treeNode_3.right = treeNode_5;

        TreeNode treeNode_1 = new TreeNode(5);
        TreeNode treeNode_2 = new TreeNode(2);
        TreeNode treeNode_3 = new TreeNode(3);
        TreeNode treeNode_4 = new TreeNode(2);
        TreeNode treeNode_5 = new TreeNode(4);
        TreeNode treeNode_6 = new TreeNode(3);
        TreeNode treeNode_7 = new TreeNode(1);
//
        treeNode_1.left = treeNode_2;
        treeNode_1.right = treeNode_3;

        treeNode_3.left = treeNode_4;
        treeNode_3.right = treeNode_5;

        treeNode_4.left = treeNode_6;
        treeNode_4.right = treeNode_7;

        System.out.println("");

        BinTreeCodec codec = new BinTreeCodec();
        System.out.println(codec.serialize(treeNode_1));

        TreeNode deserialize = codec.deserialize("[1]");
        System.out.println(deserialize);

    }
}
