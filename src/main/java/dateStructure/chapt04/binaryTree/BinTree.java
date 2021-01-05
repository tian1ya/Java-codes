package dateStructure.chapt04.binaryTree;


import dateStructure.chapt03.Iterator;

public interface BinTree {
    BinTreePosition getRoot();

    boolean isEmpty();

    int getSize();

    int getHeight();

    Iterator elementsPreOrder();

    Iterator elementsPostOrder();

    // 中序遍历
    Iterator elementsInOrder();

    // 层次遍历
    Iterator elementsLevelOrder();


}
