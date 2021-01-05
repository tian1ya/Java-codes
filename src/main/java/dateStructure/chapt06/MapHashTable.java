package dateStructure.chapt06;

import dateStructure.chapt03.Iterator;
import dateStructure.chapt03.IteratorElement;
import dateStructure.chapt03.List;
import dateStructure.chapt03.ListDLNode;
import dateStructure.chapt05.Entry;

public class MapHashTable implements Map {
    //桶数组，每个桶本身也是一个(基于列表实现的)映射结构
    private Map[] A;

    //散列表长
    private int N;

    //装填因子上限
    private final double maxlemda = 0.75;

    //判等器
    private EqualityTester T;

    private int size;

    public MapHashTable(int n, EqualityTester t) {
        N = p(n);
        T = t;
        A = new Map[N];
        for (int i = 0; i < N; i++) {
            A[i] = new MapDLNode(T);
        }
        size = 0;
    }

    //散列定址函数(采用模余法)
    public int h(Object key) {
        return key.hashCode() % N;
    }

    //判断n是否为素数
    private static boolean prime(int n) {
        for (int i = 3; i < 1 + Math.sqrt(n); i++)
            if (n / i * i == n) return false;
        return true;
    }

    //取不小于n的最小素数
    private static int p(int n) {
        if (3 > n)
            n = 3;
        n = n | 1;//奇数化
        while (!prime(n))
            n += 2;
        return n;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object get(Object key) {
        return A[h(key)].get(key);
    }

    @Override
    // 若M中不存在以key为关键码的条目，则将条目(key, value)加入到M中并返回null
    // 否则，将已有条目的数据对象替换为value，并返回原先的数据对象
    public Map put(Object key, Object value) {
        Object oldOne = A[h(key)].put(key, value);
        if (oldOne == null) {
            size++;
        }
        if (size > N * maxlemda)
            //若装填因子过大，则重散列
            rehash();
        return null;
    }

    private void rehash() {
        Iterator entries = this.entries();
        N = p(N << 1);
        //桶数组容量至少加倍
        A = new Map[N];
        for (int i = 0; i < N; i++) {
            A[i] = new MapDLNode(T);
        }

        while (entries.hasNext()) {
            Entry next = (Entry) entries.getNext();
            Object key = next.getKey();
            Object value = next.getValue();
            A[h(key)].put(key,value);
        }
    }

    @Override
    public Object remove(Object key) {
        Object oldValue = A[h(key)].remove(key);
        if (oldValue != null) {
            size--;
        }
        return oldValue;
    }

    @Override
    //返回M中所有条目的一个迭代器
    //将各桶对应的映射结构的迭代器串接起来，构成整体的迭代器
    public Iterator entries() {
        List L = new ListDLNode();
        for (int i=0; i<N; i++) {
            Iterator it = A[i].entries();
            while (it.hasNext())  L.insertLast(it.getNext());
        }
        return new IteratorElement(L);
    }
}
