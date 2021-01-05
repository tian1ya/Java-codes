package com.it.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class singleNumber {

    public static void main(String[] args) {
        Integer re = findSingleNumber3(new Integer[]{1, 1, 2, 3, 5, 2, 3});
        System.out.println(re);

    }

    public static Integer findSingleNumber(Integer[] lists) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer value : lists) {
            Integer v = map.get(value);
            map.put(value, v == null ? 1 : v + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue()==1)
                return entry.getKey();
        }
        return -1;
    }

    public static Integer findSingleNumber2(Integer[] lists) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (Integer value : lists) {
            if (!hashSet.add(value))
                hashSet.remove(value);
        }
        // 第一个元素
        return hashSet.iterator().next();
    }

    public static Integer findSingleNumber3(Integer[] nums) {
        int ans = nums[0];
        if (nums.length > 1) {
            for (int i = 1; i < nums.length; i++) {
                ans = ans ^ nums[i];
            }
        }
        return ans;
    }
}
