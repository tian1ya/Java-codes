package dateStructure.dsPlay.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/*
    返回 s 字典序最小的子序列，该子序列包含 s 的所有不同字符，且只包含一次。

    解题思路：
        遍历s，当出现一个已经在栈中的字母时，直接跳过该字母，并将计数–
        若出现一个不在栈中的字母，则判断该字母与栈顶字母的大小：
        如果该字母小于栈顶字母，且栈顶字母之后还会出现，就讲栈顶字母弹出
        若栈顶字母之后不会再出现，或该字母大于栈顶字母，则加入该字母

    注意这里的 字典序理解 https://www.jisuanke.com/article/9v3lyb4y

 */

public class SmallestSubSequence {
    public String smallestSubsequence(String s) {

        char[] chars = s.toCharArray();

        //记录每个元素最后一次出现的位置
        int[] lastInx = new int[26];

        for (int i = 0; i < chars.length; i++) {
            lastInx[chars[i] - 'a'] = i;
        }

        Deque<Character> stack = new ArrayDeque<>();

        //某一个字符是否在栈中出现
        boolean[] visited = new boolean[26];

        for (int i = 0; i < chars.length; i++) {
            if (visited[chars[i] - 'a']) { //如果出现舍弃当前字符
                continue;
            }

            //栈顶字母比较大，且在后续中还会存在该栈顶字母，则出栈
            while (!stack.isEmpty() && stack.peekLast() > chars[i] && lastInx[stack.peekLast() - 'a'] > i) {
                // 小于栈顶元素： 不符合字典序，需要移除
                // 且栈顶元素还会出现，窄顶元素移除。栈顶元素大且后续还有，那么后移
                Character c = stack.removeLast();  //移除栈顶元素
                visited[c - 'a'] = false; //表示该字符没有在栈中出现，等待后续出现，因为是要对字点进行排序，就需要将较大的char 往后排序
            }

            stack.addLast(chars[i]);
            visited[chars[i] - 'a'] = true;
        }


        StringBuilder sb = new StringBuilder();
        for (Character c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }

    public String smallestSubsequenceV2(String s) {
        char[] chars = s.toCharArray();
        int[] lastIndex = new int[26];
        boolean[] isVisited = new boolean[26];

        for (int i = 0; i < chars.length; i++) {
            lastIndex[chars[i] - 'a'] = i;
        }

        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (isVisited[c - 'a']) continue;

            while (!stack.isEmpty() && stack.peekLast() > c && lastIndex[stack.peekLast() - 'a'] > i) {
                Character character = stack.removeLast();
                isVisited[character - 'a'] = false;
            }

            stack.addLast(c);
            isVisited[c - 'a'] = true;
        }

        StringBuffer sb = new StringBuffer();
        for (Character character : stack) {
            sb.append(character);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "cdadabcc";

        String s1 = new SmallestSubSequence().smallestSubsequence(s);
        String s2 = new SmallestSubSequence().smallestSubsequenceV2(s);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2);
    }
}
