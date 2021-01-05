package dateStructure.chapt02.linkedList;


import dateStructure.chapt02.Assert;

import java.util.Objects;

public class linkedListTest {
    public static void main(String[] args) {
//        insertFirstNodeTest();
//        insertManyValues();
        deleteOneNodeInManyValues();
    }

    public static void insertFirstNodeTest() {
        linkedList linkedList = new linkedList();
        linkedList.insert(1);
        Assert.assertThat((t) -> Objects.equals(t, 1), linkedList.getHead().getEle());

        Assert.assertThat((t) -> Objects.equals(t, 1), linkedList.getTail().getEle());
    }

    public static void insertManyValues() {
        linkedList linkedList = new linkedList();
        linkedList.insert(3);
        linkedList.insert(2);
        linkedList.insert(1);
        linkedList.insert(0);

        Assert.assertThat((t) -> Objects.equals(t, 0), linkedList.getHead().getEle());
        Assert.assertThat((t) -> Objects.equals(t, 1), linkedList.getHead().getNext().getEle());
        Assert.assertThat((t) -> Objects.equals(t, 2), linkedList.getHead().getNext().getNext().getEle());
        Assert.assertThat((t) -> Objects.equals(t, 3), linkedList.getHead().getNext().getNext().getNext().getEle());

        Assert.assertThat((t) -> Objects.equals(t, 3), linkedList.getTail().getEle());
        Assert.assertThat((t) -> Objects.equals(t, 4), linkedList.length());
    }

    public static void deleteOneNodeInManyValues() {
        linkedList linkedList = new linkedList();
        linkedList.insert(3);
        linkedList.insert(2);
        linkedList.insert(1);

        linkedList.delete(2);
        Assert.assertThat((t) -> Objects.equals(t, 1), linkedList.getHead().getEle());
        Assert.assertThat((t) -> Objects.equals(t, 3), linkedList.getHead().getNext().getEle());

        Assert.assertThat((t) -> Objects.equals(t, 2), linkedList.length());

        linkedList.delete(1);
        Assert.assertThat((t) -> Objects.equals(t, 1), linkedList.length());
        Assert.assertThat((t) -> Objects.equals(t, 3), linkedList.getHead().getEle());

    }
}
