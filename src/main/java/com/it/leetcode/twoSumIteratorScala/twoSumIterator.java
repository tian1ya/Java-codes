package com.it.leetcode.twoSumIteratorScala;

import dateStructure.chapt03.Iterator;

class twoSumIterator implements Iterator {

    private com.it.leetcode.twoSumIteratorScala.Nodes nodes;

    public twoSumIterator(Nodes nodes) {
        this.nodes = nodes;
    }

    @Override
    public boolean hasNext() {
        return nodes.getFirst() != null;
    }

    @Override
    public Node getNext() {
        return nodes.removeFirst();
    }
}