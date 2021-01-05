package com.it.leetcode.huawei;

import java.util.Scanner;

public class test11 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int val1 = scanner.nextInt();
        int val2 = scanner.nextInt();

        System.out.println(String.format("输入：%d", val1));
        System.out.println(String.format("输入：%d", val2));

        int greatestCommonDivisor = findGreatestCommonDivisor(val1, val2);
        System.out.println(String.format("最小公约数是：%d", greatestCommonDivisor));
    }

    public static int findGreatestCommonDivisor(int val1, int val2) {
        /*
            1. 找到最小公倍数
         */

        int leastCommonMultiple = findLeastCommonMultiple(val1, val2);

        /*
            2. 最大公约数 = (val1 * val2) / 最小公倍数
         */
        return (val1 * val2) / leastCommonMultiple;
    }

    private static int findLeastCommonMultiple(int val1, int val2) {
        int diff = val1 - val2;
        if (diff == 0) {
            return val1;
        } else if (diff > 0) {
            return findLeastCommonMultiple(diff, val2);
        } else {
            return findLeastCommonMultiple(diff * -1, val1);
        }
    }
}
