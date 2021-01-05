package dateStructure.chapt04.binaryTree;

import dateStructure.chapt02.linkedList.QueueList;
import dateStructure.chapt03.Iterator;
import dateStructure.chapt03.List;
import dateStructure.chapt03.ListDLNode;

public class BinTreeNode implements BinTreePosition {
    protected Object element;
    protected BinTreePosition parent;
    protected BinTreePosition lChild;
    protected BinTreePosition rChild;
    protected int size;
    protected int height;
    protected int depth;

    public BinTreeNode() {
        this(null, null, true, null, null);
    }

    public BinTreeNode(Object element, BinTreePosition parent,
                       boolean asLChild,// 是否作为父节点的左孩子
                       BinTreePosition lChild, BinTreePosition rChild) {

        this.element = element;
        size = 1;
        height = depth = 0;

        // 建立父于子的关系
        if (parent != null)
            if (asLChild)
                parent.attachL(this);
            else
                parent.attachR(this);

        if (lChild != null)
            attachL(lChild);
        if (rChild != null)
            attachR(rChild);

    }

    @Override
    public boolean hasParent() {
        return parent != null;
    }

    @Override
    public BinTreePosition getParent() {
        return parent;
    }

    @Override
    public void setParent(BinTreePosition p) {
        parent = p;
    }

    @Override
    public boolean isLeaf() {
        return !hasRChild() && !hasLChild();
    }

    @Override
    public boolean isChild() {
        return false;
    }

    @Override
    public BinTreePosition getChild() {
        return null;
    }

    @Override
    public void setChild(BinTreePosition child) {

    }

    @Override
    public boolean isRChild() {
        return hasParent() && getParent().getRChild() == this;
    }

    @Override
    public boolean hasRChild() {
        return rChild != null;
    }

    @Override
    public void setRChild(BinTreePosition child) {
        this.rChild = child;
    }


    @Override
    public BinTreePosition getRChild() {
        return rChild;
    }

    @Override
    public boolean isLChild() {
        return hasParent() && getParent().getLChild() == this;
    }

    @Override
    public boolean hasLChild() {
        return lChild != null;
    }

    @Override
    public void setLChild(BinTreePosition child) {
        this.lChild = child;
    }

