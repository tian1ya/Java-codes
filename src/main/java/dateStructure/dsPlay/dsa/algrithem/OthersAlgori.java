package dateStructure.dsPlay.dsa.algrithem;

import java.util.*;
import java.util.stream.Stream;

/*
    ASCII
        码使用指定的7 位或8 位二进制数组合来表示128 或256 种可能的字符。
        标准ASCII 码也叫基础ASCII码，使用7 位二进制数（剩下的1位二进制为0）来表示所有的大写和小写字母，数字0 到9、标点符号，
        以及在美式英语中使用的特殊控制字符

        0～31及127(共33个)是控制字符或通信专用字符（其余为可显示字符），如控制符
        32～126(共95个)是字符(32是空格），
            其中48～57为0到9十个阿拉伯数字。
            65～90为26个大写英文字母，
            97～122号为26个小写英文字母，
            其余为一些标点符号、运算符号等。

 */


public class OthersAlgori {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(twoSum2(new int[]{3,2,4}, 6)));

//        System.out.println(lengthOfLongestSubstringV2("pwwkew"));
//        System.out.println(lengthOfLongestSubstringV2("abcabcbb"));
//        System.out.println(lengthOfLongestSubstringV2("bbbbb"));

//        System.out.println(longestPalindrome("babad"));
//        System.out.println(longestPalindrome("cbbd"));
//        System.out.println(longestPalindrome("ac"));
//        System.out.println(longestPalindrome("bb"));
//        System.out.println(longestPalindrome("bbbb"));
//        System.out.println(longestPalindrome("abb"));

//        System.out.println(longestPalindromeDP("babad"));
//        System.out.println(longestPalindromeDP("cbbd"));
//        System.out.println(longestPalindromeDP("ac"));
//        System.out.println(longestPalindromeDP("bb"));
//        System.out.println(longestPalindromeDP("bbbb"));
//        System.out.println(longestPalindromeDP("abb"));
//        System.out.println(longestPalindromeDP("aacabdkacaa"));

//        ListNode listNode1 = new ListNode(2, new ListNode(4, new ListNode(3)));
//        ListNode listNode2 = new ListNode(5, new ListNode(6, new ListNode(4)));
//
//        ListNode listNode = addTwoNumbers(listNode1, listNode2);
//        System.out.println("Aa");

//        System.out.println(convert("PAYPALISHIRING", 3));
//        System.out.println(convert("AB", 1));
//        System.out.println(threeSumV1(new int[]{-1, 0, 1, 2, -1, -4}));
//        System.out.println(threeSumV1(new int[]{0, 0, 0, 0}));

