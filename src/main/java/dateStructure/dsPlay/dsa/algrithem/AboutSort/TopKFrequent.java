package dateStructure.dsPlay.dsa.algrithem.AboutSort;

import dateStructure.dsPlay.dsa.Array;

import java.util.*;
import java.util.stream.Collectors;

/*
    给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 */
public class TopKFrequent {

    static class KV implements Comparable<KV> {
        public KV(Integer k, Integer v) {
            K = k;
            V = v;
        }

        public Integer K;
        public Integer V;

        @Override
        public int compareTo(KV o) {
            return V - o.V;
        }
    }

    public static int[] topKFrequent(int[] nums, int k) {
        if (nums.length <= k) return nums;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<KV> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer K = entry.getKey();
            Integer V = entry.getValue();

            if (priorityQueue.size() < k) {
                priorityQueue.offer(new KV(K, V));
            } else if (V > priorityQueue.peek().V) {
                priorityQueue.poll();
                priorityQueue.offer(new KV(K, V));
            }
        }

        int[] ints = new int[k];
        int size = priorityQueue.size();
        for (int i = 0; i < size; i++) {
            ints[i] = priorityQueue.poll().K;
        }
        return ints;
    }

    public static int[] topKFrequentQuickSort(int[] nums, int k) {
        if (nums.length <= k) return nums;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        KV[] kvs = new KV[map.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            KV kv = new KV(entry.getKey(), entry.getValue());
            kvs[index] = kv;
            index++;
        }

        quickSort(kvs);
        int[] ints = new int[k];
        for (int i = 0; i < k; i++) {
            ints[i] = kvs[kvs.length - 1 - i].K;
        }
        return ints;
    }

    private static void quickSort(KV[] kvs) {
        int startIndex = 0;
        int endIndex = kvs.length - 1;
        quickSortInner(kvs, startIndex, endIndex);
    }

    private static void quickSortInner(KV[] kvs, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int midIndex = partition(kvs, startIndex, endIndex);
            quickSortInner(kvs, startIndex, midIndex - 1);
            quickSortInner(kvs, midIndex + 1, endIndex);
        }
    }

    private static int partition(KV[] kvs, int startIndex, int endIndex) {
        KV pivot = kvs[startIndex];
        while (startIndex < endIndex) {

            while (startIndex < endIndex && kvs[endIndex].compareTo(pivot) >= 0) {
                endIndex--;
            }

            kvs[startIndex] = kvs[endIndex];

            while (startIndex < endIndex && kvs[startIndex].compareTo(pivot) <= 0) {
                startIndex++;
            }

            kvs[endIndex] = kvs[startIndex];
        }

        kvs[startIndex] = pivot;
        return startIndex;
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println(Arrays.toString(topKFrequent(new int[]{4, 1, -1, 2, -1, 2, 3}, 2)));
        System.out.println(Arrays.toString(topKFrequentQuickSort(new int[]{4, 1, -1, 2, -1, 2, 3}, 2)));
    }
}
