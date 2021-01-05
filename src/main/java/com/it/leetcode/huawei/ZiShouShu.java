package com.it.leetcode.huawei;

import java.util.Scanner;

public class ZiShouShu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            StringBuffer sb = new StringBuffer();
            int i1 = scanner.nextInt();
            int cnt = 0;
            for (int i = 0; i < i1; i++) {
                int pow = i * i;
                boolean ends = String.valueOf(pow).endsWith(String.valueOf(i));
                if (ends) {
                    sb.append(i + "\n");
                    cnt +=1;
                }
            }
            System.out.println(sb.toString());
            System.out.println(cnt);
        }
    }
}
