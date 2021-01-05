package dateStructure.chapt05;

public interface Entry {

    // 取条目码
    Object getKey();
    Object setKey(Object key);

    //取条目的数据对象
    Object getValue();
    Object setValue(Object value);
}
