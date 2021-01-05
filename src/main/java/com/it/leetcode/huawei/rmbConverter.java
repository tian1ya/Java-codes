package com.it.leetcode.huawei;



import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/*
    -Xmx10m  : 设置堆内存最大值
    +PrintStringTableStatistics: 大于字符串表统计信息(串池中字符串数量)
    -xx:+PrintGCDetails -verbose:gc: （打印垃圾回收信息）

    设置： -Xmx20m -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -verbose:gc

 */
public class rmbConverter {

//    private static final int _512KB = 512 * 1024;
//    private static final int _1MB = 1024 * 1024;
//    private static final int _6MB = 1024 * 1024 * 6;
//    private static final int _7MB = 1024 * 1024 * 7;
//    private static final int _8MB = 1024 * 1024 * 8;
//
//    public static void main(String[] args) throws IOException {
//        ArrayList<byte[]> list = new ArrayList<>();
//        list.add(new byte[_8MB]);
//        list.add(new byte[_8MB]);
//    }


    private int stackLength = 1;
    public void stackLeak() {
        stackLength += 1;
        stackLeak();
    }

    public void test_stack_over_flow(){
        /*
            虚拟机配置： -Xss160k
         */

        try {

            stackLeak();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println("stack deepth: " + stackLength);
        }
    }

    public void testIntern() {
        String s = new StringBuffer("java").append("scala").toString();
        System.out.println(s.intern() == s);

        String s1 = new StringBuffer("ja").append("vaaaaa").toString();
        System.out.println(s1.intern() == s1);
    }

    public static void main(String[] args) {
        rmbConverter rmbConverter = new rmbConverter();
//        rmbConverter.test_stack_over_flow();

        rmbConverter.testIntern();
    }
}
