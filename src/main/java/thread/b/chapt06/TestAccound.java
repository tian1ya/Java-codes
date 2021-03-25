package thread.b.chapt06;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

public class TestAccound {

    interface Account {
        Integer getBalance();
        void withdraw(Integer amount);

        static void demo(Account account) {
            ArrayList<Thread> ts = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                ts.add(new Thread(() -> {
                    account.withdraw(10);
                }));
            }

            long start = System.nanoTime();

            ts.forEach(Thread::start);
            ts.forEach(t -> {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            long end = System.nanoTime();
            System.out.println(account.getBalance() + " cost "
                    + (end - start)/1000_000 + " ms");

        }
    }

    static class AccountUnsafe implements Account{

        private Integer amount;

        public AccountUnsafe(Integer amount) {
            this.amount = amount;
        }

        @Override
        public synchronized Integer getBalance() {
            return amount;
        }

        @Override
        public synchronized void withdraw(Integer amount) {
            this.amount -= amount;
        }
    }

    static class AccountCas implements Account {

        private AtomicInteger balance;

        public AccountCas(Integer balance) {
            this.balance = new AtomicInteger(balance);
        }

        @Override
        public Integer getBalance() {
            return balance.get();
        }

        @Override
        public void withdraw(Integer amount) {
            balance.getAndAdd(amount * -1);
        }
    }

    interface DecimalAccount {
        BigDecimal getBalance();
        void withdraw(BigDecimal amount);

        static void demo(DecimalAccount account) {
            ArrayList<Thread> ts = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                ts.add(new Thread(() -> {
                    account.withdraw(BigDecimal.TEN);
                }));
            }

            long start = System.nanoTime();

            ts.forEach(Thread::start);
            ts.forEach(t -> {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            long end = System.nanoTime();
            System.out.println(account.getBalance() + " cost "
                    + (end - start)/1000_000 + " ms");

        }
    }

    static class DecimalAccountCas implements DecimalAccount {

        private AtomicReference<BigDecimal> balance;

        public DecimalAccountCas(BigDecimal balance) {
            this.balance = new AtomicReference<>(balance);
        }


        @Override
        public BigDecimal getBalance() {
            return balance.get();
        }

        @Override
        public void withdraw(BigDecimal amount) {
            while (true) {
                BigDecimal prev = balance.get();
                BigDecimal decimal = prev.subtract(amount);
                if (balance.compareAndSet(prev, decimal)) {
                    break;
                }
            }
        }
    }
    public static void main(String[] args) {


        // 没有添加 synchronized  300 cost 78 ms
        // 添加 synchronized 之后  0 cost 70 ms
        // 使用 AtomicInteger     0 cost 81 ms
        // 使用 AtomicReference    0 cost 60 ms
        DecimalAccount.demo(new DecimalAccountCas(new BigDecimal("10000")));

//        AccountCas accountUnsafe = new AccountCas(10000);
//        Account.demo(accountUnsafe);


//        AtomicInteger atomicInteger = new AtomicInteger(0);
//        System.out.println(atomicInteger.getAndIncrement()); // i++
//        System.out.println(atomicInteger.incrementAndGet()); // ++i
//        System.out.println(atomicInteger.addAndGet(10)); // += 10
//
//        System.out.println(atomicInteger.updateAndGet(i->i * 2));

//        AtomicReference<String> reference = new AtomicReference<>("A");
//        String s = reference.get();
//
//        boolean compareAndSet = reference.compareAndSet(s, "C");
//        System.out.println(compareAndSet + " " + reference.get());
        
    }
}
