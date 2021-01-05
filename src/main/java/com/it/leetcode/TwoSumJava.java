package com.it.leetcode;

import sun.rmi.runtime.RuntimeUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TwoSumJava {

    public static int[] indexs(int[] nums, int taregt) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(taregt - nums[i]))
                return new int[]{i, map.get(taregt - nums[i])};
            map.put(nums[i], i);
        }
        return new int[]{-1,-1};
    }


    public static void main(String[] args) {
        int[] indexs = indexs(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 9);
        System.out.println(indexs[0]);
        System.out.println(indexs[1]);
    }
}
