package dateStructure.dsPlay.dsa.algrithem.AboutSort;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

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

    public int[] quickSort(int[] arr) {
        quickSortInner(arr, 0, arr.length - 1);
        return arr;
    }

    private void quickSortInner(int[] arr, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int midIndex = partitionDesc(arr, startIndex, endIndex);
            quickSortInner(arr, midIndex + 1, endIndex);
            quickSortInner(arr, startIndex, midIndex - 1);
        }
    }

    private int partition(int[] arr, int startIndex, int endIndex) {
        int pivotNum = arr[startIndex];
        while (startIndex < endIndex) {
            while (startIndex < endIndex && arr[endIndex] >= pivotNum) {
                // 基准后元素均比基准大
                endIndex--;
            }
            arr[startIndex] = arr[endIndex];

            while (startIndex < endIndex && arr[startIndex] <= pivotNum) {
                // 基准前元素均比基准小
                startIndex++;
            }

            arr[endIndex] = arr[startIndex];
        }

        arr[startIndex] = pivotNum;
        return startIndex;
    }

    private int partitionDesc(int[] arr, int startIndex, int endIndex) {
        int pivotNum = arr[startIndex];
        while (startIndex < endIndex) {
            while (startIndex < endIndex && arr[endIndex] <= pivotNum) {
                // 基准后元素均比基准大
                endIndex--;
            }
            arr[startIndex] = arr[endIndex];

            while (startIndex < endIndex && arr[startIndex] >= pivotNum) {
                // 基准前元素均比基准小
                startIndex++;
            }

            arr[endIndex] = arr[startIndex];
        }

        arr[startIndex] = pivotNum;
        return startIndex;
    }

    public void toString(int[] nums) {
        System.out.println(Arrays.toString(nums));
    }


    public int[] arraySort(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }

    public int prioritySort(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int num : nums) {
            priorityQueue.add(num);
            if (priorityQueue.size() > k)
                // 这里不会将最终答案poll 出去，因为这里的判断条件，priorityQueue 中的元素已经是多余 k 个了。
                priorityQueue.poll();
        }
        return priorityQueue.peek();
    }

    public int findKthLargest(int[] nums, int k) {
        if (k < 1) return nums[0];

        quickSortInner(nums,0, nums.length-1);

        toString(nums);
        return nums[k - 1];
    }


    public static void main(String[] args) {
        KthLargestNumber number = new KthLargestNumber();
        System.out.println(Arrays.toString(number.bubbleSort(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6})));
        System.out.println(number.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
//        System.out.println(number.prioritySort(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));

        System.out.println(Arrays.toString(number.quickSort(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6})));
    }
}
