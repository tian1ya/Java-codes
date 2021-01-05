package com.it.leetcode.twoSumIteratorScala;

import dateStructure.chapt03.Iterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Nodes {
    private Node first;
    private int length;

    public Nodes() {
    }

    private Node createNode(Integer value) {
        return new Node(value);
    }

    public Node getFirst() {
        return first;
    }


    public Nodes add(Integer value) {
        Node node = createNode(value);
        if (first == null) {
            this.first = node;
        } else {
            Node lastPlace = findLastPlace();
            lastPlace.next = node;
        }
        length += 1;
        return this;
    }

    private Node findLastPlace() {
        Node currentPlace = this.first;
        while (currentPlace.next != null) {
            currentPlace = currentPlace.next;
        }
        return currentPlace;
    }

    public void printNodes() {
        Node currentPlace = this.first;

        while (currentPlace != null) {
            System.out.println(currentPlace.value);
            currentPlace = currentPlace.next;
        }
    }

    public int length() {
        return length;
    }

    public Iterator iterator() {
        return new twoSumIterator(this);
    }

    static List<Integer> sum(Nodes left, Nodes right) {
        if (left.length() != right.length()) {
            return Collections.emptyList();
        }

        Iterator leftIterator = left.iterator();
        Iterator rightIterator = right.iterator();

        ArrayList<Integer> sumResult = new ArrayList<>();
        Integer flag = 0;

        while (leftIterator.hasNext() && rightIterator.hasNext()) {
            Integer rightValue = ((Node) rightIterator.getNext()).value;
            Integer leftValue = ((Node) leftIterator.getNext()).value;
            if ((rightValue + leftValue) >= 10) {
                sumResult.add(rightValue + leftValue - 10);
                flag = 1;
            } else {
                sumResult.add(rightValue + leftValue + flag);
                flag = 0;
            }
        }

        return sumResult;
    }

    public Node removeFirst() {
        if (first == null) {
            return null;
        }
        Node removedNode = first;
        first = first.next;
        return removedNode;
    }

    public static String printSum(List<Integer>  sumNodes) {
        Collections.reverse(sumNodes);
        String result = "";
        for (Integer sumNode : sumNodes) {
            result += sumNode;
        }
        return result;
    }
}