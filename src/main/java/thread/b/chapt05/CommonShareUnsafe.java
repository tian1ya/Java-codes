package thread.b.chapt05;

import java.util.ArrayList;
import java.util.Arrays;

public class CommonShareUnsafe {
    static class ThreadUnsafe {

        public static ArrayList<Integer> list = new ArrayList<>();

        public void method() {
            for (int i = 0; i < 10; i++) {
                method2(list);
                method3(list);
            }

        }

        public static void method2(ArrayList<Integer> list) {
            list.add(1);
            System.out.println((list));

        }

        public static void method3(ArrayList<Integer> list) {
            list.remove(0);
            System.out.println((list));

        }
    }
    public static void main(String[] args) {
        ThreadUnsafe threadUnsafe = new ThreadUnsafe();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                threadUnsafe.method();
            }).start();
        }

        System.out.println((threadUnsafe.list));

    }
}
