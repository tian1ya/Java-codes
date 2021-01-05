package dateStructure.chapt06.SortDict;

import dateStructure.chapt03.Iterator;
import dateStructure.chapt05.Entry;
import dateStructure.chapt06.UpSortDict.Dictionary;

/*
    只要在关键码之间定义有某一全序关系，我们就可以将词典中的条目组织为一个有序向量S

     基于数组的基础结构构建Map：
        为了保持有序查找表的完整性与一致性，每删除一个条目后，我们都需要
     将其后续的条目前移;在插入一个条目之前，我们都需要将其后续的条目后移。就最坏情况的复杂 度而言，此类更新操作均需要O(n)的时间。

     有序词典可以看作是无序词典的扩充，也就是说，只需在无序词典 ADT 的基
        础上再增加以下操作:
        first(), last(),
        successors(): 返回由关键码不小于 key(大于等于) 的条目依非降序组成的迭代器
        predecessors(): 返回由关键码不大于 key (小于等于) 的条目依非升序组成的迭代器
 */
public interface SortedDictionary extends Dictionary {
    Entry first();
    Entry last();

    Iterator successors(Object key);
    Iterator predecessors(Object key);
}