//        System.out.println(letterCombinations("23"));
        System.out.println(isPalindromeV2(121));
    }

    public static boolean isPalindrome(int x) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(x);
        buffer.reverse();
        return buffer.toString().equals(String.valueOf(x));
    }

    public static boolean isPalindromeV2(int x) {
        String str = String.valueOf(x);
        int leftIndex = 0;
        int rightIndex = str.length() - 1;
        boolean result = false;
        while (!(leftIndex == rightIndex || leftIndex == rightIndex - 1)) {
            if (!str.substring(leftIndex, leftIndex + 1).equals(str.substring(rightIndex, rightIndex + 1)))
                break;
            leftIndex++;
            rightIndex--;
        }

        if (leftIndex == rightIndex)
            result = true;
        else if (leftIndex == rightIndex - 1)
            result = str.substring(leftIndex, leftIndex + 1).equals(str.substring(rightIndex, rightIndex + 1));
        return result;
    }


    public static List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return Collections.emptyList();
        HashMap<Character, String> hashMap = new HashMap<>();
        hashMap.put('2', "abc");
        hashMap.put('3', "def");
        hashMap.put('4', "ghi");
        hashMap.put('5', "jkl");
        hashMap.put('6', "mno");
        hashMap.put('7', "pqrs");
        hashMap.put('8', "tuv");
        hashMap.put('9', "wxyz");

        ArrayList<String> result = new ArrayList<>();

        // 画图理解下，sb 是如何收集combination的
        StringBuffer sb = new StringBuffer();
        backTrace(result, hashMap, digits, 0, sb);
        return result;
    }

    private static void backTrace(List<String> result, Map<Character, String> table, String digits, Integer depthIndex, StringBuffer currentComb) {
        if (depthIndex == digits.length()) {
            result.add(currentComb.toString());
        } else {
            char charAt = digits.charAt(depthIndex);
            char[] chars = table.get(charAt).toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char aChar = chars[i];
                currentComb.append(aChar);
                backTrace(result, table, digits, depthIndex + 1, currentComb);
                currentComb.deleteCharAt(depthIndex);
            }
        }
    }

    public static List<List<Integer>> threeSumV1(int[] nums) {
        if (nums.length < 3) return Collections.emptyList();

        Arrays.sort(nums);
        // 小到大排序，对于index_i < index_j <index_k
        // 和等于 num[index_i] 的元素肯定在 index_j 和 index_k 中取到
        ArrayList<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) continue;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int indexL = i + 1;
            int indexR = nums.length - 1;


            while (indexL < indexR) {
                int sum = nums[i] + nums[indexL] + nums[indexR];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[indexL], nums[indexR]));
                    while (indexL < indexR && nums[indexL] == nums[indexL + 1])
                        indexL += 1;
                    while ((indexL < indexR && nums[indexR] == nums[indexR - 1]))
                        indexR -= 1;
                    indexR -= 1;
                    indexL += 1;
                } else if (sum > 0) {
                    indexR -= 1;
                } else {
                    indexL += 1;
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        // 会有重复问题，
        if (nums.length < 3) return Collections.emptyList();

        Arrays.sort(nums);

        ArrayList<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        return result;
    }

    public static String convert(String s, int numRows) {
        if (s.length() <= numRows) return s;
        if (numRows == 1) return s;


        ArrayList<StringBuffer> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuffer());
        }

        char[] chars = s.toCharArray();
        boolean flag = true;
        int index = 0;

        for (int i = 0; i < chars.length; i++) {

            list.get(index).append(chars[i]);
            index = flag ? index + 1 : index - 1;
            if (index == numRows - 1 || index == 0)
                flag = !flag;
        }

        StringBuffer sb = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            sb.append(list.get(i).toString());
        }

        return sb.toString();
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode cur = res;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;

            int tempSum = l1Val + l2Val;
            int tempSum1 = (tempSum + carry) % 10;
            cur.next = new ListNode(tempSum1);
            cur = cur.next;

            carry = (tempSum + carry) / 10;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        if (carry == 1) {
            cur.next = new ListNode(carry);
            cur = cur.next;
        }

        return res.next;
    }


    public static String longestPalindromeDP(String s) {
        /*
            dp[i][k] 表示 s[i->k] 是回文串

            状态转移方程：
                dp[i][k] = dp[i+1][k-1] && s[i] == s[k]

            初始/边界状态:
                s[i][i] = true
                s[i][i+1] = s[i]==s[j] ? true: false
         */

        int n = s.length();

        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }

        String rest = "";
        for (int j = 1; j < s.length(); j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(j) != s.charAt(i))
                    dp[i][j] = false;
                else {
                    if (j - i < 3)
                        dp[i][j] = true;
                    else
                        // 这里 j 是外层循环，所以在循环到j的时候j-1已经得到了结果值
                        dp[i][j] = dp[i + 1][j - 1];
                }
                if (dp[i][j] && rest.length() < s.substring(i, j + 1).length()) {
                    rest = s.substring(i, j + 1);
                }
            }
        }
        return rest.isEmpty() ? s.substring(0, 1) : rest;
    }

    public static String longestPalindrome(String s) {
        if (s.length() == 1) return s.substring(0, 1);
        if (s.length() == 2) return s.substring(0, 1).equals(s.substring(1, 2)) ? s : s.substring(0, 1);

        String res = "";

        for (int i = 1; i < s.length() - 1; i++) {

            String currentChar = String.valueOf(s.charAt(i));
            String currentNextChar = String.valueOf(s.charAt(i + 1));
            String currentPrevChar = String.valueOf(s.charAt(i - 1));

            if (currentPrevChar.equals(currentNextChar)) {
                // s[mid-1] == s[mid+1]
                String res2 = longestPalindromeInner(s, i - 1, i + 1, currentChar);
                res = res2.length() > res.length() ? res2 : res;
            }
            if (currentChar.equals(currentNextChar)) {
                // s[mid] == s[mid + 1]
                String res2 = longestPalindromeInner(s, i - 1, i + 2, currentChar + currentChar);
                res = res2.length() > res.length() ? res2 : res;
            }
            if (currentChar.equals(currentPrevChar)) {
                // s[mid] == s[mid-1]
                String res2 = longestPalindromeInner(s, i - 2, i + 1, currentChar + currentChar);
                res = res2.length() > res.length() ? res2 : res;
            }
        }
        return res.isEmpty() ? s.substring(0, 1) : res;
    }


    public static String longestPalindromeInner(String s, int left, int right, String res) {
        if (left < 0 || right >= s.length() || left > right) return res;

        if (s.charAt(left) == s.charAt(right)) {
            char c = s.charAt(left);
            res = c + res + c;
            return longestPalindromeInner(s, left - 1, right + 1, res);
        }
        return res;
    }

    private static String longestPalindromeInner(String s, int mid) {
        char midChar = s.charAt(mid);

        return null;
    }

    public static int lengthOfLongestSubstringV2(String s) {
        if (s.isEmpty()) return 0;
        int[] ints = new int[128];

        Arrays.fill(ints, -1);

        int left = 0, res = 0;

        for (int i = 0; i < s.length(); i++) {
            if (ints[s.charAt(i)] != -1) {
                // 当前字符以前已经访问过了， 那么就更新 left
                left = Math.max(left, ints[s.charAt(i)] + 1);
            }
            ints[s.charAt(i)] = i;
            res = Math.max(res, i - left + 1);
        }
        return res;
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) return 0;
        int res = Integer.MIN_VALUE;
        HashSet<Character> set = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            // 从每个元素开始，往后找最大的
            int i1 = lengthOfLongestSubstringInner(s, i, set);
            if (i1 > res) {
                res = i1;
            }
            set.clear();
        }
        return res;

    }

    private static int lengthOfLongestSubstringInner(String s, int index, HashSet<Character> set) {
        set.add(s.charAt(index));
        for (int i = index + 1; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                break;
            }
            set.add(s.charAt(i));
        }
        return set.size();
    }


    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (j > i && nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int resValues = target - nums[i];
            if (map.containsKey(resValues) && map.get(resValues) != i) {
                return new int[]{i, map.get(resValues)};
            }
        }
        return null;
    }


}
