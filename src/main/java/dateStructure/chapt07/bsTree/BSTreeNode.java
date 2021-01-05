package dateStructure.chapt07.bsTree;

import dateStructure.chapt04.binaryTree.BinTreeNode;
import dateStructure.chapt04.binaryTree.BinTreePosition;
import dateStructure.chapt05.Entry;

// 二分查找树节点类的实现
public class BSTreeNode extends BinTreeNode implements BinTreePosition, Entry {
    public BSTreeNode() {
        super();
    }

    public BSTreeNode(Object element,
                      BinTreePosition parent,
                      boolean asLChild,
                      BinTreePosition lChild,
                      BinTreePosition rChild) {
        super(element, parent, asLChild, lChild, rChild);
    }

    @Override
    //返回当前节点的关键码
    public Object getKey() {
        return ((Entry)getEle()).getKey();
    }

    @Override
    //修改条目的关键码，返回此前存放的关键码
    public Object setKey(Object key) {
        return ((Entry)getEle()).setKey(key);
    }

    @Override
    public Object getValue() {
        return ((Entry)getEle()).getValue();
    }

    @Override
    public Object setValue(Object value) {
        return ((Entry)getEle()).setValue(value);
    }
}
