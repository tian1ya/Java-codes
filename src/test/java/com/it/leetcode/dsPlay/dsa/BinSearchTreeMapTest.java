package com.it.leetcode.dsPlay.dsa;

import dateStructure.dsPlay.setAndMap.BinSearchTreeMap;
import dateStructure.dsPlay.setAndMap.LinkedListMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class BinSearchTreeMapTest {

    public Student createStudent(int age, String name) {
        return new Student(name,age);
    }

    @Test
    public void should_add() {
        BinSearchTreeMap<Integer, Student> map = new BinSearchTreeMap<>();

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int age = random.nextInt(100000);
            Student student = createStudent(i, String.valueOf(age));
            map.add(age, student);
        }

        Assert.assertTrue(map.getSize() > 10);
    }

    @Test
    public void should_get_min_and_max() {

        BinSearchTreeMap<Integer, Student> map = new BinSearchTreeMap<>();

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int age = random.nextInt(100);
            Student student = createStudent(i, String.valueOf(i));
            map.add(age, student);
        }

        Student student = createStudent(0, String.valueOf("00"));
        map.add(0, student);

        Student student1 = createStudent(101, String.valueOf("1001"));
        map.add(101, student1);

        Student min = map.getMin();
        Assert.assertEquals(min, student);

        Student max = map.getMax();
        Assert.assertEquals(max, student1);
    }


    @Test
    public void should_remove_min_and_max() {

        BinSearchTreeMap<Integer, Student> map = new BinSearchTreeMap<>();

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int age = random.nextInt(100);
            Student student = createStudent(i, String.valueOf(i));
            map.add(i, student);
        }

        map.removeMin();
        map.removeMax();

        Assert.assertEquals(map.getSize(), 3);
    }

    @Test
    public void should_remove_any_node() {
        BinSearchTreeMap<Integer, Student> map = new BinSearchTreeMap<>();


        Student student = createStudent(6, String.valueOf("6"));
        map.add(6, student);


        Student student2 = createStudent(8, String.valueOf("8"));
        map.add(8, student2);

        Student student3 = createStudent(2, String.valueOf("2"));
        map.add(2, student3);

        Student student44 = createStudent(4, String.valueOf("4"));
        map.add(4, student44);

        Student student4 = createStudent(1, String.valueOf("1"));
        map.add(1, student4);

        Student student77 = createStudent(7, String.valueOf("7"));
        map.add(7, student77);


        Student student34 = createStudent(9, String.valueOf("9"));
        map.add(9, student34);

        map.remove(1);
        Assert.assertEquals(map.getSize(), 6);

        map.remove(4);
        Assert.assertEquals(map.getSize(), 5);

        map.remove(8);
        Assert.assertEquals(map.getSize(), 4);
    }



    @Test
    public void should_remove_middle_node() {
        BinSearchTreeMap<Integer, Student> map = new BinSearchTreeMap<>();


        Student student = createStudent(6, String.valueOf("6"));
        map.add(6, student);


        Student student2 = createStudent(8, String.valueOf("8"));
        map.add(8, student2);

        Student student3 = createStudent(2, String.valueOf("2"));
        map.add(2, student3);

        Student student44 = createStudent(4, String.valueOf("4"));
        map.add(4, student44);

        Student student4 = createStudent(1, String.valueOf("1"));
        map.add(1, student4);

        Student student77 = createStudent(7, String.valueOf("7"));
        map.add(7, student77);


        Student student34 = createStudent(9, String.valueOf("9"));
        map.add(9, student34);


        map.remove(6);
        Assert.assertEquals(map.getSize(), 6);
    }

    @Test
    public void should_contains() {
        BinSearchTreeMap<Integer, Student> map = new BinSearchTreeMap<>();


        Student student = createStudent(6, String.valueOf("6"));
        map.add(6, student);


        Student student2 = createStudent(8, String.valueOf("8"));
        map.add(8, student2);

        Student student3 = createStudent(2, String.valueOf("2"));
        map.add(2, student3);

        Student student44 = createStudent(4, String.valueOf("4"));
        map.add(4, student44);

        Student student4 = createStudent(1, String.valueOf("1"));
        map.add(1, student4);

        Student student77 = createStudent(7, String.valueOf("7"));
        map.add(7, student77);


        Student student34 = createStudent(9, String.valueOf("9"));
        map.add(9, student34);

        boolean contains = map.contains(9);
        Assert.assertTrue(contains);


        boolean conta2ins = map.contains(90);
        Assert.assertTrue(!conta2ins);
    }

    @Test
    public void should_set_and_get() {
        BinSearchTreeMap<Integer, Student> map = new BinSearchTreeMap<>();


        Student student = createStudent(6, String.valueOf("6"));
        map.add(6, student);


        Student student2 = createStudent(8, String.valueOf("8"));
        map.add(8, student2);

        Student student3 = createStudent(2, String.valueOf("2"));
        map.add(2, student3);

        Student student44 = createStudent(4, String.valueOf("4"));
        map.add(4, student44);

        Student student4 = createStudent(1, String.valueOf("1"));
        map.add(1, student4);

        Student student77 = createStudent(7, String.valueOf("7"));
        map.add(7, student77);


        Student student34 = createStudent(9, String.valueOf("9"));
        map.add(9, student34);

        Student newValue = new Student("4444", 4);
        map.set(4, newValue);

        Student student1 = map.get(4);
        Assert.assertEquals(student, newValue);
    }
}
