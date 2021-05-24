package dateStructure.dsPlay.dsa.algrithem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DP {

    /*
        什么是动态规划:
     */

    public int fibV1(int n) {
        /*
            时间复杂度 O(2^N)
            这种写法会存在大量的重复计算每一次计算都会会到最初的起点，然后计算到终点
         */
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        return fibV1(n - 1) + fibV1(n - 2);
    }


    private List<Integer> list = new ArrayList<>();

    private int fobV22(int n) {
        for (int i = 0; i < n; i++) {
            list.add(-1); // 初始化只需要在后面的计算结果中没有出现
        }
        return fibV2(n);
    }

    public int fibV2(int n) {
        /*
            list 缓存结果，这样每次计算如果遇到之前计算结果
            那么久直接从缓存重获取，而不是重新计算， 只计算一次
         */
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (list.get(n) == 0) {
            return fibV2(n - 1) + fibV2(n - 2);
        } else
            return list.get(n);
    }

    public int fibV3(int n) {
        List<Integer> list = new ArrayList<>();
        list.set(0, 0);
        list.set(1, 1);
        for (int i = 2; i < n; i++) {
            list.set(i, list.get(i - 1) + list.get(i - 2));
        }
        return list.get(n);

    }

    /*
        记忆化搜索：自上而下的解决问题
        动态规划 自下而上的解决问题
           从小数据上解决问题，然后从小数据中推理到大数据中，从家而解决实际问题
           将原问题拆解问若干子问题，同时保存子问题的答案，使得每个子问题只求解一次
           最终获得原问题的答案

        递归问题 -> 重叠子问题 -> 动态规划(低向上的解决问题)

        与分治法最大的差别是：
            适合于用动态规划法求解的问题，经分解后得到的子问题往往不是互相独立的
            （即下一个子阶段的求解是建立在上一个子阶段的解的基础上，进行进一步的求解）

        适用的情况
            最优化原理：如果问题的最优解所包含的子问题的解也是最优的，就称该问题具有最优子结构，即满足最优化原理。
            无后效性：即某阶段状态一旦确定，就不受这个状态以后决策的影响。也就是说，
                    某状态以后的过程不会影响以前的状态，只与当前状态有关。
                    (推断出来的状态转移方程/矩阵)是只受到当前状态有关影响，而不会受到以前的状态
            有重叠子问题：即子问题之间是不独立的，一个子问题在下一阶段决策中可能被多次使用到。
                （该性质并不是动态规划适用的必要条件，但是如果没有这条性质，动态规划算法同其他算法相比就不具备优势）

        求解基本步骤:
            动态规划所处理的问题是一个多阶段决策问题，一般由初始状态开始，通过对中间阶段决策的选择，达到结束状态。
            1. 划分阶段：
                按照问题的时间或空间特征，把问题分为若干个阶段。在划分阶段时，
                注意划分后的阶段一定要是有序的或者是可排序的，否则问题就无法求解。

            2. 确定状态和状态变量
                 将问题发展到各个阶段时所处于的各种客观情况用不同的状态表示出来。当然，状态的选择要满足无后效性。
            3. 确定决策并写出状态转移方程
                状态转移就是根据上一阶段的状态和决策来导出本阶段的状态
            4. 寻找边界条件
                需要一个递推的终止条件或边界条件。

            一般，只要解决问题的阶段、状态和状态转移决策确定了，就可以写出状态转移方程（包括边界条件）。
     */

    public static void main(String[] args) {
        // 70，
//        System.out.println(leetCode70climbStairs(8));
//        System.out.println(leetCode70climbStairsV2(8));

        // 120
//        List<List<Integer>> list = new ArrayList<>();
//        ArrayList<Integer> list1 = new ArrayList<>();
//        list1.add(2);
//
//        ArrayList<Integer> list2 = new ArrayList<>();
//        list2.add(3);
//        list2.add(4);
//        ArrayList<Integer> list3 = new ArrayList<>();
//        list3.add(6);
//        list3.add(5);
//        list3.add(7);
//        list.add(list1);
//        list.add(list2);
//        list.add(list3);
//        ArrayList<Integer> list4 = new ArrayList<>();
//        list4.add(4);
//        list4.add(1);
//        list4.add(8);
//        list4.add(3);
//        list.add(list4);

//        ArrayList<Integer> list1 = new ArrayList<>();
//        list1.add(-1);
//
//        ArrayList<Integer> list2 = new ArrayList<>();
//        list2.add(-2);
//        list2.add(-3);
//
//        list.add(list1);
//        list.add(list2);

//        System.out.println(minimumTotal(list));
//        System.out.println(minimumTotalV2(list));

        // 64

//        int[][] grids = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
//        int[][] grids = {{1,2,3}, {4,5,6}};
//        System.out.println(minPathSum(grids));

        // 343
        /*
            1. 递归结构
            2. 自底向上 + 记忆化搜索 解决
            3. 自底向上DP 解决
         */
//        System.out.println(integerBreak(10));
//        System.out.println(integerBreakDp(10));
//        System.out.println(integerBreakDp2(10));

        // 279
//        System.out.println(numSquares(12));
//        System.out.println(numSquaresDp(12));
//
//        System.out.println(numSquares(13));
//        System.out.println(numSquaresDp(2));

        // 91、
//        System.out.println(numDecodings("12"));

        /*
            62I,
            一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
            机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
            问总共有多少条不同的路径？
         */
//        System.out.println(uniquePaths(3, 7));
//        System.out.println(uniquePaths(3, 2));
//        System.out.println(uniquePaths(3, 3));

        // 62II、
//        int[][] ints = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
//        int[][] ints = {{0, 0}, {1, 1}, {0, 0}};
//        int[][] ints = {{0, 1}, {0, 0}};
//        System.out.println(uniquePathsWithObstacles(ints));


        // 62III 这些题还没有完成


        /*
         198 打家劫舍 问题
            你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
            如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
            给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，
            一夜之内能够偷窃到的最高金额。
         */
//        int[] ints22 = {1, 2};
//        int[] ints2 = {2, 7, 9, 3, 1};
//        System.out.println(rob(ints2));
//        System.out.println(robOpt(ints2));
//
//        System.out.println(rob(ints2));
//        System.out.println(robOpt(ints2));

        /*
            213 打家劫舍II
            你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，
            这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
         */
//        int[] nums = new int[]{2, 3, 2}; // 3
//        int[] nums = new int[]{1,2,3,1}; // 4
//        int[] nums = new int[]{2,7,9,3,1}; // 11
//        int[] nums = new int[]{1,1,1,2}; // 3
//        int[] nums = new int[]{1}; // 1
//        int[] nums = new int[]{0,0}; // 0
//        System.out.println(robII(nums));

        // 0-1 背包问题(DP_note 中有一些笔记)
        // knapSack012
        System.out.println(knapSack01(new int[]{2,3,4,5}, new int[]{3,4,5,6}, 8));
        System.out.println(knapSack012(new int[]{2,3,4,5}, new int[]{3,4,5,6}, 8));

        /*
            416
            典型的背包问题，在n 个物品中选出一定的物品，填满 sum/2 的背包
                1. F(n,c) 考虑将 n 个物品填满容量为C 的背包
                2. F(i，c) = F(i-1, c) || F(i-1, c-w(i))
         */

//        int[] ints = {1, 5, 11, 5};
//        System.out.println(canPartition(ints));

        // 322、377、474、137、

        // 300 最长上升子序列
//        System.out.println(lengthOfLIS(new int[]{1,2,3}));

        // 376

        // 最长公共子序列

        // 42
//        int[] add = new int[]{-2,1,-3,4,-1,2,1,-5,4};
//        int[] add = new int[]{-1, -2};
//        int[] add = new int[]{-1};
//        System.out.println(maxSubArray(add));
//        System.out.println(maxSubArrayOpt(add));

        // 53
//        int[] add = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        int[] add = new int[]{-1, -2};
////        int[] add = new int[]{-1};
//        System.out.println(maxSubArray(add));
//        System.out.println(maxSubArray2(add));

        // 764
//        int[] cost = {10, 15, 20};
//        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
//        System.out.println(minCostClimbingStairs(cost));

//        int[] prices = {7, 1, 5, 3, 6, 4};
//        int[] prices = {7,6,4,3,1};
//        System.out.println(maxProfitV2(prices));

//        System.out.println(waysToStep(5));
    }

    public static int waysToStep(int n) {
        /*
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3]
         */
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;

        int one = 1;
        int two = 2;
        int three = 4;

        int res = 0;
        for (int i = 4; i <= n; i++) {
            res = ((three + two) % 1000000007 + one) % 1000000007;
            one = two;
            two = three;
            three = res;
        }

        return res;
    }

    public static int maxProfitV2(int[] prices) {
        /*
            dp[i][0]: 第 i 天不持股票而手上的现金数，那么可以是前天有股票，今天卖了，或者是，昨天没买入，今天什么也没做
            dp[i][1]: 第 i 天持股票而手上的现金数，那么可以是前天没有股票，今天买入，或者是，昨天买入，今天什么也没做
         */
        int size = prices.length;
        int[][] dp = new int[size][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0]; // 先买入，那么自己手里的钱就是负钱

        for (int i = 1; i < size; i++) {
            // 第 i 天手里没有股票，那么可以是前一天没有，那么今天还没有，或者昨天有，今天卖了，所以手里的钱就是 + prices[i]
            dp[i][0] = Math.max(dp[i - 1][0], prices[i] + dp[i - 1][1]);

            // 第 i 天手里有股票，那么可以是前一天有，今天还有，或者是昨天没有，今天买入了 - prices[i]。
            dp[i][1] = Math.max(-prices[i], dp[i - 1][1]);
        }

        return dp[size - 1][0];
    }

    public static int maxProfit(int[] prices) {
        // 暴力破解，超出时间
//        int res = Integer.MIN_VALUE;
//        for (int i = 0; i < prices.length; i++) {
//            for (int j = i; j < prices.length; j++) {
//                res = Math.max(res, prices[j] - prices[i]);
//            }
//        }
//        return res;

        int minValue = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minValue) {
                minValue = prices[i];
            } else if (prices[i] - minValue > maxProfit) {
                maxProfit = prices[i] - minValue;
            }
        }
        return maxProfit;
    }

    public static int minCostClimbingStairs(int[] cost) {
        // dp[i] 代表在第i个台阶的时候最大的消耗
        int[] dp = new int[cost.length];
        // dp[i] = num[i] + max(dp[i-1],dp[i-2])
        dp[0] = cost[0];  // 第一个台阶也是可以一次性跳上去的，所以这里最优可以选择 cost[0]
        dp[1] = cost[1]; // 第二个台阶，可以一次性跳上去的，所以这里最优可以去 cost【1】

        for (int i = 2; i < cost.length; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }

    public static int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int curr = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            curr = Math.max(temp, curr + temp);
            res = Math.max(res, curr);
        }
        return res;
    }

    public static int[] maxSubArrayII(int[] nums) {

        int curr = nums[0];
        int res = nums[0];
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            curr = Math.max(nums[i], nums[i] + curr);
            if (curr > res) {
                index = i;
                res = curr;
            }
        }

        ArrayList<Integer> subArr = new ArrayList<>();
        int temp = 0;
        for (int i = index; i >= 0; i--) {
            int num = nums[i];
            temp = res - num;
            if (temp >= 0) {
                subArr.add(num);
                res = temp;
                if (temp == 0) break;
            }
        }

        int[] ints = new int[subArr.size()];
        for (int i = subArr.size() - 1; i >= 0; i--) {
            ints[subArr.size() - 1 - i] = subArr.get(i);
        }
        return ints;
    }

    public static int maxSubArrayOpt(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1)
            return nums[0];

        int curr = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curr = Math.max(nums[i], nums[i] + curr);
            res = Math.max(res, curr);
        }

        return res;
    }

    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 如果之前的结果比较小，那么最大和就是现在了，
            // 否则就是当前值和之前值的和
            // 这里假设的 dp[i] 是 是以 num[i] 为结尾的 subArr 的和
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
        }

        int res = Integer.MIN_VALUE;
        for (int i : dp) {
            res = Math.max(res, i);
        }
        return res;
    }

    public static int lengthOfLIS(int[] nums) {
        // LIS[i] 以第i个数字为结尾的最长上升子序列的长度
        // LIS[i] => LIS[i] = LIS[i]+1 if LIS[i] > LIS[j] (j = 0:i-1)
        if (nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    nums[i] = Math.max(nums[i], nums[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0) {
            return false;
        }

        int[][] dp = new int[nums.length][sum / 2];

        // -1 表示未计算，0表示不可以填充，1表示可以填充
        for (int i = 0; i < dp.length; i++) {
            int[] ints1 = new int[sum / 2];
            Arrays.fill(ints1, -1);
            dp[i] = ints1;
        }

        return tryPartition(nums, nums.length - 1, sum / 2, dp);
    }

    private static boolean tryPartition(int[] nums, int index, int targetSum, int[][] dp) {
        if (targetSum == 0) {
            // 背包已经被填满
            return true;
        }

        if (targetSum < 0 || index < 0) {
            // 背包中不能被平均填充
            return false;
        }

        if (dp[index][targetSum - 1] != -1) {
            // 不能填充，那么超看是否已经填充
            return dp[index][targetSum - 1] == 1;
        }
        boolean res = tryPartition(nums, index - 1, targetSum, dp) || tryPartition(nums, index - 1, targetSum - nums[index], dp);
        dp[index][targetSum - 1] = res ? 1 : 0;

        return dp[index][targetSum - 1] == 1;
    }

    public static boolean canPartitionV2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0) {
            return false;
        }

        int n = nums.length;
        int c = sum / 2;

        boolean[] dp = new boolean[c + 1];

        for (int i = 0; i <= c; i++) {
            dp[i] = nums[0] == i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = c; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }

        return dp[c];
    }

    public static int knapSack012(int[] w, int[] v, int c) {
        int length = w.length;
        int[][] dp = new int[length][c + 1];
        /*
            dp[i][c] 在当前背包容量 C，前i个物品的最佳组合所得到的最大价值
         */

        if (length == 0) return 0;

        for (int i = 0; i <= c; i++) {
            dp[0][i] = i >= w[0] ? v[0] : 0;
        }

        for (int i = 1; i < length; i++) {
            for (int j = 0; j <= c; j++) {
                dp[i][j] = dp[i - 1][j];
                // 上一次获取的最大价值
                if (j >= w[i]) {
                    /*
                        j 当前容量
                        w[i] 当前物品重量
                        这次回去的最大价值 = 这次物品价值 + 还可以放的下容量
                        注意这里采用的方法是倒推的方式，当前重量小于可容纳的，那么放入当前的物品。然后再放空出来的空间给剩余物品
                    */
                    dp[i][j] = Math.max(dp[i][j], v[i] + dp[i - 1][j - w[i]]);
                }
            }
        }
        return dp[length - 1][c];
    }

    /*
        考虑第 [公式] 个物品，无外乎两种可能：选，或者不选。

        F(i, c) = F(i-1, c) ：不选的话，背包的容量不变
                = F(i-1, c-w(i)) + v(i) ：选的话，背包的容量变小，价值增加
                => max(F(i-1, c), F(i-1, c-w(i)) + v(i))
     */
    public static void printA(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + ",");
            }
            System.out.println();
        }
    }
    public static int knapSack01(int[] w, int[] v, int c) {
        int length = w.length;

        int[][] dp = new int[length][c + 1];
        // 第 i 个 物品放到第 c 个重量中

        return bestValue(w, v, length - 1, c, dp);
    }

    private static int bestValue(int[] w, int[] v, int index, int c, int[][] dp) {
        if (index < 0 || c <= 0) {
            return 0;
        }

        if (dp[index][c] != 0) {
            return dp[index][c];
        }
        // index-1 填充 c 获得的结果
        int res = bestValue(w, v, index - 1, c, dp);
        if (c >= w[index]) {
            int i = v[index] + bestValue(w, v, index - 1, c - w[index], dp);
            res = Math.max(res, i);
        }
        dp[index][c] = res;
        return res;
    }
    /*
        空间优化
        knapSack01 其中我们在计算下一次的时候，值需要上一次的那行 dp 就可以了，而不需要保留所有 dp
     */


    public static int robII(int[] nums) {
        /*
            环状排列意味着第一个房子和最后一个房子中只能选择一个偷窃，
            因此可以把此环状排列房间问题约化为两个单排排列房间子问题：

                在不偷窃第一个房子的情况下（即 nums[1:]），最大金额是 p1
                在不偷窃最后一个房子的情况下（即 nums[:n-1]），最大金额是 p2

            综合偷窃最大金额： 为以上两种情况的较大值，即 max(p1,p2)。
         */
        if (nums.length == 1) return nums[0];
        int rob = robbIIInner(Arrays.copyOfRange(nums, 0, nums.length - 1));
        int rob2 = robbIIInner(Arrays.copyOfRange(nums, 1, nums.length));
        return Math.max(rob, rob2);
    }

    public static int robbIIInner(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        // dp[n] = Math.max(dp[n-1], dp[n-2] + nums[i])

        int pre = 0, cur = 0, temp;
        for (int num : nums) {
            temp = cur;
            cur = Math.max(cur, pre + num);
            pre = temp;
        }
        return cur;
    }

    public static int uniquePaths(int m, int n) {
        if (m == 1 && n == 1) {
            return 1;
        }

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];

    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        if (obstacleGrid.length == 1)
            return 1;

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];
        /*
            注意在初始化的道路上，如果一但遇到1，那么就初始化结果
            因为一但遇到1，那么后续的路就走不到了的，所以值应该为0，而不是初始话为1
         */
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1)
                break;
            dp[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1)
                break;
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static int numDecodings(String s) {
        // 这个题和 70 号题很相似
        int len = s.length();
        if (len == 0) {
            return 0;
        }

        char[] charsArray = s.toCharArray();
        if (charsArray[0] == '0') {
            return 0;
        }

        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            if (charsArray[i] != '0') {
                dp[i] = dp[i - 1];
            }

            char currentChar = charsArray[i];
            char prevChar = charsArray[i - 1];
            int num = 10 * (prevChar - '0') + (currentChar - '0');

            if (num >= 10 && num <= 26) {
                if (i == 1)
                    dp[i] += dp[0];
                else
                    dp[i] += dp[i - 2];
            }
        }
        return dp[len - 1];
    }

    public static int numSquares(int n) {
        // 58 的时候超出时间限制
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i <= Math.sqrt(n) + 1; i++) {
            list.add(i * i);
        }

        return numSquareInner(n, list);
    }

    private static int numSquareInner(int n, ArrayList<Integer> list) {
        if (list.contains(n)) {
            return 1;
        }


        int res = Integer.MAX_VALUE;
        for (Integer integer : list) {
            if (integer < n) {
                res = Math.min(res, numSquareInner(n - integer, list) + 1);
            }
        }
        return res;
    }

    // dp[i] = MIN(dp[i], dp[i - j * j] + 1)
    public static int numSquaresDp(int n) {
        // https://leetcode-cn.com/problems/perfect-squares/solution/wan-quan-ping-fang-shu-by-leetcode/
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        int max_square_index = (int) Math.sqrt(n) + 1;
        int[] square_dim = new int[max_square_index];

        for (int i = 1; i < max_square_index; i++) {
            square_dim[i] = i * i;
        }


        for (int i = 1; i <= n; i++) {
            for (int s = 1; s < max_square_index; ++s) {
                if (i < square_dim[s])
                    break;
                dp[i] = Math.min(dp[i], dp[i - square_dim[s]] + 1);
            }
        }
        return dp[n];
    }

    /*
        1. 定义子问题：
            子问题是和原问题相似，但规模较小的问题。例如这道小偷问题，原问题是“从全部房子中能偷到的最大金额”，
            将问题的规模缩小，子问题就是“从 kk 个房子中能偷到的最大金额”

            定义的子问题中有参数 kk。假设一共有 nn 个房子的话，就一共有 nn 个子问题。
            动态规划实际上就是通过求这一堆子问题的解，来求出原问题的解。这要求子问题需要具备两个性质：
                原问题要能由子问题表示。例如这道小偷问题中，k=nk=n 时实际上就是原问题
                一个子问题的解要能通过其他子问题的解求出。f(k) 可以由 f(k-1)f(k−1) 和 f(k-2)f(k−2) 求出(这个性质就是教科书中所说的“最优子结构”)


        2. 写出子问题的递推关系(最关键的一步)
            分析一下这道小偷问题的递推关系：
            https://leetcode-cn.com/problems/house-robber/solution/dong-tai-gui-hua-jie-ti-si-bu-zou-xiang-jie-cjavap/
            f(k)=max{f(k−1),H(k−1)+f(k−2)}

        3. 确定DP 数组的计算顺序
            动态规划有两种计算顺序，
                一种是自顶向下的、使用备忘录的递归方法，
                一种是自底向上的、使用 dp 数组的循环方法。
            不过在普通的动态规划题目中，99% 的情况我们都不需要用到备忘录方法，所以我们最好坚持用自底向上的 dp 数组。

            DP 数组也可以叫”子问题数组”，因为 DP 数组中的每一个元素都对应一个子问题
            dp[k] 对应子问题 f(k)f(k)，即偷前 kk 间房子的最大金额。

        4. 空间优化(可选)
            空间优化的基本原理是，很多时候我们并不需要始终持有全部的 DP 数组。
            对于小偷问题，我们发现，最后一步计算 f(n)f(n) 的时候，实际上只用到了
            f(n-1)f(n−1) 和 f(n-2)f(n−2) 的结果。n-3n−3 之前的子问题，实际上早就已经用不到了。
            那么，我们可以只用两个变量保存两个子问题的结果
     */
    public static int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // 只有一间房子
        if (nums.length == 1) {
            return nums[0];
        }

        // 只有2间房子
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        // 多间房子, dp[i] 表示前 i 间房子能偷窃到的最高金额
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    public static int robOpt(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // 只有一间房子
        if (nums.length == 1) {
            return nums[0];
        }

        // 只有2间房子
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        // 多间房子, dp[i] 表示前 i 间房子能偷窃到的最高金额
        int[] dp = new int[3];
        int prev = nums[0];
        int curr = Math.max(nums[0], nums[1]);

        for (int ij = 2; ij < nums.length; ij++) {
            int temp;
            if (curr < prev + nums[ij]) {
                temp = prev + nums[ij];
            } else {
                temp = curr;
            }
            prev = curr;
            curr = temp;

        }
        return curr;
    }

    public static int integerBreak(int n) {
        return integerBreakCurr(n);
    }

    private static int integerBreakCurr(int n) {
        // 会有大量的重复计算
        if (n == 1) return n;
        int res = -1;
        for (int i = 1; i < n; i++) {
            res = max3(res, i * (n - i), i * integerBreakCurr(n - i));
        }

        return res;
    }

    public static int integerBreakDp(int n) {
        int[] memo = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }
        return integerBreakCurrDp(n, memo);
    }

    private static int integerBreakCurrDp(int n, int[] memo) {
        // memo 记录重复，自顶向下，记忆化搜索
        if (n == 1) return n;
        if (memo[n] != -1) {
            return memo[n];
        }

        int res = -1;
        for (int i = 1; i < n; i++) {
            res = max3(res, i * (n - i), i * integerBreakCurr(n - i));
        }
        memo[n] = res;

        return res;
    }

    public static int integerBreakDp2(int n) {
        int[] memo = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            memo[i] = -1;
        }

        return integerBreakInnerDp2(n, memo);
    }

    private static int integerBreakInnerDp2(int n, int[] memo) {
        if (n == 1) return 1;
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i - 1; j++) {
                memo[i] = max3(memo[i], j * (i - j), j * memo[i - j]);
            }
        }
        return memo[n];
    }

    private static int max3(int res, int i, int i1) {
        return Math.max(res, Math.max(i, i1));
    }

    public static int minPathSum(int[][] grid) {
        /*
            三种情况
            1. 第一行
            2. 第一列
            3. 非第一行第一列元素
         */
        int length = grid.length;
        int high = grid[0].length;

        int[][] dp = new int[length][high];

        dp[0][0] = grid[0][0];
        for (int i = 1; i < high; i++) {
            // 处理第一行
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        for (int j = 1; j < length; j++) {
            // 处理第一列
            dp[j][0] = dp[j - 1][0] + grid[j][0];
        }

        for (int i = 1; i < length; i++) {
            for (int j = 1; j < high; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[length - 1][high - 1];
    }


    public static int minimumTotal(List<List<Integer>> triangle) {
        /*
            时间复杂度O(N^2)
            空间复杂度O(N^2) dp 浪费一半的空间
            到了下一层，考虑上一次来源
         */
        int size = triangle.size();
        int[][] dp = new int[size][size];

        dp[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < size; i++) {
            // 处理每层第一个元素,特殊情况，第一个元素，只能是上一行第一个元素和当前元素相加
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
            }
            // 最左边元素，等于上册最嘴边元素和当前元素加
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            res = Math.min(dp[size - 1][i], res);
        }

        return res;
    }

    public static int minimumTotalV2(List<List<Integer>> triangle) {
        /*
            动态规划 + 空间优化
            使用一维数据存储结果，每一次都更新这个一维数据


         */
        int size = triangle.size();
        int[] dp = new int[size];
        dp[0] = triangle.get(0).get(0);

        for (int i = 1; i < size; i++) {
            dp[i] = dp[i - 1] + triangle.get(i).get(i);

            for (int j = i - 1; j > 0; j--) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + triangle.get(i).get(j);
            }
            dp[0] = dp[0] + triangle.get(i).get(0);
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            res = Math.min(res, dp[i]);
        }
        return res;
    }

    public static int leetCode70climbStairs(int n) {
        // 第n个台阶只能从第n-1或者n-2个上来。到第n-1个台阶的走法 + 第n-2个台阶的走法 = 到第n个台阶的走法
        if (n == 0) return 1;
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    public static int leetCode70climbStairsV2(int n) {
        return leetCode71climbStairsV2Inner(n, 2, 1);
    }

    public static int leetCode71climbStairsV2Inner(int n, int acc, int prev) {
        if (n == 1 || n == 0) return 1;
        if (n == 2) return acc;

        return leetCode71climbStairsV2Inner(n - 1, acc + prev, acc);
    }
}
