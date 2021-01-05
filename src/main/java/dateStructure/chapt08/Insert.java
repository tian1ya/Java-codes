package dateStructure.chapt08;

/*
    复杂度 O(n^2)
 */
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
                arr[j+1] = arr[j];
            else
                break;
        }

        arr[j+1] = a;
    }
}
