package dateStructure.chapt02.Queue;

public class QueueArrayTest {
    public static void main(String[] args) {
        QueueArray queueArray = new QueueArray();
        for (int i = 0; i < 4; i++) {
            queueArray.enqueue(i);
        }
        queueArray.enqueue(23);
        System.out.println("d");
    }
}
