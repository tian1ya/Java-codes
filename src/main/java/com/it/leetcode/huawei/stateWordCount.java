package com.it.leetcode.huawei;

import java.util.*;

/*
    TreeMap是一个内部元素排序版的HashMap。同样，TreeSet是一个封装了一个HashSet的成员变量来实现的，底层运用了红黑树的数据结构。
    https://blog.csdn.net/u012050154/article/details/51459679
 */
class Node implements Comparable {
    public Node(char key, int count) {
        this.key = key;
        this.count = count;
    }

    public char key;
    public int count;


    @Override
    public int compareTo(Object o) {
        Node node2 = (Node) o;

        if (count != node2.count)
            return node2.count - count;
        else
            return key - node2.key;
    }
}
public class stateWordCount {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            count(line);
        }

        scanner.close();

    }

    private static void count(String line) {
        TreeSet<Node> treeSet = new TreeSet<>();
        Map<Character, Node> map = new HashMap<>();

        for (int i = 0; i < line.length(); i++) {
            char key = line.charAt(i);
            if (key >= 'a' || key <= 'z' || key >= 'A' || key <= 'Z' || key <= '9' || key >= '0' || key == ' '){
                if (map.containsKey(key))
                    map.get(key).count += 1;
                else
                    map.put(key, new Node(key, 1));
            }
        }

        for (Node value : map.values()) {
            treeSet.add(value);
        }

        StringBuffer buffer = new StringBuffer();
        treeSet.forEach(node -> buffer.append(node.key));

        System.out.println(buffer.toString());
    }
}


//public class stateWordCount {
//    public static void thread.book.main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
////        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
//        while (scanner.hasNext()) {
//            String input = scanner.nextLine();
//            System.out.println(count(input));
//        }
//
//        scanner.close();
//    }
//
//    private static String count(String s) {
//
//        Set<Node> set = new TreeSet<>(new Comparator<Node>() {
//            @Override
//            public int compare(Node t, Node s) {
//
//                if (t.v != s.v) {
//                    return s.v - t.v;
//                }
//
//                return t.c - s.c;
//            }
//        });
//
//        Map<Character, Node> map = new HashMap<>(s.length());
//
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9' || c ==' ') {
//                if (map.containsKey(c)) {
//                    map.get(c).v++;
//                } else {
//                    map.put(c, new Node(c, 1));
//                }
//            }
//        }
//
//
//        for (Node n : map.values()) {
//            set.add(n);
//        }
//
//        StringBuilder builder = new StringBuilder(set.size());
//        for (Node n : set) {
//            builder.append(n.c);
//        }
//
//        return builder.toString();
//    }
//
//    private static class Node {
//        private char c;
//        private int v;
//
//        public Node(char c, int v) {
//            this.c = c;
//            this.v = v;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//
//            Node node = (Node) o;
//
//            return c == node.c;
//
//        }
//
//        @Override
//        public int hashCode() {
//            return (int) c;
//        }
//    }
//}
