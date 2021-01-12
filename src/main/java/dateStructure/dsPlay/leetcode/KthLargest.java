package dateStructure.dsPlay.leetcode;

import java.util.Arrays;

public class KthLargest {
    private int[] nums;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.nums = nums;
        this.k = k;

    }

    public int add(int val) {

        this.nums = Arrays.copyOf(this.nums, nums.length + 1);
        this.nums[nums.length-1] = val;
        Arrays.sort(this.nums);
        int num = this.nums[this.nums.length - this.k];
        return num;
    }

    public static void main(String[] args) {
        KthLargest largest = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(largest.add(3));
        System.out.println(largest.add(5));
        System.out.println(largest.add(10));
        System.out.println(largest.add(9));
        System.out.println(largest.add(4));
    }

}
