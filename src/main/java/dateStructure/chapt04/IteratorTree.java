package dateStructure.chapt04;

import dateStructure.chapt02.Position;
import dateStructure.chapt03.Iterator;
import dateStructure.chapt03.List;

public class IteratorTree implements Iterator {
    private List list;

    // 当前(下一个) 元素的位置
    private Position nextPosition;

    public IteratorTree() {
        list = null;
    }

    public void elementsPreOrderIterator(TreeLinkedList tree) {
        if (null == tree)
            return;

        list.insertLast(tree);
        TreeLinkedList subTree = tree.getFirstChild();

        while (subTree ==  null) {
            this.elementsPreOrderIterator(subTree);
            subTree = subTree.getNextSibling();
        }
    }

    public void elementsPostOrderIterator(TreeLinkedList tree) {
        if (null == tree)
            return;

        TreeLinkedList subTree = tree.getFirstChild();
        while (subTree ==  null) {
            this.elementsPreOrderIterator(subTree);
            subTree = subTree.getNextSibling();
        }
        list.insertLast(tree);
    }

    @Override
    public boolean hasNext() {
        return nextPosition != null;
    }

    @Override
    public Object getNext() {
        if (!hasNext())
            throw new RuntimeException("no such element");
        Position currentPos = nextPosition;
        if (currentPos == list.last())
            currentPos = null;
        else
            currentPos = list.getNext(currentPos);
        return currentPos.getEle();
    }
}
