package dateStructure.chapt08;

import static dateStructure.chapt08.common.swap;

/*
    选择排序是一种简单直观的排序算法，无论什么数据进去都是 O(n²) 的时间复杂度。
    所以用到它的时候，数据规模越小越好。唯一的好处可能就是不占用额外的内存空间了吧。

    一次选出一个最大值，然后将最大值和此次的比较直做交换
 */
public class Select {

    public static void sort(int[] arr) {
        if (arr == null || arr.length == 0)
            return;

        for (int i = 0; i < arr.length; i++) {
            //从第一个元素开始，每次知道满足条件的那个元素和第一个元素交换
            maxMoveRight(arr, i);
        }
    }

    private static void maxMoveRight(int[] arr, int index) {
        int max = arr[index];
        int maxIndex = index;
        for (int i = index + 1; i < arr.length; i++) {
            // 每次找出最小值
            if (arr[i] < max) {
                max = arr[i];
                maxIndex = i;
            }
        }
        if (maxIndex != index) {
            swap(arr, index, maxIndex);
        }
    }

}
