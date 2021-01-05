package dateStructure.chapt04.binaryTree;

import dateStructure.chapt03.Iterator;

public class BinTreeLinkedList implements BinTree {

    protected BinTreePosition root;

    public BinTreeLinkedList(BinTreePosition root) {
        this.root = root;
    }

    public BinTreeLinkedList() {
        this(null);
    }

    @Override
    public BinTreePosition getRoot() {
        return root;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int getSize() {
        return isEmpty() ? -1 : root.getSize();
    }

    @Override
    public int getHeight() {
        return isEmpty() ? -1 : root.getHeight();
    }

    @Override
    public Iterator elementsPreOrder() {
        return root.elementsPreOrder();
    }

    @Override
    public Iterator elementsPostOrder() {
        return root.elementsPostOrder();
    }

    @Override
    public Iterator elementsInOrder() {
        return root.elementsInOrder();
    }

    @Override
    public Iterator elementsLevelOrder() {
        return root.elementsLevelOrder();
    }
}
