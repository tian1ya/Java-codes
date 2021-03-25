package com.it.leetcode.huawei;

import java.util.Scanner;

public class DPLogestIncrementSeq {
    /*
    https://blog.csdn.net/u013309870/article/details/62037674
        题目：
            给定数组 arr， 返回数组中的最长递增子序列
            如：[10,22,9,33,21,50,41,60,80] => [10,22,33,41,60,80]

       思路：
           1. 定义长苏为 n 的 dp 数组，dp[i] 代表 arr[i] 结尾的最长递增子序列的长度
           2. 对于第一个数 arr[0] 来说 dp[0]=1，依次求出以 i 结尾的最长递增子序列
           3. 对于 dp[i] 求 arr[i] 结尾的最长递增子序列，在 arr[0...i-1] 中选出比 arr[i] 小且长度最长的
              dp[j], dp[i] = max(dp[j]+1, dp[i])


          10 22 9 33 21 50 41 60 80
           1  2 1  3  2  4  4  5  6
     */

    // 答案在这里
//    public static void thread.book.main(String[] args) {
//        int[] arr = new int[]{10, 22, 9, 33, 21, 50, 41, 60, 80};
//        int[] x = longestSubStringLen(arr);
//        System.out.println(x);
//
//        generateLIS(arr, x);
//    }
//
//    public static int[] longestSubStringLen(int arr[]) {
//        int len = 0;
//        System.out.println("input arr: ");
//        for (int i : arr) {
//            System.out.print(i + " ");
//        }
//
//        int dp[] = new int[arr.length];
//        dp[0] = 1;
//
//        for (int i = 0; i < arr.length; i++) {
//            dp[i] = 1;
//            for (int j = 0; j < i; j++) {
//                if (arr[j] < arr[i]) {
//                    dp[i] = Math.max(dp[i], dp[j] + 1);
//                }
//            }
//        }
//
//
//        System.out.println("dp:");
//        for (int i : dp) {
//            System.out.print(i + " ");
//            len = Math.max(i, len);
//        }
//        return dp;
//    }
//
//
//    public static void generateLIS(int arr[], int dp[]) {
//        int k = 0;
//        int index = 0;
//        int len = 0;
//        for (int i = 0; i < arr.length; i++) {
//            if (dp[i] > len) {
//                len = dp[i];
//                index = i;
//                System.out.println("index: " + index);
//                //找到递增子序列中的最后一个元素[10,22,33,41,60,80]中的80，
//            }
//        }
//        int subArr[] = new int[len];
//        subArr[k++] = arr[index];
//        System.out.println("arr[index] " + arr[index]);
//
//        for (int j = index - 1; j >= 0; j--) {
//            if ((dp[index] == dp[j] + 1) && (arr[index] > arr[j])) {
//                //从后向前,将属于递增子序列的元素加入到subArr中。
//                subArr[k++] = arr[j];
//                index = j;
//            }
//        }
//        for (int j = subArr.length - 1; j >= 0; j--) {
//            System.out.print(subArr[j] + " ");
//        }
//    }

    //自己联系

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        while (scanner.hasNextInt()) {
//            int count = scanner.nextInt();
//            int[] seq = new int[count];
//            for (int i = 0; i < count; i++) {
//                seq[i] = scanner.nextInt();
//            }
//
////            int[] seq = new int[]{10, 22, 9, 33, 21, 50, 41, 60, 80};
//            // result => 1,2,1,3,2,4,4,5,6
//            int[] result = findSortIndex(seq);
//
//            int res = 0;
//            for (int i = 0; i < result.length; i++) {
//                if (result[i] > res)
//                    res = result[i];
//            }
//            System.out.println(res);
//
//            findLIS(seq, result);
////        }
//        }
//    }
//
//    public static int[] findSortIndex(int[] seq) {
//        int length = seq.length;
//        int[] stepsSeq = new int[length];
//        for (int i = 0; i < length; i++) {
//            stepsSeq[i] = 1;
//            for (int j = 0; j < i; j++) {
//                if (seq[j] < seq[i]) {
////                    dp[i]={max(dp[j])+1,j<i且a[j]<a[i]},
//                    stepsSeq[i] = Math.max(stepsSeq[i], stepsSeq[j] + 1);
//                }
//            }
//        }
//        return stepsSeq;
//    }
//
//    public static void findLIS(int[] seq, int[] dp) {
//
//        int biggestValue = 0;
//
//        for (int i : dp) {
//            if (i > biggestValue)
//                biggestValue = i;
//        }
//
//        int[] result = new int[biggestValue];
//        int index = 0;
//        for (int i = biggestValue; i > 0; i--) {
//            int indexOfSeq = indexOf(dp, i);
//            result[index] = seq[indexOfSeq];
//            index += 1;
//        }
//
//        for (int i = result.length; i > 0; i--) {
//            System.out.print(result[i - 1] + " ");
//        }
//    }
//
//    public static int indexOf(int[] seq, int value) {
//        int index = 0;
//        for (int i = 0; i < seq.length; i++) {
//            if (seq[i] == value)
//                break;
//            index += 1;
//        }
//        return index;
//    }


    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        while(sc.hasNextLine()){
//            int n = Integer.parseInt(sc.nextLine());
//            String str = sc.nextLine();
//            System.out.println(removeNo(str,n));
//        }
        int i = removeNo(9);
        System.out.println(i);
    }

    public static int removeNo(int n){
        String[] stra =  new String[]{"10", "22", "9", "33", "21", "50", "41", "60", "80"};
        int[] height = new int[n];//分别为正序和逆序的整型数组
        int[] heightopp = new int[n];
        for(int i=0;i<n;i++){
            height[i] = Integer.parseInt(stra[i]);
        }
        for(int i=0;i<n;i++){
            heightopp[i] = height[n-i-1];
        }
        int[] dp = Maxsub(height,n);//递增计数
        int[] dpopp = Maxsub(heightopp,n);//递减计数（逆序）
        int[] sum = new int[n];
        for(int i=0;i<n;i++){
            sum[i] = dp[i]+dpopp[n-i-1];//相加时要将递减计数倒过来
        }
        int max = 0;//求递增计数与递减计数之和的最大值
        for(int i=0;i<n;i++){
            max = sum[i]>max?sum[i]:max;
        }
        return n-(max-1);//返回出列人数
    }

    public static int[] Maxsub(int[] stra,int n){//按公式dp[i]={max(dp[j])+1,j<i且a[j]<a[i]}求dp[i]
        int[] dp = new int[n];
        for (int i=0;i<n;i++){
            dp[i] = 1;
            for (int j=0;j<i;j++) {
                if (stra[j]<stra[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        return dp;
    }
}
