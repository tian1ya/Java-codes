package dateStructure.chapt07.huffmanTree;

import java.util.Arrays;

public class HuffmanTree {
    int[] data;
    Node root;

    public HuffmanTree(int[] data) {
        this.data = data;
    }

    public Node build() {
        Node[] nodeArray = buildNodeArray(data);
        root = build(nodeArray);
        return root;
    }

    private Node build(Node[] nodeArray) {
        if (nodeArray.length == 1)
            return nodeArray[0];
        bubbleSort(nodeArray);
        Node currentTree = buildBinaryTree(nodeArray[0], nodeArray[1]);
        Node[] newNodes = updateNodeArray(nodeArray, currentTree);
        return build(newNodes);
    }

    private Node[] updateNodeArray(Node[] nodeArray, Node currentTree) {
        Node[] nodes = new Node[nodeArray.length - 1];
        for (int i = 2; i < nodeArray.length; i++) {
            nodes[i - 2] = nodeArray[i];
        }
        nodes[nodeArray.length - 2] = currentTree;
        return nodes;
    }

    private Node buildBinaryTree(Node node, Node node1) {
        return new Node(node.value + node1.value, node, node1);
    }

    private Node[] buildNodeArray(int[] data) {
        Node[] nodes = new Node[data.length];
        for (int i = 0; i < data.length; i++) {
            nodes[i] = new Node(data[i]);
        }
        return nodes;
    }

    private void bubbleSort(Node[] nodeArray) {
        for (int i = 0; i < nodeArray.length; i++) {
            for (int j = i; j < nodeArray.length; j++) {
                if (nodeArray[j].value < nodeArray[i].value) {
                    Node tmp = nodeArray[j];
                    nodeArray[j] = nodeArray[i];
                    nodeArray[i] = tmp;
                }
            }
        }
    }

    // 先使用数组中所有的元素创建若干个二叉树，只有一个节点
    // 排序
    // 取出来权值最小的两个二叉树
    // 创建一颗心的二叉树
    // 把取出来的两个二叉树移除
    // 放入原来的二叉树集合中

    public void printArrayData() {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public void printHumanTree() {
        if (root != null) {
            preOrder(root);
        }
    }

    private void preOrder(Node root) {
        System.out.print(root.value + " ");
        if (root.left != null)
            preOrder(root.left);

        if (root.right != null)
            preOrder(root.right);

    }
}
