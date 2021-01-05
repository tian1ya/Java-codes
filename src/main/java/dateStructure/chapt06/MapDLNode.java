package dateStructure.chapt06;

import dateStructure.chapt02.Position;
import dateStructure.chapt03.Iterator;
import dateStructure.chapt03.IteratorElement;
import dateStructure.chapt03.List;
import dateStructure.chapt03.ListDLNode;
import dateStructure.chapt05.Entry;
import dateStructure.chapt05.EntryDefault;

public class MapDLNode implements Map {
    private List L;
    private EqualityTester T;

    public MapDLNode(EqualityTester t) {
        L = new ListDLNode();
        T = t;
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
    public Object get(Object key) {
        Iterator positions = L.positions();
        while (positions.hasNext()) {
            Position position = (Position) positions.getNext();
            Entry ele = (EntryDefault) position.getEle();
            if (T.isEqualityTo(ele.getKey(), key))
                return ele.getValue();
        }
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        Iterator positions = L.positions();
        while (positions.hasNext()) {
            Position oldValue = (Position) positions.getNext();
            Entry ele = (Entry) oldValue.getEle();
            if (T.isEqualityTo(ele.getKey(), key)) {
                L.replace(oldValue, value);
                return ele;
            }
        }
        L.insertLast(new EntryDefault(key, value));
        return null;
    }

    @Override
    public Object remove(Object key) {
        Iterator positions = L.positions();
        while (positions.hasNext()) {
            Position next = (Position) positions.getNext();
            Entry ele = (Entry) next.getEle();
            if (T.isEqualityTo(ele.getKey(), key)){
                L.remove(next);
                return ele;
            }
        }
        return null;
    }

    @Override
    public Iterator entries() {
        return new IteratorElement(L);
    }
}
