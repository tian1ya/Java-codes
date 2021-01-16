package com.it.leetcode.dsPlay.dsa;

import dateStructure.dsPlay.dsa.setAndMap.BSTSet;
import org.junit.Assert;
import org.junit.Test;

public class BSTSetTest {

    @Test
    public void should_not_include_dup_ele() {
        BSTSet<String> bstSet = new BSTSet<>();

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
        Assert.assertTrue(bstSet.toString().contains("[aaa, aaa2, aasae, aada, asada, aadda, asad34a, asa534da, ]"));
    }
}
