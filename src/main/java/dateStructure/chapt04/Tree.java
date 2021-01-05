package dateStructure.chapt04;

public interface Tree {
    Object getElem();
    Object setElem(Object object);
    TreeLinkedList getParent();
    TreeLinkedList getFirstChild();
    TreeLinkedList getNextSibling();
    int getSize();
    int getHeight();
    int getDepth();
}
