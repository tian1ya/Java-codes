package com.it.leetcode.dsPlay.dsa;

import dateStructure.dsPlay.dsa.list.LinkedList;
import dateStructure.dsPlay.dsa.stack.ListStack;
import org.junit.Assert;
import org.junit.Test;

public class ListStackTest {

    @Test
    public void should_insert_list() {
        ListStack<Student> list = new ListStack<>();
        Student s1 = new Student("1", 1);
        list.push(s1);
        Student s0 = new Student("0", 0);
        list.push(s0);
        Student s2 = new Student("2", 2);
        list.push(s2);

        Assert.assertEquals(list.getSize(), 3);

        System.out.println(list);
    }
}
