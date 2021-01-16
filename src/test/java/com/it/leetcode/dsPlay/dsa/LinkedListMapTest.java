package com.it.leetcode.dsPlay.dsa;

import dateStructure.dsPlay.dsa.setAndMap.LinkedListMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class LinkedListMapTest {

    public Student createStudent(int age, String name) {
        return new Student(name,age);
    }

    @Test
    public void should() {
        LinkedListMap<Integer, Student> map = new LinkedListMap<>();

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int age = random.nextInt(1000);
            Student student = createStudent(i, String.valueOf(age));
            map.add(i, student);
        }

        map.remove(0);
    }

    @Test
    public void should_set_new_val() {
        LinkedListMap<Integer, Student> map = new LinkedListMap<>();

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int age = random.nextInt(1000);
            Student student = createStudent(i, String.valueOf(age));
            map.add(i, student);
        }

        Student aa = createStudent(0, "你好");
        map.set(0, aa);
        Assert.assertEquals(map.get(0), aa);
    }
}
