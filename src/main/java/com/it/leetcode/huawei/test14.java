package com.it.leetcode.huawei;

import java.util.Scanner;

public class test14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int negCounter = 0;
        double posSvg = 0.0;
        int posCounter = 0;

        while(scanner.hasNextInt()) {
            int val = scanner.nextInt();
            if (val < 0) {
                negCounter += 1;
            }else {
                posCounter += 1;
                posSvg += val;
            }
        }
        System.out.println(negCounter);
        System.out.println(String.format("%.1f", posSvg / posCounter));
    }
}
