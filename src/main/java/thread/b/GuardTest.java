package thread.b;

import TDD.TicTocToe.TicTacToe;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GuardTest {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new People().start();
        }

        Thread.sleep(100);

        for (Integer integer : MailBox.getId()) {
            new Postman(integer, "内容 " + integer).start();
        }
    }

    static class GuardObject{

        private int id;

        public GuardObject(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        private Object response;

        public Object get(long timeout) {
            synchronized (this) {
                long begin = System.currentTimeMillis();
                long passedTime = 0;

                while (response == null) {
                    long waitTime = timeout - passedTime;
                    if (waitTime <= 0) {
                        break;
                    }

                    try {
                        this.wait(waitTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    passedTime = System.currentTimeMillis() - begin;
                }

                return response;
            }
        }

        public void complete(String mail) {
            synchronized (this) {
                this.response = mail;
                this.notifyAll();
            }
        }
    }

    static class MailBox {
        private static HashMap<Integer, GuardObject> box = new HashMap<Integer, GuardObject>();

        private static int id = 1;
        public static synchronized int generateId() {
            return id++;
        }

        public static GuardObject createGuardObj() {
            GuardObject object = new GuardObject(generateId());
            box.put(object.id, object);
            return object;
        }

        public static Set<Integer> getId() {
            return box.keySet();
        }

        public static GuardObject getObj(int id) {
            return box.remove(id);
        }
    }

    static class People extends Thread {
        @Override
        public void run() {
            GuardObject guardObject = MailBox.createGuardObj();
            System.out.println("收信前 People: " + guardObject.id);
            Object o = guardObject.get(5000);
            System.out.println("收到新 People: " + guardObject.id + "信内容：" + o);
        }
    }

    static class Postman extends Thread {
        private int id;
        private String mail;

        public Postman(int id, String mail) {
            this.id = id;
            this.mail = mail;
        }

        @Override
        public void run() {
            GuardObject obj = MailBox.getObj(this.id);
            System.out.println("Postman 送信：" + id + " 内容是： " + mail);
            obj.complete(this.mail);
        }
    }
}
