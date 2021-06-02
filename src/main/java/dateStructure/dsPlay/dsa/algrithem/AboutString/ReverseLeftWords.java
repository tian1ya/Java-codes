package dateStructure.dsPlay.dsa.algrithem.AboutString;

public class ReverseLeftWords {
    // 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部
    public static String reverseLeftWords(String s, int n) {
        if (s.isEmpty()) return "";
        String first = s.substring(0, n);
        return s.substring(n) + first;
    }

    public static void main(String[] args) {
        System.out.println(reverseLeftWords("lrloseumgh", 6));
    }
}
