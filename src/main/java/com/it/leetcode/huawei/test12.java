package com.it.leetcode.huawei;

import java.util.Scanner;

public class test12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double input = scanner.nextDouble();
        System.out.println(getCubeRoot(input, 0.0));
    }

    private static double getCubeRoot(double input, double initial) {

        while (Math.abs(Math.pow(initial, 3) - input) > 0.001) {
            double g = Math.pow(initial, 3) - input;
            double g2 = Math.pow(initial, 2) * 3;
            initial = initial - 0.01 * g / (g2 + 0.01);
        }
        return initial;
    }
}
