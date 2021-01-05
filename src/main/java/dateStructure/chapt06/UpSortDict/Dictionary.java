package dateStructure.chapt06.UpSortDict;

import dateStructure.chapt03.Iterator;
import dateStructure.chapt05.Entry;

public interface Dictionary {
    int getSize();
    boolean isEmpty();
    Entry find(Object key);
    Iterator findAll(Object key);
    Entry insert(Object key, Object value);
    Entry remove(Object key);
    Iterator entries();
}
