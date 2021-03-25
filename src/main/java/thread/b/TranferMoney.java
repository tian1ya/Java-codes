package thread.b;

import java.util.Random;

public class TranferMoney {

    static Random random = new Random();

    static class Account {
        private int money;

        public Account(int money) {
            this.money = money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getMoney() {
            return money;
        }

        public void transfer(Account account, int amount) {
            synchronized (Account.class) {
                if (this.money >= money){
                    this.money = this.money - amount;
                    account.setMoney(account.getMoney() + amount);
                }
            }
        }
    }

    public static int randomAmount() {
        return random.nextInt(5) + 1;
    }

    public static void main(String[] args) throws InterruptedException {
        Account account1 = new Account(1000);
        Account account2 = new Account(1000);

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                account1.transfer(account2, randomAmount());
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                account2.transfer(account1, randomAmount());
            }
        });

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

        System.out.println("account1 " + account1.money);
        System.out.println("account2 " + account2.money);
        System.out.println("total " + (account1.money + account2.money));
    }
}
