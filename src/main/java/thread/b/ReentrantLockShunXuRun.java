package thread.b;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockShunXuRun {


    private int runFlag;
    private int loopNumber;

    private ReentrantLock lock;
    private List<Condition> conditions;
    private int uniqueStrNum;

    public ReentrantLockShunXuRun(int runFlag, int loopNumber, int uniqueStrNum) {
        this.lock = new ReentrantLock();
        this.loopNumber = loopNumber;
        this.uniqueStrNum = uniqueStrNum;
        this.runFlag = runFlag;

        conditions = new ArrayList<>();
        for (int i = 0; i < this.uniqueStrNum; i++) {
            conditions.add(this.lock.newCondition());
        }
    }

    public void print(String str, int waitFlag, int nextRunFlag) {
        for (int i = 0; i < loopNumber; i++) {
            lock.lock();
            try {

                while (runFlag != waitFlag) {
                    try {
                        this.conditions.get(waitFlag - 1).await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.print(str);
                this.runFlag = nextRunFlag;
                this.conditions.get(this.runFlag - 1).signal();

            } finally {
                lock.unlock();
            }

        }
    }

    public static void main(String[] args) {
//

        ReentrantLockShunXuRun lock = new ReentrantLockShunXuRun(1,5, 3);
        new Thread(() -> lock.print("a", 1, 2)).start();
        new Thread(() -> lock.print("b", 2, 3)).start();
        new Thread(() -> lock.print("c", 3, 1)).start();

    }
}
