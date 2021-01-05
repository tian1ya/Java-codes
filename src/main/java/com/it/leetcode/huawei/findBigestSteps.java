package com.it.leetcode.huawei;

import java.util.*;

public class findBigestSteps {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> arr = new ArrayList<>();
        while(scanner.hasNextInt()){
            arr.add(scanner.nextInt());
        }

        int result = 0;
        for(int i=0;i<arr.size();i++) {
            int res = findSteps(arr.subList(i+1, arr.size()), arr.get(i), 1);
            if(res > result)
                result = res;
        }
        System.out.println(result);
    }

    public static int findSteps(List<Integer> arr, int firstSteps, int steps){
        if (!arr.isEmpty()){
            boolean isBigger = arr.get(0) > firstSteps;
            List<Integer> arr1 = arr.subList(1, arr.size());
            return findSteps(arr1, isBigger?arr.get(0):firstSteps, isBigger ? 1 + steps : steps);
        } else {
            return steps;
        }
    }

    public static int findIndexOfBiggestOneButSmallThanGivenValue(List<Integer> arr, int givenValue) {
        Integer maxValue = arr.stream().filter(ele -> ele > givenValue).max(Integer::compareTo).get();
        return arr.indexOf(maxValue);
    }
}
