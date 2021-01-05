package dateStructure.chapt07.avlTree;

import dateStructure.chapt04.binaryTree.BinTreePosition;
import dateStructure.chapt05.Comparator;
import dateStructure.chapt05.Entry;
import dateStructure.chapt06.UpSortDict.Dictionary;
import dateStructure.chapt07.bsTree.BSTree;
import dateStructure.chapt07.bsTree.BSTreeNode;

public class AVLTree extends BSTree implements Dictionary {
    public AVLTree() {
        super();
    }

    public AVLTree(BinTreePosition root) {
        super(root);
    }

    public AVLTree(BinTreePosition root, Comparator c) {
        super(root, c);
    }

    //插入条目(key, value)，并返回该条目
    public Entry insert(Object key, Object value) {
        Entry e = super.insert(key, value);
        //从插入节点的父亲开始重新平衡化
        root = rebalance(lastV.getParent(), root);
        return e;
    }

    public Entry remove(Object key) {
        Entry e = super.remove(key);//调用父类方法完成删除
        if (null != e)
            root = rebalance(lastV, root);//从删除节点的父亲开始重新平衡化
        return e;
    }
    //从节点z开始，自上而下重新平衡化
    //返回后，root仍为平衡后的(整棵)树的根节点
    protected static BinTreePosition rebalance(BinTreePosition z, BinTreePosition root) {
        if (z == null)
            return z;
        while (true) {
            if (!isBalanced(z))
                rotate(z);
            if (!z.hasParent())
                return z;
            z = z.getParent();
        }
    }

    protected static boolean isBalanced(BinTreePosition v) {
        if (v == null)
            return true;
        int lH = (v.hasLChild()) ? (v.getLChild().getHeight()) : -1;
        int rH = (v.hasRChild()) ? (v.getRChild().getHeight()) : -1;
        int deltaH = lH - rH;

//        deltaH == 0
        return (-1 <= deltaH) && (deltaH <= 1);
    }

    //通过旋转，使节点z的平衡因子的绝对值不超过1(支持AVL树)
    //返回新的子树根
    public static BinTreePosition rotate(BinTreePosition z) {
        BinTreePosition y = tallerChild(z);//取y为z更高的孩子
        BinTreePosition x = tallerChild(y);//取x为y更高的孩子
        boolean cType = z.isLChild();//记录:z是否左孩子
        BinTreePosition p = z.getParent();//p为z的父亲
        BinTreePosition a, b, c;//自左向右，三个节点
        BinTreePosition t0, t1, t2, t3;//自左向右，四棵子树

        /******** 以下分四种情况 ********/
        if (y.isLChild()) {//若y是左孩子，则
            c = z;
            t3 = z.getRChild();
            if (x.isLChild()) {//若x是左孩子
                b = y;
                t2 = y.getRChild();
                a = x;
                t1 = x.getRChild();
                t0 = (BSTreeNode) x.getLChild();
            } else {//若x是右孩子
                a = y;
                t0 = y.getLChild();
                b = x;
                t1 = x.getLChild();
                t2 = (BSTreeNode) x.getRChild();
            }
        } else {//若y是右孩子，则
            a = z;
            t0 = z.getLChild();
            if (x.isRChild()) {//若x是右孩子
                b = y;
                t1 = y.getLChild();
                c = x;
                t2 = x.getLChild();
                t3 = (BSTreeNode) x.getRChild();
            } else {//若x是左孩子
                c = y;
                t3 = y.getRChild();
                b = x;
                t1 = x.getLChild();
                t2 = (BSTreeNode) x.getRChild();
            }
        }

        //摘下三个节点 z.secede();
        y.secede();
        x.secede();

        //摘下四棵子树
        if (null != t0) t0.secede();
        if (null != t1) t1.secede();
        if (null != t2) t2.secede();
        if (null != t3) t3.secede();

        //重新链接
        a.attachL(t0);
        a.attachR(t1);
        b.attachL(a);
        c.attachL(t2);
        c.attachR(t3);
        b.attachR(c);

        //子树重新接入原树
        if (null != p)
            if (cType) p.attachL(b);
            else p.attachR(b);
        return b;//返回新的子树根
    }

    //返回节点p的孩子中的更高者
    protected static BinTreePosition tallerChild(BinTreePosition v) {
        int lH = v.hasLChild()
                ? v.getLChild().getHeight()
                : -1;

        int rH = v.hasRChild()
                ? v.getRChild().getHeight()
                : -1;

        if (lH > rH)
            return v.getLChild();

        if (lH < rH)
            return v.getRChild();

        if (v.isLChild())
            return v.getLChild();
        else
            return v.getRChild();
    }

    //返回节点p的孩子中的更矮者
    protected static BinTreePosition shorterChild(BinTreePosition v) {
        int lH = v.hasLChild()
                ? v.getLChild().getHeight()
                : -1;
        int rH = v.hasRChild()
                ? v.getRChild().getHeight()
                : -1;
        if (lH > rH)
            return v.getRChild();
        if (lH < rH)
            return v.getLChild();
        if (v.isLChild())
            return v.getRChild();
        else
            return v.getLChild();
    }

    // 请注意，这里的重平衡过程采用了统一算法。
}
