package dateStructure.dsPlay.leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。

    返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。

    输入：[0,1,1]
    输出：[true,false,false]
    解释：
    输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此 answer[0] 为真。

    A = [0],        index = 0
    num = 0
    A = [0,1]       index = 1
    num = 1 = num << 1 + 1 = 1
    A = [0,1,0]     index = 2
    num = 2 = num << 1 + 0 = 2
    A = [0,1,0,1] index = 3
    num = 5 = num << 1 + 1 = 4 + 1 = 5
    规律： num = num << 1 + A[nextIndex]

    余数定理
        a = b%c%c
        (a+b)%c = ((a%c) + (b%c))%c
        ab%c = ((a%c) * (b%c))%c
    当num=3的时候， 也就是遇到 [0,1,1] => [0,1,1,1]
    num = 3 << 1 + 1 = 7  7 % 5 != 0

    ====> [0,1,1,1]
           num = 7 * 2 + 1 = 15 => 15 % 5 == 0

           (preNum * 2 + A[index]) % 5
         = ((preNum * 2)%5+ (A[index] % 5))%5
         = (((preNum)%5 * 2%5)%5 + (A[index] % 5))%5
         = ((preNum%5 * 2)%5 + (A[index] % 5))%5 preNum 替换为上一个
         =  (((7 * 2 + 1)%5)%5 * 2)%5 + (A[index] % 5))%5
         =  ((7 * 2 + 1) * 2)%5 + (A[index] % 5))%5 余数法则
         =  ((7 * 2 + 1) * 2 + A[index]) % 5
         =  (preNum * 2 + A[index]) % 5

         也不会影响下一次运算，最主要的是这样做了之后preNum会在[0,4]之间，不会再溢出了

 */
public class PrefixesDivBy5 {

    public List<Boolean> prefixesDivBy5V1(int[] A) {
        /*
            位数太长的时候，计算2的N次方，出现数据精度问题
         */
        ArrayList<Boolean> res = new ArrayList<>();

        for (int i = 0; i < A.length; i++) {
            int acc = 0;

            for (int j = i; j >= 0; j--) {
                int digital = A[j];
                acc += digital == 1 ? Math.pow(2, i - j) : 0;
            }
            res.add(acc % 5 == 0);
        }
        return res;
    }

    public List<Boolean> prefixesDivBy5V2(int[] A) {
        int num = 0;
        ArrayList<Boolean> res = new ArrayList<>();

        for (int i = 0; i < A.length; i++) {
            int digital = A[i];
            num = ((num * 2) + digital) % 5;
            res.add(num == 0);
        }

        return res;
    }

    public static void main(String[] args) {
        PrefixesDivBy5 div = new PrefixesDivBy5();
//        List<Boolean> list = div.prefixesDivBy5V2(new int[]{0, 1, 1});
//        List<Boolean> list = div.prefixesDivBy5V2(new int[]{1, 1, 1});
//        List<Boolean> list = div.prefixesDivBy5V2(new int[]{0,1,1,1,1,1});
//        List<Boolean> list = div.prefixesDivBy5V2(new int[]{1,1,1,0,1});
        List<Boolean> list = div.prefixesDivBy5V2(new int[]{1,1,0,0,0,1,0,0,1});

        System.out.println(Arrays.toString(list.toArray()));
    }
}
