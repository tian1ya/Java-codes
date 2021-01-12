package dateStructure.chapt08;

import java.util.Arrays;

public class MergeSort {
    public static void sort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    public static void mergeSort(int[] nums, int startIndex, int endIndex) {
        int mid = (startIndex + endIndex) / 2;
        if (startIndex < endIndex) {

            // 右边排序
            mergeSort(nums, startIndex, mid);

            // 左边排序
            mergeSort(nums, mid + 1, endIndex);

            // 左右排好序的2组，在去 排一个组，组成是最终的结果
            merge(nums, startIndex, mid, endIndex);
        }
    }

    private static void merge(int[] nums, int lowIndex, int mid, int highIndex) {
        int[] newNums = new int[highIndex - lowIndex + 1];

        int tempLowIndex = lowIndex;
        int tempHighIndex = mid + 1;
        int k = 0;

        // 头部开始2个数组开始排序
        while (tempLowIndex <= mid && tempHighIndex <= highIndex) {
            if (nums[tempLowIndex] < nums[tempHighIndex]) {
                // 这类符号一换就可以完成正，反的排序
                newNums[k++] = nums[tempLowIndex++];
            } else {
                newNums[k++] = nums[tempHighIndex++];
            }
        }
        // 这个时候左边或者右边有一个可能已经是空的数组了(其实没有空，只是元素已经被复制了)

        // 右边可能会剩余元素
        while (tempLowIndex <= mid) {
            newNums[k++] = nums[tempLowIndex++];
        }


        // 左边可能会剩余元素
        while (tempHighIndex <= highIndex) {
            newNums[k++] = nums[tempHighIndex++];
        }

        // 排序好的结果在复制回 nums
        for (int i = 0; i < newNums.length; i++) {
            nums[i + lowIndex] = newNums[i];
        }
    }


    public static void main(String[] args) {
        int a[] = {51, 46, 20, 18, 65, 97, 82, 30, 77, 50};
        sort(a);
        System.out.println("排序结果：" + Arrays.toString(a));
    }
}
