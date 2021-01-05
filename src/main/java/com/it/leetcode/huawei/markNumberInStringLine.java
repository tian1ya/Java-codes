package com.it.leetcode.huawei;

import java.util.*;

public class markNumberInStringLine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            StringBuffer sb = new StringBuffer();
            Map<Integer, String> kv = new HashMap<>();

            String firstEle = line.substring(0,1);
            if(Character.isDigit(firstEle.charAt(0))) {
                sb.append("*" + firstEle + "*");
            } else {
                sb.append(firstEle);
            }

            for(int i=1;i < line.length();i++) {
                String c = line.substring(i,i+1);
                String prev_c = line.substring(i-1,i);

                if(Character.isDigit(c.charAt(0))) {
                    if(Character.isDigit(prev_c.charAt(0))) {
                        sb.append(c);
                    } else {
                        sb.append("*" + c + "*");
                    }
                }
            }
            System.out.println(sb.toString());
        }
    }
}
