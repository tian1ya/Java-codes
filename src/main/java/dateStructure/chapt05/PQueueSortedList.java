package dateStructure.chapt05;

import dateStructure.chapt02.Position;
import dateStructure.chapt03.List;
import dateStructure.chapt03.ListDLNode;
import dateStructure.chapt03.Sequence;

// 要求所有条目按非升次序排列
public class PQueueSortedList implements PQueue{
    private List L;
    private Comparator C;

    public PQueueSortedList(Sequence l) {
        this(new ComparatorDefault(),l);
    }

    public PQueueSortedList(Comparator c) {
        this(c, null);
    }

    //构造方法(使用指定比较器和初始元素)
    public PQueueSortedList(Comparator c,Sequence s) {
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
    public Entry getMin() throws ExceptionPQueueEmpty {
        return (Entry) L.last();
    }

    @Override
    public Entry insert(Object key, Object obj) throws ExceptionKeyInvalid {
        EntryDefault entryDefault = new EntryDefault(key, obj);
        Position position;
        if (L.isEmpty() || 0 > C.compare(L.first().getEle(), key)) {
            position = L.insertFirst(entryDefault);
        }else {
            Position last = L.last();
            while (C.compare(last.getEle(), key) < 0){
                last = L.getPrev(last);
            }
            position = L.insertAfter(last, entryDefault);
        }
        return (Entry) position;
    }

    @Override
    public Entry delMin() throws ExceptionPQueueEmpty {
        return (Entry) L.remove(L.last());
    }
}
