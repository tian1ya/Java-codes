package offer.targetOffer.code.numberOf1Between1ToN;

public class NumberOf1Between1ToN {

    /*
case 1: cur=0
        2  3   0  4
         千位和百位可以选00 01 02....22  十位可以取到1( 形如[00|01..|22]1[0-9] 都是<2304 ) 个位可以选0-9  共有 23 * 10 中排列
         当千位和百位取23,如果十位取1 那就是形如 231[0-9] > 2304,所以当千位和百位取23，十位只能能取0，个位取0-4即 2300 2301 2302 2303 2304
         但是2301不应该算进来，这个1是 单独  出现在个位的（而11，121,111这种可以被算多次）
         即 23*10
case 2: cur=1
       2  3  1  4
       千位和百位可以选00 01 02....22  十位可以取到1 个位可以选0-9  共有 23 * 10 中排列
       当千位和百位取23,十位取1，个位可以去0-4 即 2310-2314共5个
       即 23 *10 + 4 +1
case 3: cur>1 即2-9
       2  3  2  4
       千位和百位可以选00 01 02....22  十位可以取到1(形如 [00|01...|22]1[0-9] 都是<2324) 个位可以选0-9  共有 23 * 10 中排列
       当千位和百位取23,十位取1，个位可以去0-9 即 2310-2319共10个 （其中2311，被计算了两次，分别是从个位和十位分析得到的1次）
       即 23 *10 + 10

     */

    public static int numbers(int n) {
        int digit = 1; // 以个位开始
        int res = 0;
        int high = n / 10;
        int cur = n % 10;    // 当前值，一直是从个位增加到最大位进行统计
        int low = 0;         // low 开始为 0，个位是 cur
        while (high != 0 || cur != 0) {
            if (cur == 0)
                res += high * digit;
            else if (cur == 1)
                res += high * digit + low + 1;
            else
                res += (high + 1) * digit;
            low += cur * digit; // 注意low 计算中的加法，加入上一次 low=1，digital=10，cur=1，那么cur * digital + low=11, 刚好就是低维的数值
            cur = high % 10;  // 原来基础上往前走一步，也就是上一次 high 的最后一步

            high /= 10;
            digit *= 10;
        }
        return res;
    }


    public static void main(String[] args) {
        test(11);
    }

    private static void test(int n) {
        System.out.println(numberOf1Between1ToN1(n));
    }

    /*
        暴力破解
     */
    private static int numberOf1Between1ToN1(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int temp = i;
            while (temp != 0) {
                if (temp % 10 == 1) {
                    count++;
                }
                temp = temp / 10;
            }
        }
        return count;
    }

    /*
        按照开头使用规律
     */

    private static int numberOf1Between1ToN2(int n) {
        int digital = 1;
        int high = n / 10;
        int low = 0;
        int current = n % 10;
        int count = 0;

        while (high != 0 || current != 0) {
            if (current == 0) count += high * digital;
            if (current == 1) count += high * digital + low + 1;
            if (current > 1) count += (high + 1) * digital;

            low = current * digital;
            current = high % 10;
            high = high / 10;

            digital *= 10;
        }

        return count;
    }
}
