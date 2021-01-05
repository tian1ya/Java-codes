package dateStructure.chapt07.bsTree;

import dateStructure.chapt03.Iterator;
import dateStructure.chapt03.List;
import dateStructure.chapt03.ListDLNode;
import dateStructure.chapt04.binaryTree.BinTreeLinkedList;
import dateStructure.chapt04.binaryTree.BinTreePosition;
import dateStructure.chapt05.Comparator;
import dateStructure.chapt05.ComparatorDefault;
import dateStructure.chapt05.Entry;
import dateStructure.chapt05.EntryDefault;
import dateStructure.chapt06.UpSortDict.Dictionary;

public class BSTree extends BinTreeLinkedList implements Dictionary {
    protected Comparator C;
    protected BinTreePosition lastV;

    public BSTree(BinTreePosition root, Comparator c) {
        root = root;
        C = c;
    }

    public BSTree(BinTreePosition root) {
        this(root, new ComparatorDefault());
    }

    public BSTree() {
        this(null, new ComparatorDefault());
    }

    public BSTree(Comparator c, BinTreePosition lastV) {
        C = c;
        this.lastV = lastV;
    }

    @Override
    public Entry find(Object key) {
        if (isEmpty())
            return null;
        BSTreeNode u = binSearch((BSTreeNode) root, key, C);
        return (0 == C.compare(key, u.getKey()))
                ? (Entry) u.getEle()
                : null;
    }

    @Override
    public Iterator findAll(Object key) {
        List s = new ListDLNode();
        findAllNodes((BSTreeNode) root, key, s, C);
        return s.elements();
    }

    @Override
    //插入条目(key, value)，并返回该条目
    //lastV指示被插入的节点
    public Entry insert(Object key, Object value) {
        Entry e = new EntryDefault(key, value);//创建新的元素
        if (isEmpty()) {//插入根节点的情况
            root = new BSTreeNode(e, null, true, null, null);//插入新节点
            lastV = root;
        } else {//插入一般节点的情况
            BSTreeNode p = (BSTreeNode) root;//从根节点开始，查找可插入位置
            boolean asLeftChild = false;//表示新节点是否作为p的左孩子插入
            while (true) {//不断地
                p = binSearch(p, key, C);//查找关键码为key的节点，直至
                if (C.compare(key, p.getKey()) < 0)//查找失败于无左孩子节点，或
                {
                    asLeftChild = true;
                    break;
                } else if (C.compare(key, p.getKey()) > 0)//查找失败无右孩子节点，或
                {
                    asLeftChild = false;
                    break;
                } else if (!p.hasLChild())//查找成功，且可作为左孩子插入，或
                {
                    asLeftChild = true;
                    break;
                } else if (!p.hasRChild())//查找成功，且可作为右孩子插入，或
                {
                    asLeftChild = false;
                    break;
                } else//否则
                    p = (BSTreeNode) p.getLChild();//在左子树中继续查找(当然，在右子树中查找亦可) }//至此，新节点可以作为p的孩子插入，插入的方向由childType确定
            }//至此，新节点可以作为p的孩子插入，插入的方向由childType确定
            lastV = new BSTreeNode(e, p, asLeftChild, null, null);//插入新节点
        }//else
        return e;
    }

    @Override
    // 若词典中存在以key为关键码的条目，则摘除这样的一个节点，并返回其中存放的条目;否则，返回null
    // lastV指示被删除节点的父亲
    /*
        没有左右子节点，可以直接删除
        存在左节点或者右节点，删除后需要对子节点移动
        同时存在左右子节点，不能简单的删除，但是可以通过和后继节点交换后转换为前两种情况
     */
    public Entry remove(Object key) {
        if (isEmpty())
            return null;
        BinTreePosition v = binSearch((BSTreeNode) root, key, C);
        if (C.compare(key, ((BSTreeNode) v).getKey()) != 0)
            //若查找失败，则返 回null
            return null;
        //至此查找必成功，v为待删除节点
        if (v.hasLChild()) {
            //在v的左子树中找出其直接前驱
            BinTreePosition w = v.getPrev();
            w.setEle(v.setEle(w.getEle()));//交换v和u的数据对象
            v = w;
            // v 指向了w， w指向了v，且二者的元素也以及发生了交换
            // 这个时候还没有删除的，只不过将
            // 情况3（同时存在左右子节点，不能简单的删除，但是可以通过和后继节点交换后转换为前两种情况）
            // 转换为前两种情况，在后继代码中删除v。
        }
        // 至此，v至多只有一个孩子
        // 下面，删除v，代之以其孩子
        // 取待删除节点v的父亲
        lastV = v.getParent();
        BinTreePosition u = v.hasLChild()
                ? v.getLChild()
                : v.getRChild();//取v的孩子u

        if (null == lastV)//若v恰为树根root节点
        {
            if (null != u)
                u.secede();
            root = u;
        } else {
            if (v.isLChild())//若v是p的左孩子，则
            {
                v.secede();
                //摘出v，将u作为p的左孩子
                lastV.attachL(u);
            } else {
                //摘出v，将u作为p的右孩子
                v.secede();
                lastV.attachR(u);
            }
        }
        return (Entry) v.getEle();//返回被删除节点中存放的的元素
    }

    @Override
    public Iterator entries() {
        ListDLNode list = new ListDLNode();
        concatenate(list, (BSTreeNode) root);
        return list.elements();
    }

    // 在以v为根的子树中查找关键码为key的节点(假设该子树不为空)
    // 若找到，则返回该节点
    // 否则，返回被访问的最后一个节点
    // 为了确定是否成功，上层方法需要再检查一次返回节点的关键码
    protected static BSTreeNode binSearch(BSTreeNode v, Object key, Comparator c) {
        // 当前节点
        BSTreeNode u = v;
        while (true) {
            int com = c.compare(key, u.getKey());
            if (com < 0) {
                if (u.hasLChild())
                    u = (BSTreeNode) u.getLChild();
                else
                    //终止于无左孩子节点
                    return u;
            } else if (com > 0) {
                if (u.hasRChild())
                    u = (BSTreeNode) u.getRChild();
                else
                    //终止于无右孩子节点
                    return u;
            } else
                //查找命中
                return u;
        }
    }

    //在以v为根节点的(子)树中，递归地找出关键码为key的所有节点
    //这些节点被组织为一个列表(借此可以生成一个迭代器)
    protected static void findAllNodes(BSTreeNode v, Object k, List s, Comparator c) {
        if (v == null)
            return;
        int comp = c.compare(k, v.getKey());
        //查找左子树
        if (comp <= 0) findAllNodes((BSTreeNode) v.getLChild(), k, s, c);
        //命中
        if (comp == 0) s.insertFirst(v);
        //查找右子树
        if (comp >= 0) findAllNodes((BSTreeNode) v.getRChild(), k, s, c);

    }

    //将v的所有后代节点(中存放的条目)组织为一个列表(借此可以生成一个迭代器)
    protected static void concatenate(List list, BSTreeNode v) {
        if (v == null)
            return;
        concatenate(list, (BSTreeNode) v.getLChild());
        list.insertLast(v.getEle());
        concatenate(list, (BSTreeNode) v.getRChild());
    }
}
