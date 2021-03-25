package com.it.leetcode.huaweiM;

public class Test {
    public static void main(String... args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例


//            String line = in.nextLine();
        String line = "abcd123.4567.890.123";

        char[] chars = line.toCharArray();
        int result = 0;
        int max = 0;
        boolean dotFlag = false;
        for (char aChar : chars) {

            if (aChar == '.' ) {
                if (dotFlag) {
                    result = Math.max(result, max);
                    max = 0;
                }
                dotFlag = true;
                continue;
            }
            else if (aChar >= '0' && aChar <= '9') {
                max += 1;
            } else {
                result = Math.max(result, max);
                max = 0;
            }
        }
        System.out.println(result);
    }
}

//    public static void main(String ... args) {
//        Scanner in = new Scanner(System.in);
//        int maxLength = 0;
//            int n = in.nextInt();
//            String[] strings = new String[n];
//            if (n == 1) {
//                if (strings[0].contains("1")) {
//                    System.out.println(1);
//                    return;
//                } else {
//                    System.out.println(0);
//                    return;
//                }
//            } else if (strings[0].length()==1) {
//                for (int i = 0; i < n; i++) {
//                    if (strings[i].contains("1")) {
//                        System.out.println(1);
//                        break;
//                    }
//                }
//                System.out.println(0);
//            } else {
//                for (int i = 0; i < n; i++) {
//                    for (int j = 0; j < strings[0].length(); j++) {
//                        if (strings[i].charAt(j) == '1') {
//                            int maxStrLenth = Math.max(n-1-i, strings[0].length()-1-j);
//                            for (int k = 1; k < maxStrLenth; k++) {
//                                if (strings[k+i].charAt(j+k) =='1') {
//                                    int flag = 1;
//                                    for (int l = 0; l <=k; l++) {
//                                        if (strings[i+1].substring(j, j+k+1).contains("0")) {
//                                            flag = 0;
//                                            break;
//                                        }
//                                    }
//                                    if (flag==1) {
//                                        maxLength = Math.max(maxLength, k+1);
//                                    }
//                                } else {
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                }
//                System.out.println(maxLength * maxLength);
//            }
//        }
//}


/*
    // 本题为考试多行输入输出规范示例，无需提交，不计分。
    import java.util.Scanner;

    public class Main {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int ans = 0, x;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    x = sc.nextInt();
                    ans += x;
                }
            }
            System.out.println(ans);
        }
    }
 */