package dateStructure.chapt07.bsTree;

import dateStructure.Student;
import dateStructure.chapt02.Assert;
import dateStructure.chapt03.Iterator;
import dateStructure.chapt05.Entry;
import dateStructure.chapt05.EntryDefault;

public class BSTreeTest {
    public static void main(String[] args) {
        sss();
    }

    public static void sss() {
        Student student = new Student("a", 12);
        Student student2 = new Student("b", 13);
        Student student3 = new Student("c", 14);
        BSTree bsTree = new BSTree();
        bsTree.insert(12, student);
        bsTree.insert(13, student2);
        bsTree.insert(14, student3);

        Entry entry = bsTree.find(12);
        Assert.assertEqual(entry.getKey(), 12);
        Assert.assertEqual(((Student)entry.getValue()).name, "a");

        Iterator entries = bsTree.entries();
        Assert.assertEqual(bsTree.getSize(), 3);

        while (entries.hasNext()) {
            EntryDefault next = ((EntryDefault)entries.getNext());
            System.out.print(next.getKey() + " => ");
            System.out.println(next.getValue());
        }

        System.out.println("===========");
        bsTree.remove(14);
        entries = bsTree.entries();
        Assert.assertEqual(bsTree.getSize(), 2);
        while (entries.hasNext()) {
            EntryDefault next = ((EntryDefault)entries.getNext());
            System.out.print(next.getKey() + " => ");
            System.out.println(next.getValue());
        }
    }
}
