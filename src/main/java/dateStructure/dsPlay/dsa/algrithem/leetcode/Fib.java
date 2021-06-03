package dateStructure.dsPlay.dsa.algrithem.leetcode;

public class Fib {

    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(1,1,n-2);
    }

    private int fib(int pre, int cur, int acc) {
        return acc == 0 ? cur : fib(cur,pre + cur, acc-1);
    }

    public static void main(String[] args) {
        Fib fib = new Fib();

        System.out.println(fib.fib(4));
        System.out.println(fib.fib(3));
        System.out.println(fib.fib(2));
        System.out.println(fib.fib(1));
        System.out.println(fib.fib(0));
    }
}
