package dateStructure.chapt07.huffmanTree.HuffmanEncoding;

public class Test {
    public static void main(String[] args) {
        String msg = "can you can a can as a can canner can a can.";

        HuffmanEncoding huffmanEncoding = new HuffmanEncoding(msg);
        byte[] bytesRest = huffmanEncoding.encoding();
        System.out.println(msg.getBytes().length);
        System.out.println(bytesRest.length);
    }
}
