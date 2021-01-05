package dateStructure.chapt04.binaryTree;

import dateStructure.chapt02.Position;
import dateStructure.chapt03.Iterator;

public interface BinTreePosition extends Position {
    boolean hasParent();

    // 返回当前节点的父节点
    BinTreePosition getParent();

    // 设置当前节点父节点
    void setParent(BinTreePosition p);

    boolean isLeaf();

    boolean isChild();

    // 返回左孩子
    BinTreePosition getChild();

    void setChild(BinTreePosition child);

    boolean isRChild();

    boolean hasRChild();

    void setRChild(BinTreePosition child);


    BinTreePosition getRChild();

    boolean isLChild();

    boolean hasLChild();

    void setLChild(BinTreePosition child);

    BinTreePosition getLChild();

    int getSize();

    void updateSize();

    int getHeight();

    // 当孩子点发送变化的时候，跟下当前父节点及祖先的高度
    void updateHeight();

    int getDepth();

    // 当父节点发送变化的时候，跟下当前父节点及后代的深度
    void updateDepth();

    //中序遍历的次序，找到当前节点的之后前驱
    BinTreePosition getPrev();

    // 中序遍历的次序，找到当前节点的之间后继
    BinTreePosition getSucc();

    //短接当前节点和父节点的父子关系
    BinTreePosition secede();

    // 将节点 C 作为当前节点的左孩子
    BinTreePosition attachL(BinTreePosition c);

    // 将节点 C 作为当前节点的右孩子
    BinTreePosition attachR(BinTreePosition c);

    //前序遍历
    Iterator elementsPreOrder();

    Iterator elementsInOrder();

    Iterator elementsPostOrder();

    Iterator elementsLevelOrder();
}
