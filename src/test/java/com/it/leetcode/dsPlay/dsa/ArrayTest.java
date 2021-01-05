package com.it.leetcode.dsPlay.dsa;

import dateStructure.dsPlay.dsa.Array;
import org.junit.Test;

import java.util.ArrayList;

public class ArrayTest {
    @Test
    public void should_to_string() {

        Array<Integer> array = new Array<>(20);
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }

        array.add(1, 100);

//        System.out.println(array.toString());

        int getOut = array.get(2);

        array.addFirst(11);
        array.addLast(99);

        System.out.println(array);
        int remove = array.remove(2);
        array.removeElement(0);
        array.removeFirst();
        array.removeLast();
        System.out.println(array);


        Array<Double> array1 = new Array<>(20);
        array1.set(0,0.0);
        array1.set(1,1.0);
        System.out.println(array1);

        Array<Student> array3 = new Array<>(2);
        array3.addFirst(new Student("1",1));
        System.out.println("size: " + array3.getSize());
        array3.addFirst(new Student("2",2));
        array3.addFirst(new Student("3",3));
        System.out.println("size: " + array3.getSize());
        System.out.println(array3);

        array3.removeLast();
        array3.removeLast();
        System.out.println("size: " + array3.getSize());
        System.out.println(array3);

    }
}
