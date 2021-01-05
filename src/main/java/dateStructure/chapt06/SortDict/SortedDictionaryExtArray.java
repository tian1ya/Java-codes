package dateStructure.chapt06.SortDict;

import dateStructure.chapt03.*;
import dateStructure.chapt05.Comparator;
import dateStructure.chapt05.Entry;
import dateStructure.chapt05.EntryDefault;

public class SortedDictionaryExtArray implements SortedDictionary {
    // 有序查找表
    Vector S;

    Comparator C;

    public SortedDictionaryExtArray(Comparator comp) {
        S = new VectorArray();
        C = comp;
    }

    @Override
    public Entry first() {
        return (S.isEmpty()) ? null : (Entry) S.getAtRank(0);
    }

    /*
        二分查找
        返回值可能是命中元素的秩，也有可能是key可以插入的秩
        具体如何，需要进一步检查
        不变性：若将key 按照返回的秩插入有序向量，向量依然有序
     */
    private static int binSearch(Vector s, Comparator c, Object key, int lo, int hi) {
        if (lo > hi)
            return lo;
        int mi = (lo + hi) >> 1;
        Entry miEntry = (Entry) s.getAtRank(mi);
        int flag = c.compare(key, miEntry.getKey());
        if (flag < 0) {
            //转向左半区间
            return binSearch(s, c, key, lo, mi-1);
        }else if (flag > 0) {
            return binSearch(s, c, key, mi + 1, hi);
        }
        return mi;
    }

    @Override
    public Entry last() {
        return (S.isEmpty()) ? null : (Entry) S.getAtRank(S.getSize()-1);
    }

    @Override
    public Iterator successors(Object key) {
        List L = new ListDLNode();//创建一个链表L
        //查找关键码为key的条目
        int k = binSearch(S, C, key, 0, S.getSize()-1);

        if (0 > k || k >= S.getSize() || (0 != C.compare(key, ((Entry)S.getAtRank(k)).getKey())))
            return new IteratorElement(L);//若这样的条目不存在，则返回空迭代器
        while (0 <= --k)//从S[k-1]开始向前搜索，直至符合要求的、秩最小的元素
            if (0 != C.compare(key, ((Entry)S.getAtRank(k)).getKey())) break;
        while (S.getSize() > ++k)
            //将后继的所有元素依次
            L.insertLast(S.getAtRank(k));//插入L中
        return new IteratorElement(L);//由L创建迭代器，返回之
    }

    @Override
    public Iterator predecessors(Object key) {
        List L = new ListDLNode();
        int k = binSearch(S, C, key, 0, S.getSize()-1);
        //查找关键码为key的条目
        if (0 > k || k >= S.getSize() || (0 != C.compare(key, ((Entry)S.getAtRank(k)).getKey())))
            return new IteratorElement(L);//若这样的条目不存在，则返回空迭代器 §6.4 有序词典
        while (S.getSize() > ++k)
            //从S[k-1]开始向后搜索，直至符合要求的、秩最大的元素
            if (0 != C.compare(key, ((Entry)S.getAtRank(k)).getKey()))
                break;
            while (0 <= --k)//将前驱的所有元素依次
                 L.insertLast(S.getAtRank(k));//插入L中
        return new IteratorElement(L);//由L创建迭代器，返回之
    }

    @Override
    public int getSize() {
        return S.getSize();
    }

    @Override
    public boolean isEmpty() {
        return S.isEmpty();
    }

    @Override
    public Entry find(Object key) {
        int k = binSearch(S, C, key, 0, S.getSize()-1);//查找关键码为key的条目
        if (0 > k || k >= S.getSize() || (0 != C.compare(key, ((Entry)S.getAtRank(k)).getKey())))
            return null;//若这样的条目不存在，则返回失败标志
        return (Entry) S.getAtRank(k);
    }

    @Override
    //返回由关键码为key的条目组成的迭代器
    public Iterator findAll(Object key) {
        ListDLNode L = new ListDLNode();
        int k = binSearch(S, C, key, 0, getSize());
        if (k < 0 || k > getSize()) {
            //若这样的条目不存在，则返回空迭代器
            return new IteratorElement(L);
        }
        L.insertFirst(S.getAtRank(k));

        //从S[k-1]开始
        int lo = k;
        while (0 <= --lo) {
            //不断向前搜索
            if (0 != C.compare(key, ((Entry)S.getAtRank(lo)).getKey()))
                break;//直到第一个不命中的条目
            L.insertFirst(S.getAtRank(lo));//将命中的条目插入L中
        }

        int hi = k;//从S[k+1]开始
        while (++hi < S.getSize()) {//不断向后搜索
            if (0 != C.compare(key, ((Entry) S.getAtRank(hi)).getKey()))
                break;//直到第一个不 命中的条目
            L.insertLast(S.getAtRank(hi));//将命中的条目插入L中
        }
        return new IteratorElement(L);
    }

    @Override
    public Entry insert(Object key, Object value) {
        Entry e = new EntryDefault(key, value);//创建新条目
        //若词典为空，则直接插入新元素
        if (S.isEmpty())
            return (Entry) S.insertAtRank(0, e);
        //通过二分查找，确定可插入位置
        return (Entry) S.insertAtRank(binSearch(S, C, key, 0, S.getSize()-1), e);
    }

    @Override
    public Entry remove(Object key) {
        int k = binSearch(S, C, key, 0, S.getSize()-1);//查找关键码为key的条目
        if (0 > k || k >= S.getSize() || (0 != C.compare(key,
                ((Entry)S.getAtRank(k)).getKey())))
            return null;//若这样的条目不存在，则返回失败标志
        return (Entry) S.removeAtRank(k);
    }

    @Override
    public Iterator entries() {
        List L = new ListDLNode();
        for (int i=0; i<S.getSize(); i++)
            L.insertLast(S.getAtRank(i));
        return new IteratorElement(L);//直接利用List接口的方法生成元素迭代器
    }
}
