package com.it.leetcode.huawei;

import java.util.Scanner;

public class DengChaShuLie {
    /*
        等差数列通项式
            a(n) = a(1) + (n-1)d

        求和公式:
            s(n) = na(1) + d*n(n-1)/2
     */
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()){
//            int n = scanner.nextInt();
//            System.out.println(sum(n,3,2));
//        }

        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            System.out.println((1 + 3 * n) * n / 2);
        }
    }

    public static int sum(int n, int d, int a1) {
        return n * a1 + d * n*(n-1)/2;
    }
}
