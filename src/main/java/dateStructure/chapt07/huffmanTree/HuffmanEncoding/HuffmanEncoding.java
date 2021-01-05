package dateStructure.chapt07.huffmanTree.HuffmanEncoding;


import scala.util.control.Exception;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/*
    t6
 */

/*
    1. 统计字符数并排序
    2. 创建hfm树
    3. 创建hfm树编码表
    4. 编码
 */
public class HuffmanEncoding {
    private String data;
    private Map<Byte, String> encodingTable = new HashMap<>();


    public HuffmanEncoding(String data) {
        this.data = data;
    }

    public Map<Byte, String> getEncodingTable() {
        return encodingTable;
    }

    /*
        1. 统计每一个byte出现的次数，并放入一个集合中
        2. 创建一颗hfm树
        3. 创建一个hfm编码表
        4. 编码
     */
    public byte[] encoding() {
        Node root = buildNods(data);
        buildHuffmanTreeEncodingTable(root);
        return zip();
    }

    private byte[] zip() {
        StringBuffer sb = new StringBuffer();
        for (byte eleData : this.data.getBytes()) {
            sb.append(this.encodingTable.get(eleData));
        }

        // 存储编码后的 byte
        byte[] encodBytes = new byte[(sb.length() + 7) / 8];
        int index = 0;
        int i1;
        for (int i = 0; i < sb.length(); i += 8) {
            if ((i + 8) > sb.length()) {
                i1 = Integer.parseInt(sb.substring(i), 2);
            } else {
                i1 = Integer.parseInt(sb.substring(i, i + 8));
            }
            encodBytes[index] = (byte) i1;
            index ++;
        }
        return encodBytes;
    }

    private void buildHuffmanTreeEncodingTable(Node root) {
        if (root == null)
            return;

        StringBuffer sb = new StringBuffer();
        buildCodeMap(root.left, "0", sb);
        buildCodeMap(root.right, "1", sb);
    }

    private void buildCodeMap(Node node, String startCode, StringBuffer stringBuffer) {
        // 没经历一root，那么将之前的code 累计，然后加入当前的code，并继续传给下次的递归
        StringBuffer sb = new StringBuffer(stringBuffer);
        sb.append(startCode);
        if (node.data == null) {
            buildCodeMap(node.left, "0", sb);
            buildCodeMap(node.right, "1", sb);
        } else {
            this.encodingTable.put(node.data, sb.toString());
        }
    }

    private Node buildNods(String data) {
        byte[] bytes = data.getBytes();
        Map<Byte, Integer> byteCounts = countBytes(bytes);
        List<Node> nodes = transformCountsToNodes(byteCounts);
        return buildHuffmanTree(nodes);
    }


    private Node buildHuffmanTree(List<Node> nodes) {
        if (nodes.size() == 1)
            return nodes.get(0);

        Collections.sort(nodes);
        Node left = nodes.get(0);
        Node right = nodes.get(1);

        Node currentTree = buildBinaryTree(left, right);
        List<Node> newNodes = updateNodes(nodes, left, right, currentTree);
        return buildHuffmanTree(newNodes);
    }

    private List<Node> updateNodes(List<Node> nodes, Node left, Node right, Node currentTree) {
        nodes.add(nodes.size(), currentTree);
        nodes.remove(left);
        nodes.remove(right);
        return nodes;
    }

    private Node buildBinaryTree(Node left, Node right) {
        return new Node(left.weight + right.weight, left, right);
    }


    private List<Node> transformCountsToNodes(Map<Byte, Integer> byteCounts) {
        return byteCounts.entrySet().stream()
                .map(kv -> new Node(kv.getKey(), kv.getValue()))
                .collect(toList());
    }

    private Map countBytes(byte[] bytes) {
        Map<Byte, Integer> map = new HashMap<>();
        for (byte aByte : bytes) {
            if (map.containsKey(aByte))
                map.put(aByte, map.get(aByte) + 1);
            else
                map.put(aByte, 1);
        }
        return map;
    }
}
