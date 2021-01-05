package dateStructure.chapt07.huffmanTree;

public class Test {
    public static void main(String[] args) {
        int[] arr = {3,5,8,11,29,14,23,7};
        HuffmanTree huffmanTree = new HuffmanTree(arr);
        huffmanTree.build();

        huffmanTree.printArrayData();
        huffmanTree.printHumanTree();
    }

}
