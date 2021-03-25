package thread.b;

public class TwoPhaseInterrupt2 {

    public static void main(String[] args) throws InterruptedException {
        Terminator terminator = new Terminator();
        terminator.start();
        terminator.start();

        Thread.sleep(1500);
        terminator.stop();
    }

    private static class Terminator {
        private Thread monitor;
        private volatile boolean stop = false;


        public void start() {
            monitor = new Thread(() -> {
                while (true) {
                    Thread thread = Thread.currentThread();
                    if (stop) {
                        System.out.println("料理后事");
                        break;
                    }

                    try {
                        Thread.sleep(500);
                        System.out.println("继续监控");
                    } catch (InterruptedException e) {
                    }

                }
            });

            monitor.start();
        }

        public void stop() {
            stop = true;
            monitor.interrupt();
        }
    }
}
