package dateStructure.chapt08;

import dateStructure.chapt02.Assert;
import dateStructure.chapt05.Sorter;

import static dateStructure.chapt08.common.swap;

public class Bubble implements Sorter {
    public static void sort(int[] arr) {
        if (arr == null || arr.length == 0)
            return;

        for (int i = 0; i < arr.length; i++) {
            for (int j=i+1; j < arr.length;j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    public static void twoWaySort(int[] arr) {
        if (arr == null || arr.length == 0)
            return;

        int left = arr.length-1;
        int right = 0;
        while (left < right) {
            leftSort(arr, right);
            right++;
            if (left >= right)
                break;
            rightSort(arr, left);
            left--;
        }
    }

    private static void rightSort(int[] arr, int left) {
        for (int j = left-1; j > 0; j--) {
            if (arr[j] > arr[left])
                swap(arr, j, left);
        }
    }

    private static void leftSort(int[] arr, int right) {
        for (int j = right+1; j < arr.length; j++) {
            if (arr[j] > arr[right])
                swap(arr, j, right);
        }
    }


}
