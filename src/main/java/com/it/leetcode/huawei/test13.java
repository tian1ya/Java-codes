package com.it.leetcode.huawei;

import java.util.Scanner;

public class test13 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String res = "";
        for (int i = line.length(); i > 0; i--) {
            res = res + line.substring(i - 1, i);
        }
        System.out.println(res);
    }
}