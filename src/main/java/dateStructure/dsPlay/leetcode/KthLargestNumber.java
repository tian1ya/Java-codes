package dateStructure.dsPlay.leetcode;

import java.util.Arrays;

public class KthLargestNumber {


    public int[] bubbleSort(int[] nums) {
        if (nums == null) return null;
        if (nums.length == 1) return nums;

        int length = nums.length;

        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if (nums[i] < nums[j]) {
                    // 降序排列
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
            }
        }
        return nums;
    }

    public int[] insertSort(int[] nums) {
        if (nums == null) return null;
        if (nums.length == 1) return nums;

        for (int i = 1; i < nums.length; i++) {
            subInsertSort(nums, i);
        }

        return nums;
    }

    public int[] subInsertSort(int[] nums, int index) {
        int val = nums[index];
        int i;
        for (i = index - 1; i >= 0; i--) {
            if (nums[i] < val) {
                nums[i + 1] = nums[i];
            } else break;
        }
        nums[i + 1] = val;
        return nums;
    }

    public void toString(int[] nums) {
        System.out.println(Arrays.toString(nums));
    }



    public int[] arraySort(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }

//    public int[] mergeSort(int[] nums) {
//        if (nums == null) return null;
//        if (nums.length == 1) return nums;
//
//    }


    public int findKthLargest(int[] nums, int k) {
        if (k < 1) return nums[0];

        int[] ints = insertSort(nums);

        toString(nums);

        return ints[k - 1];

//        int[] ints = arraySort(nums);
//        return ints[nums.length - k];
    }


    public static void main(String[] args) {
        KthLargestNumber number = new KthLargestNumber();
        System.out.println(number.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }
}
