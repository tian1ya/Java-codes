package dateStructure.chapt02.linkedList;

import dateStructure.Student;
import dateStructure.chapt02.Assert;

public class QueueListTest {
    public static void main(String[] args) {
        aaa();
    }

    public static void aaa() {
        QueueList queueList = new QueueList();
        queueList.enqueue(new Student("12",12));
        queueList.enqueue(new Student("13",13));
        queueList.enqueue(new Student("14",14));

        int size = queueList.size;
        Node head = queueList.head;
        Node tail = queueList.tail;

        Assert.assertEqual(size, 3);
        Assert.assertEqual(((Student)head.getEle()).age, 12);
        Assert.assertEqual(((Student)head.getEle()).name, "12");

        Assert.assertEqual(((Student)tail.getEle()).age, 14);
        Assert.assertEqual(((Student)tail.getEle()).name, "14");

        Student dequeue = (Student)queueList.dequeue();
        int size1 = queueList.size;
        Assert.assertEqual(((Student)dequeue).age, 12);
        Assert.assertEqual(((Student)dequeue).name, "12");

        Assert.assertEqual(size1, 2);
    }
}
