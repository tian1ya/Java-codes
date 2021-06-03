package dateStructure.dsPlay.dsa.algrithem.AboutSort;

import java.util.Arrays;

public class SortColors {
    public static void sortColors(int[] nums) {
//        Arrays.sort(nums);
//        quickSort(nums);
        int ptrIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = nums[ptrIndex];
                nums[ptrIndex++] = 0;
            }
        }

        for (int i = ptrIndex; i < nums.length; i++) {
            if (nums[i] ==1) {
                nums[i] = nums[ptrIndex];
                nums[ptrIndex++] = 1;
            }
        }
    }

    private static void quickSort(int[] nums) {
        quickSortInner(nums, 0, nums.length-1);
    }

    private static void quickSortInner(int[] nums, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int midIndex = partition(nums, startIndex, endIndex);
            quickSortInner(nums, startIndex, midIndex-1);
            quickSortInner(nums,  midIndex+1, endIndex);
        }
    }

    private static int partition(int[] nums, int startIndex, int endIndex) {
        int pivot = nums[startIndex];
        while (startIndex < endIndex) {
            while (startIndex < endIndex && nums[endIndex] >= pivot)
                endIndex--;

            nums[startIndex] = nums[endIndex];

            while (startIndex < endIndex && nums[startIndex] <= pivot)
                startIndex++;

            nums[endIndex] = nums[startIndex];
        }
        nums[startIndex] = pivot;
        return startIndex;
    }



    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors(nums);
        System.out.println("");
    }
}
