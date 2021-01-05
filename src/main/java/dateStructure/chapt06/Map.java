package dateStructure.chapt06;

import dateStructure.chapt03.Iterator;

public interface Map {
    int getSize();
    boolean isEmpty();
    Object get(Object obj);
    Object put(Object key, Object value);
    Object remove(Object key);
    Iterator entries();
}
