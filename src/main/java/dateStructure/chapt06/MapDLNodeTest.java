package dateStructure.chapt06;

import dateStructure.Student;
import dateStructure.chapt02.Assert;
import dateStructure.chapt03.Iterator;
import dateStructure.chapt05.EntryDefault;

public class MapDLNodeTest {
    public static void main(String[] args) {
        aaa();
    }

    public static void aaa() {
        MapDLNode mapDLNode = new MapDLNode((a, b) -> a.equals(b));
        int size = mapDLNode.getSize();
        Assert.assertEqual(size, 0);

        mapDLNode.put(1, new Student("1",1));
        mapDLNode.put(2, new Student("2",2));
        mapDLNode.put(3, new Student("3",3));

        int size1 = mapDLNode.getSize();
        Assert.assertEqual(size1, 3);

        Student o = (Student)mapDLNode.get(3);
        Assert.assertEqual(o.name, "3");
        Assert.assertEqual(o.age, 3);

        Iterator entries = mapDLNode.entries();
        while (entries.hasNext()) {
            EntryDefault next = (EntryDefault) entries.getNext();
            Object key = next.getKey();
            Student value = (Student) next.getValue();
            System.out.print(key + " : ");
            System.out.print(value.name + " : ");
            System.out.println(value.age);
        }

        Object remove= mapDLNode.remove(3);
        EntryDefault next = (EntryDefault) remove;
        Student value = (Student) next.getValue();
        Assert.assertEqual(value.name, "3");
        Assert.assertEqual(value.age, 3);

        Assert.assertEqual(mapDLNode.getSize(), 2);


    }

}
