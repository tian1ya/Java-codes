package com.it.leetcode.dsPlay.dsa;

import dateStructure.dsPlay.dsa.setAndMap.LinkedListSet;
import org.junit.Assert;
import org.junit.Test;

public class LinkedListSetTest {

    @Test
    public void should_not_include_dup_ele() {
        LinkedListSet<String> bstSet = new LinkedListSet<>();

        bstSet.add("aaa");
        bstSet.add("aaa");
        bstSet.add("aaa2");
        bstSet.add("aasae");
        bstSet.add("aada");
        bstSet.add("aadda");
        bstSet.add("asada");
        bstSet.add("asad34a");
        bstSet.add("asa534da");

        System.out.println(bstSet);
        Assert.assertEquals(bstSet.geiSize(), 8);
        Assert.assertTrue(bstSet.toString().contains("asa534da => asad34a => asada => aadda => aada => aasae => aaa2 => aaa => NULL"));
    }
}
