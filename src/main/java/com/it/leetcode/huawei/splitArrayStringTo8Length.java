package com.it.leetcode.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class splitArrayStringTo8Length {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> res = new ArrayList<>();

        while (scanner.hasNext()) {
            Integer times = Integer.valueOf(scanner.nextLine());
            for (int i = 0; i < times; i++) {
                String line = scanner.nextLine();
                printSplitStringTo8Length(line, res);
            }
        }


        for (String re : res) {
            System.out.println(re);
        }
    }

    private static List<String> printSplitStringTo8Length(String line, List<String> res) {
        if (line == null || line.isEmpty())
            return res;
        if (line.length() > 8) {
            res.add(line.substring(0, 8));
            return printSplitStringTo8Length(line.substring(8), res);
        } else {
            int paddings = 8 - line.length();
            res.add(append(line, "0", paddings));
            return res;
        }
    }


    private static String append(String line, String padValue, int howManyPads) {
        for (int i = 0; i < howManyPads; i++) {
            line += padValue;
        }
        return line;
    }

}

/*
    import java.util.HashMap;
import java.util.Scanner;
import java.util.LinkedList;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = Integer.valueOf(sc.nextLine());
            for (int i = 0; i <n; i++){
                String str = sc.nextLine();
                helper(str);
            }
        }
    }
    private static void helper(String s){
        if (s == null) return;
        int a = 0;
        if (s.length() % 8 != 0)
            a = 8 - s.length()%8;
        while(a>0) {
            s += "0";
            a--;
        }

        for (int i = 0; i < s.length(); i+=8){
            System.out.println(s.substring(i, i+8));
        }
    }
}
 */
