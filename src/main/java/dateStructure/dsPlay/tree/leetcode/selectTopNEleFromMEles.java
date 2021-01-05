package dateStructure.dsPlay.tree.leetcode;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/*
    在N个元素中选出前M 个元素，N >> M

    排序-> 取出前100，如使用好点的排序算法，其复杂度为 NlogN

    使用优先队列 NlogM

    给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

    输入:  nums = [1,1,1,2,2,3], k = 2
    输出: [1,2]

 */
public class selectTopNEleFromMEles {
    public int[] topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]))
                map.put(nums[i], map.get(nums[i]) + 1);
            else
                map.put(nums[i], 1);
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(map::get));

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();

            if (queue.size() < k) {
                queue.add(key);
            } else if (value > map.get(queue.peek())){
                queue.remove(); // removes the head of this queue
                queue.add(key);
            }
        }

        int[] ints = new int[k];
        for (int i = 0; i < k; i++) {
            if (!queue.isEmpty())
                ints[i] = queue.remove();
        }
        return ints;
    }

    public static void main(String[] args) {
        int[] ints = new selectTopNEleFromMEles().topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " => ");
        }
    }
}
