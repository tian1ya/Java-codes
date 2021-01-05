package dateStructure.chapt02.Queue;

public class Josephus {

    public static Object Josephus(QueueArray queue, int k) {
        if (queue.isEmpty())
            return null;
        while (queue.getSize() > 1) {
            queue.traversal();

            for (int i = 0; i < k; i++) {
                queue.enqueue(queue.dequeue());
            }
            Object e = queue.dequeue();
            System.out.println(e + " 退出");
        }

        return queue.dequeue();
    }

    public static QueueArray buildQueue(Object a[]) {
        QueueArray q = new QueueArray();
        for (int i = 0; i < a.length; i++) {
            q.enqueue(a[i]);
        }
        return q;
    }

    public static void main(String[] args) {
        String[] kid = {"Alice", "Bob", "Cindy", "Doug", "Ed",
                "Fred", "Gene", "Hope", "Irene", "Jack",
                "Kim", "Lance", "Mike", "Nancy", "Ollie"};
        System.out.println("最终的幸运者是" + Josephus(buildQueue(kid), 5));
    }
}
