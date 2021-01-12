package dateStructure.dsPlay.leetcode;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.Arrays;
import java.util.Optional;

/*
    有一堆石头，每块石头的重量都是正整数。

    每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：

    如果 x == y，那么两块石头都会被完全粉碎；
    如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
    最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。

    输入：[2,7,4,1,8,1]
    输出：1
    解释：
    先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
    再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
    接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
    最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。

    解题：
        1. 大到小排序
        2. 取出前 2个元素，进入粉碎功能子函数，返回结果
        3. 返回结果加入到数组末尾
        4. 重复1步骤

    优化：
        1. 高效排序算法
        2. 递归实现

 */
public class LastStoneWeight {


    int lastStoneWeight(int[] nums, int startIndex, int endIndex) {
        if (startIndex == endIndex) return nums[startIndex];

        Arrays.sort(nums, startIndex, endIndex + 1);
        Optional<Integer> res = destroyStone(nums[endIndex], nums[endIndex - 1]);

        if (endIndex - startIndex == 2 && !res.isPresent()) return nums[startIndex];
        if (endIndex - startIndex == 1) return res.orElse(0);

        if (res.isPresent()) {
            endIndex--;
            nums[endIndex] = res.get();
        } else {
            endIndex = endIndex - 2;
        }
        return lastStoneWeight(nums, startIndex, endIndex);
    }

    private Optional<Integer> destroyStone(int a, int b) {
        if (a == b) return Optional.empty();
        return a > b ? Optional.of(a - b) : Optional.of(b - a);
    }

    public int lastStoneWeight(int[] stones) {
        if (stones.length == 2) {
            return Math.abs(stones[0] - stones[1]);
        }
        if (stones.length == 1) {
            return stones[0];
        }
        Arrays.sort(stones);
        if (stones[stones.length - 3] == 0) {
            return stones[stones.length - 1] - stones[stones.length - 2];
        }
        stones[stones.length - 1] = stones[stones.length - 1] - stones[stones.length - 2];
        stones[stones.length - 2] = 0;
        // 置0 之后，在下一轮排序之后，会将0放到前面，当出现0的时候，就会进入上面的判断条件，从而得到结果
        return lastStoneWeight(stones);
    }

    public static void main(String[] args) {
        LastStoneWeight weight = new LastStoneWeight();
//        int[] nums = {2, 7, 4, 1, 8, 1}; // 1
//        int[] nums = {1, 3}; // 2
//        int[] nums = {1}; // 1
//        int[] nums = {3, 7, 2}; // 2
        int[] nums = {10,4,2,10}; // 2

//        int res = weight.lastStoneWeight(nums, 0, nums.length - 1);
        System.out.println(weight.lastStoneWeight(nums));
    }
}
