package dateStructure.chapt08;


public class Insert {
    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            insertSort(arr, i);
        }
    }

    private static void insertSort(int[] arr, int i) {
        int a = arr[i];
        int j;
        for (j = i-1; j >= 0; j--) {
            if (a < arr[j])
                // 第一次覆盖了的值存储在 a 中
                arr[j+1] = arr[j]; // 小的值往后移动
            else // 因为是降序的，所以当遇到小于，那么肯定是小于全面的所有值，那么本轮的值，在当前位置就是最小的的。继续找下个次小的
                break;
        }

        arr[j+1] = a;
    }
}
