package dateStructure.chapt06.UpSortDict;

import dateStructure.chapt02.Position;
import dateStructure.chapt03.Iterator;
import dateStructure.chapt03.IteratorElement;
import dateStructure.chapt03.List;
import dateStructure.chapt03.ListDLNode;
import dateStructure.chapt05.Entry;
import dateStructure.chapt05.EntryDefault;
import dateStructure.chapt06.EqualityTester;

/*
     基于无序列表实现的词典结构非常适用于解决网络访问日志之类的应用问题，
     这类问题的共同特点是:插入操作频繁，查找、删除操作却极少进行

     另外一些问题则正好相反，它 们要求频繁地进行查询，但插入、
     删除操作相对更少，这方面的例子包括在线电话簿、订票系统等
 */
public class DictionaryDLNode implements Dictionary {
    private List L;
    private EqualityTester T;

    public DictionaryDLNode(EqualityTester t) {
        T = t;
        L = new ListDLNode();
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
    public Entry find(Object key) {
        Iterator positions = L.positions();
        while (positions.hasNext()) {
            Position next = (Position)positions.getNext();
            Entry ele = (Entry) next.getEle();
            if (T.isEqualityTo(ele.getKey(), key)){
                return ele;
            }
        }
        return null;
    }

    @Override
    public Iterator findAll(Object key) {
        Iterator positions = L.positions();
        ListDLNode list = new ListDLNode();
        while (positions.hasNext()) {
            Position next = (Position)positions.getNext();
            Entry ele = (Entry) next.getEle();
            if (T.isEqualityTo(ele.getKey(), key)){
                list.insertLast(ele);
            }
        }
        return new IteratorElement(list);
    }

    @Override
    public Entry insert(Object key, Object value) {
        EntryDefault entryDefault = new EntryDefault(key, value);
        L.insertFirst(entryDefault);
        return entryDefault;
    }

    @Override
    public Entry remove(Object key) {
        Iterator positions = L.positions();
        while (positions.hasNext()) {
            Position next = (Position)positions.getNext();
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
