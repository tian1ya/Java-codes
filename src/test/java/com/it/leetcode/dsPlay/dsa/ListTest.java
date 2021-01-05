package com.it.leetcode.dsPlay.dsa;

import dateStructure.dsPlay.dsa.list.LinkedList;
import org.junit.Assert;
import org.junit.Test;

public class ListTest {

    @Test
    public void should_insert_list() {
        LinkedList<Student> list = new LinkedList<>();
        Student s1 = new Student("1", 1);
        list.addFirst(s1);
        Student s0 = new Student("0", 0);
        list.add(0, s0);
        Student s2 = new Student("2", 2);
        list.addLast(s2);

        Assert.assertEquals(list.getSize(), 3);

        Assert.assertEquals(s2, list.get(2));

        s2.setAge(22);
        Assert.assertEquals(Long.parseLong(s2.getAge().toString()), 22L);

        System.out.println(list);

//        list.delete(2);
//        System.out.println(list);

        list.remove(0);
        System.out.println(list);
    }
}
