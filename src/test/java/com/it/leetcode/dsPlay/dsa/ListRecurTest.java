package com.it.leetcode.dsPlay.dsa;

import dateStructure.dsPlay.dsa.list.LinkedListRecur;
import org.junit.Assert;
import org.junit.Test;

public class ListRecurTest {

    @Test
    public void should_insert_list() {
        LinkedListRecur<Student> list = new LinkedListRecur<>();
        Student s1 = new Student("1", 1);
        Student s2 = new Student("2", 2);
        Student s3 = new Student("3", 3);
        list.add(0, s1);
        list.add(1, s2);
        list.add(2, s3);

        Assert.assertEquals(list.toString(), "Student{name='1', age=1} => Student{name='2', age=2} => Student{name='3', age=3} => NULL");
    }

    @Test
    public void shouold_get_one() {
        LinkedListRecur<Student> list = new LinkedListRecur<>();
        Student s1 = new Student("1", 1);
        Student s2 = new Student("2", 2);
        Student s3 = new Student("3", 3);
        list.add(0, s1);
        list.add(1, s2);
        list.add(2, s3);

        Student student = list.get(2);
        Assert.assertEquals(student.getAge().toString(), String.valueOf(3));
        Assert.assertEquals(student.getName(), "3");
    }

    @Test
    public void should_set_value() {
        LinkedListRecur<Student> list = new LinkedListRecur<>();
        Student s1 = new Student("1", 1);
        Student s2 = new Student("2", 2);
        Student s3 = new Student("3", 3);
        list.add(0, s1);
        list.add(1, s2);
        list.add(2, s3);

        list.set(2,new Student("33", 33));
        Assert.assertEquals(list.get(2).getAge().toString(), String.valueOf(33));
        Assert.assertEquals(list.get(2).getName(), String.valueOf("33"));
    }

    @Test
    public void should_contains_value() {
        LinkedListRecur<Student> list = new LinkedListRecur<>();
        Student s1 = new Student("1", 1);
        Student s2 = new Student("2", 2);
        Student s3 = new Student("3", 3);
        list.add(0, s1);
        list.add(1, s2);
        list.add(2, s3);

        Assert.assertEquals(list.contains(s2), true);
        Assert.assertEquals(list.contains(s1), true);
        Assert.assertEquals(list.contains(s3), true);
    }

    @Test
    public void should_remove_value() {
        LinkedListRecur<Student> list = new LinkedListRecur<>();
        Student s1 = new Student("1", 1);
        Student s2 = new Student("2", 2);
        Student s3 = new Student("3", 3);
        list.add(0, s1);
        list.add(1, s2);
        list.add(2, s3);

        Assert.assertEquals(list.toString(),"Student{name='1', age=1} => Student{name='2', age=2} => Student{name='3', age=3} => NULL");
        list.removeElement(s2);

        Assert.assertEquals(list.toString(), "Student{name='1', age=1} => Student{name='3', age=3} => NULL");
    }

    @Test
    public void should_remove_value_2() {
        LinkedListRecur<Student> list = new LinkedListRecur<>();
        Student s1 = new Student("1", 1);
        Student s2 = new Student("2", 2);
        Student s3 = new Student("3", 3);
        Student s4 = new Student("4", 4);
        Student s5 = new Student("5", 5);
        Student s6 = new Student("6", 6);
        Student s7 = new Student("7", 7);

        list.add(0, s1);
        list.add(1, s2);
        list.add(2, s3);


        list.add(3, s4);
        list.add(4, s5);
        list.add(5, s6);
        list.add(6, s7);

        list.removeElement(s7);
        System.out.println(list.toString());

        list.removeElement(s1);
        System.out.println(list.toString());

        list.removeElement(s4);
        System.out.println(list.toString());
    }

}
