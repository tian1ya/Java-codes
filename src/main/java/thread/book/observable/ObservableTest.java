package thread.book.observable;

import java.util.concurrent.TimeUnit;

public class ObservableTest {
    public static void main(String[] args) {
        ObservableThread<Object> observableThread = new ObservableThread<>(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("finished done");
            return null;
        });

        observableThread.start();
    }
}
