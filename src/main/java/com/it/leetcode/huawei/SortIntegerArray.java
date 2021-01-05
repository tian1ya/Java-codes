package com.it.leetcode.huawei;

import java.util.Scanner;

public class SortIntegerArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int cnt = scanner.nextInt();
            int[] arr = new int[cnt];
            for (int i = 0; i < cnt; i++) {
                arr[i] = scanner.nextInt();
            }
            int sortFlag = scanner.nextInt();

            printSortedArr(arr, sortFlag);
        }
    }

    public static void printSortedArr(int[] arr, int sortFlag) {

        insertSort(arr);

        StringBuilder sb = new StringBuilder();
        if (sortFlag == 1){
            for(int i=0;i<arr.length;i++){
                sb.append(arr[i]);
                sb.append(' ');
            }
        }else {
            for(int i=arr.length-1;i>=0;i--){
                sb.append(arr[i]);
                sb.append(' ');
            }
        }
        System.out.println(sb.toString());
    }

    public static int[] insertSort(int[] arr) {
        for(int i=1;i<arr.length;i++) {
            int insertValue = arr[i];
            int insertIndex = i - 1;


            while(insertIndex >= 0 && insertValue > arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex -= 1;
            }
            arr[insertIndex + 1] = insertValue;
        }
        return arr;
    }
}
