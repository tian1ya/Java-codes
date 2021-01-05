package dateStructure.chapt02.Stack;


public class StackArrayTest {
    public static void main(String[] args) {
//        reverse_test();
        factorial(10);
    }

    public static long factorial(long n) {
        if (n <= 1)
            return 1;
        else
            return n * factorial(n -1);
    }

    public static void reverse_test() {
        Integer[] integers = {1, 2, 3, 4, 5, 6};
        Integer[] integersb = new Integer[integers.length];
        StackArray stackArray = new StackArray(integers.length);
        for (Integer integer : integers) {
            System.out.print(integer + " ");
            stackArray.push(integer);
        }

        for (int i = 0; i < integersb.length; i++) {
            integersb[i] = (Integer) stackArray.pop();
            System.out.print(integersb[i] + " ");
        }

    }
}
