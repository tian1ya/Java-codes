package dateStructure.dsPlay.dsa.algrithem.AboutSort;

public class QuickSort {

    /*
    二叉树遍历框架
    void traverse(TreeNode root) {
        // 前序遍历
        traverse(root.left)
        // 中序遍历
        traverse(root.right)
        // 后序遍历
    }

      先构造分界点，然后去左右子数组构造分界点，你看这不就是一个二叉树的前序遍历吗
     */
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

    /*
        先对左右子数组排序，然后合并（类似合并有序链表的逻辑），你看这是不是二叉树的后序遍历框架
     */
    void sort(int[] nums, int lo, int hi) {
        int mid = (lo + hi) / 2;
        sort(nums, lo, mid);
        sort(nums, mid + 1, hi);

        /****** 后序遍历位置 ******/
        // 合并两个排好序的子数组
        merge(nums, lo, mid, hi);
        /************************/
    }

    private void merge(int[] nums, int lo, int mid, int hi) {

    }
}
