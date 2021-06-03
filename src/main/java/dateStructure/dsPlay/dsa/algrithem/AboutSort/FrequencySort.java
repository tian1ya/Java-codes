package dateStructure.dsPlay.dsa.algrithem.AboutSort;


import java.util.*;

public class FrequencySort {

    static class KV implements Comparable<KV> {
        public String K;
        public Integer V;

        public KV(String k, Integer v) {
            K = k;
            V = v;
        }

        @Override
        public int compareTo(KV o) {
            return o.V - V;
        }
    }

    public static String frequencySort(String s) {
        /*
            1. s 转为 char -> count

            2. 对1步骤进行排序
                1. 转为 KV
            3. 组装输出 char*count + char*count + char*count
         */
        // 1. s 转为 char -> count
        HashMap<String, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(String.valueOf(c), map.getOrDefault(String.valueOf(c), 0) + 1);
        }

        // 2. 对1步骤进行排序
//        int index = 0;
//        KV[] kvs = new KV[map.size()];
        ArrayList<KV> kvs = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            kvs[index++] = new KV(entry.getKey(), entry.getValue());
            kvs.add(new KV(entry.getKey(), entry.getValue()));
        }

//        quickSort(kvs);
        Collections.sort(kvs);
        // 3. 组装输出 char*count + char*count + char*count

//        return Arrays.stream(kvs).reduce("", (String b, KV a) -> b + concatStr(a.K, a.V), (a, b) -> a + b);
        return kvs.stream().reduce("", (String b, KV a) -> b + concatStr(a.K, a.V), (a, b) -> a + b);
    }

    private static String concatStr(String str, int dupNum) {
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < dupNum; i++) {
            a.append(str);
        }
        return a.toString();
    }

    private static void quickSort(KV[] kvs) {
        quickSortInner(kvs, 0, kvs.length - 1);
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
            while (startIndex < endIndex && kvs[endIndex].compareTo(pivot) >= 0)
                endIndex--;

            kvs[startIndex] = kvs[endIndex];

            while (startIndex < endIndex && kvs[startIndex].compareTo(pivot) <= 0)
                startIndex++;

            kvs[endIndex] = kvs[startIndex];
        }

        kvs[startIndex] = pivot;
        return startIndex;
    }

    public static void main(String[] args) {
        System.out.println(frequencySort("tree"));
        System.out.println(frequencySort("cccaaa"));
        System.out.println(frequencySort("Aabb"));
    }


}
