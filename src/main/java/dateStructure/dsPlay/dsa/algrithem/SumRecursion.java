package dateStructure.dsPlay.dsa.algrithem;

public class SumRecursion {
    public int sum(int[] list) {
        return sum(list, 0,0);
    }

    // 对用户屏蔽
    private int sum(int[] list, int index, int result) {
        return list.length == index
                ? result
                : sum(list, index + 1, result + list[index]);
                /*
                    原始问题转为更小问题，更小问题，构建出原始问题

                    注意递归函数的宏观语义（这里就是 [1..n] 的和）

                    递归函数就是一个函数，完成一个功能。

                    每一次递归掉用，就认为是掉一个函数，这个函数完成宏观语义的一小部分

                    链表天然的递归性：每一步调用，将更小的链表作为入参
                 */
    }


    public static void main(String[] args) {
        int[] list = {1, 2, 3, 4, 5, 6, 7};
        int sum = new SumRecursion().sum(list);
        System.out.println(sum);
    }

}