    @Override
    public BinTreePosition getLChild() {
        return lChild;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    //在孩子发生变化后，更新当前节点及其祖先的规模
    public void updateSize() {
        // 当前节点
        size = 1;
        if (hasLChild())
            size += getLChild().getSize();//左子树的规模
        if (hasRChild())
            size += getRChild().getSize();//右子树的规模
        if (hasParent())
            getParent().updateSize();//递归更新各个真祖先的规模记录
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void updateHeight() {
        height = 0;//先假设没有左、右孩子
        if (hasLChild())
            height = Math.max(height, 1 + getLChild().getHeight());//左孩子
        if (hasRChild())
            height = Math.max(height, 1 + getRChild().getHeight());//右孩子
        if (hasParent())
            getParent().updateHeight();//递归更新各个真祖先的高度记录
    }

    @Override
    public int getDepth() {
        return depth;
    }

    @Override
    public void updateDepth() {
        depth = hasParent() ? 1 + getParent().getDepth() : 0;//当前节点
        if (hasLChild())
            getLChild().updateDepth();//沿孩子引用逐层向下，
        if (hasRChild())
            getRChild().updateDepth();//递归地更新所有后代的深度记录
    }

    @Override
    // 是该节点的左子树中的最大节点。
    // 按照中序遍历的次序，找到当前节点的直接前驱
    // 若左子树非空，则其中的最大者即为当前节点的直接前驱
    public BinTreePosition getPrev() {
        if (hasLChild())
            return findMaxDescendant(getLChild());

        // 至此，当前节点没有左孩子
        // x是"一个右孩子"，则"x的前驱结点"为 "它的父结点"。
        if (isRChild())
            //若当前节点是右孩子，则父亲即为其直接前驱
            return getParent();

        // 至此，当前节点没有左孩子，而且是左孩子
        // 从当前节点出发
        BinTreePosition v = this;
        while (v.isLChild())
            v = v.getParent();
        // 沿左孩子链一直上升
        // 至此，v或者没有父亲，或者是父亲的右孩子
        return v.getParent();
    }

    @Override
    //按照中序遍历的次序，找到当前节点的直接后继
    public BinTreePosition getSucc() {
        //若右子树非空，则其中的最小者即为当前节点的直接后继
        if (hasRChild())
            return findMinDescendant(getRChild());
        //至此，当前节点没有右孩子
        if (hasLChild())
            return getParent();
        BinTreePosition v = this;
        while (v.isRChild())
            //沿右孩子链一直上升
            v = v.getParent();
        //至此，v或者没有父亲，或者是父亲的左孩子
        return v.getParent();
    }

    @Override
    //返回当前节点， 将以某一节点为根的子树从母树中分离出来。
    public BinTreePosition secede() {
        if (parent != null) {
            if (isLChild())
                //切断父亲指向当前节点的引用
                parent.setLChild(null);
            else
                parent.setRChild(null);

            parent.updateSize();
            parent.updateHeight();
            parent = null;
            updateDepth();
        }
        return this;
    }

    @Override
    //将节点c作为当前节点的左孩子
    public BinTreePosition attachL(BinTreePosition c) {
        if (hasLChild())
            //摘除当前节点原先的左孩子
            getChild().secede();
        if (c != null) {
            c.secede();
            lChild = c;
            c.setParent(this);
            updateSize();
            updateHeight();
            c.updateDepth();
        }
        return this;
    }

    @Override
    //将节点c作为当前节点的右孩子
    public BinTreePosition attachR(BinTreePosition c) {
        if (hasRChild())
            getRChild().secede();
        if (c != null) {
            c.secede();
            rChild = c;
            c.setParent(this);
            updateSize();
            updateHeight();
            c.updateDepth();
        }
        return this;
    }

    @Override
    public Iterator elementsPreOrder() {
        ListDLNode list = new ListDLNode();
        preorder(list, this);
        return list.elements();
    }

    @Override
    public Iterator elementsInOrder() {
        ListDLNode list = new ListDLNode();
        inorder(list, this);
        return list.elements();
    }

    @Override
    public Iterator elementsPostOrder() {
        ListDLNode list = new ListDLNode();
        postorder(list, this);
        return list.elements();
    }

    @Override
    public Iterator elementsLevelOrder() {
        ListDLNode list = new ListDLNode();
        levelorder(list, this);
        return list.elements();
    }

    @Override
    public Object getEle() {
        return element;
    }

    @Override
    public Object setEle(Object ele) {
        Object old = this.element;
        this.element = ele;
        return old;
    }


    /*
        ===============================================================================================
     */

    //在v的后代中，找出最小者
    private BinTreePosition findMinDescendant(BinTreePosition v) {
        if (v != null) {
            while (v.hasLChild())
                //从v出发，沿左孩子链一直下降
                v = v.getLChild();
            //至此，v或者为空，或者没有左孩子
        }
        return v;
    }

    //在v的后代中，找出最大者
    private BinTreePosition  findMaxDescendant(BinTreePosition v) {
        if (v != null) {
            while (v.hasRChild())
                //从v出发，沿左孩子链一直下降
                v = v.getRChild();
            //至此，v或者为空，或者没有左孩子
        }
        return v;
    }

    private void preorder(List list, BinTreePosition v) {
        if (v == null)
            //递归基:空树
            return;
        list.insertLast(v);
        //遍历左子树
        preorder(list, v.getLChild());
        //遍历右子树
        preorder(list, v.getRChild());
    }

    //中序遍历以v为根节的(子)树
    private void inorder(List list, BinTreePosition v) {
        if (v == null)
            //递归基:空树
            return;
        //遍历左子树
        inorder(list, v.getLChild());
        list.insertLast(v);

        //遍历右子树
        inorder(list, v.getRChild());
    }

    //后序遍历以v为根节的(子)树
    private void postorder(List list, BinTreePosition v) {
        if (v == null)
            //递归基:空树
            return;
        //遍历左子树
        postorder(list, v.getLChild());
        //遍历右子树
        postorder(list, v.getRChild());

        list.insertLast(v);
    }

    //后序遍历以v为根节的(子)树
    private void levelorder(List list, BinTreePosition v) {
        QueueList queueList = new QueueList();
        queueList.enqueue(v);

        while (!queueList.isEmpty()) {
            BinTreePosition u = (BinTreePosition) queueList.dequeue();
            list.insertLast(u);
            if (u.hasLChild())
                queueList.enqueue(u.getLChild());
            if (u.hasRChild())
                queueList.enqueue(u.getRChild());
        }
    }
}
