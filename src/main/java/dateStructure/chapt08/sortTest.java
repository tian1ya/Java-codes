package dateStructure.chapt08;

import dateStructure.chapt02.Assert;

import static dateStructure.chapt08.Bubble.twoWaySort;


public class sortTest {
    public static void main(String[] args) {
    // * 起泡
        bubble();

    // * 选择
        select();

    // * 插入
        insert();

    // * 堆排序

    // 归并(merge)排序

    // 快速排序
    }

    private static void bubble() {
        int[] arr = {1,3,4,6,8,43,3};;
        Bubble.sort(arr);
        Assert.assertEqual(arr[0], 1);
        Assert.assertEqual(arr[arr.length-1], 43);
    }

    private static void select() {
        int[] arr = {1,3,4,6,8,43,3};;
        Select.sort(arr);
        Assert.assertEqual(arr[0], 1);
        Assert.assertEqual(arr[arr.length-1], 43);
    }

    private static void insert() {
        int[] arr = {1,3,4,6,8,43,3};;
        Insert.sort(arr);
        Assert.assertEqual(arr[0], 1);
        Assert.assertEqual(arr[arr.length-1], 43);
    }
}
