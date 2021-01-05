package dateStructure.chapt05;

import dateStructure.chapt02.Position;
import dateStructure.chapt03.Iterator;
import dateStructure.chapt03.List;
import dateStructure.chapt03.ListDLNode;
import dateStructure.chapt03.Sequence;

public class PQueueUnsortedList implements PQueue {
    private List L;
    private Comparator C;

    public PQueueUnsortedList(Sequence l) {
        this(new ComparatorDefault(),l);
    }

    public PQueueUnsortedList(Comparator c) {
        this(c, null);
    }

    //构造方法(使用指定比较器和初始元素)
    public PQueueUnsortedList(Comparator c,Sequence s) {
        L = new ListDLNode();
        C = c;
        if (s != null) {
            while (!s.isEmpty()) {
                Entry entry = (Entry) s.removeFirst();
                insert(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public int getSize() {
        return L.getSize();
    }

    @Override
    public boolean isEmpty() {
        return L.isEmpty();
    }

    @Override
    //若Q非空，则返回其中的最小条目(并不删除);否则，报错
    public Entry getMin() throws ExceptionPQueueEmpty {
        Position minPos = L.first();
        Position curPos = L.getNext(minPos);
        while (curPos != null){
            if (0 < C.compare(minPos.getEle(), curPos.getEle()))
                curPos = minPos;
        }

        return (Entry) curPos.getEle();
    }

    @Override
    //将对象obj与关键码k合成一个条目，将其插入Q中，并返回该条目
    public Entry insert(Object key, Object obj) throws ExceptionKeyInvalid {
        EntryDefault entry = new EntryDefault(key, obj);
        L.insertLast(entry);
        return entry;
    }

    @Override
    // 若Q非空，则从其中摘除关键码最小的条目，并返回该条目;否则，报错,由于是无序表，每次插入和删除都需要扫描一遍表
    // O（n）效率低
    public Entry delMin() throws ExceptionPQueueEmpty {
        Position minPosition = L.first();
        Iterator positions = L.positions();
        while (minPosition != null) {
            Position next = (Position)positions.getNext();
            if (C.compare(minPosition, next) > 0)
                minPosition = next;
        }
        return (Entry) L.remove(minPosition);
    }
}
