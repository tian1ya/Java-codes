package dateStructure.chapt07.huffmanTree.HuffmanEncoding;

public class Node implements Comparable<Node> {
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(byte data) {
        this.data = data;
    }

    public Node(byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    public Node(byte data, int weight, Node left, Node right) {
        this.data = data;
        this.weight = weight;
        this.left = left;
        this.right = right;
    }

    public Node(int weight, Node left, Node right) {
        this.weight = weight;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node node) {
        return weight - node.weight;
    }
}
