package dateStructure.dsPlay.dsa.graph.algorithems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/*
    2个水桶，一个装5升，一个装3升，
    怎么利用这2个水桶，得到4升水。

    主要的问题是：如何将一个问题转换为图论问题
    OpenLock 和 这个问题，都是抽象出状态

    注意抽象出代码注释中的那几个状态

    还有一个智能题：那就是农夫过河问题，也可以通过这样的抽象使用BFS 解决
 */
public class WaterPuzzle {

    private int[] pre;
    private int end = -1;

    public WaterPuzzle() {
        pre = new int[100];

        Queue<Integer> queue = new LinkedList<>();
        // 水桶的容量最多也就是个位数，
        boolean[] visited = new boolean[100];
        queue.add(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            Integer cur = queue.remove();
            // 十位
            int a = cur / 10;
            // 个位
            int b = cur % 10;
            /*
                a(十位)桶最大盛水5L，b(个位)最大的盛水3L
                2个桶的结果是 a*10 + b
             */

            ArrayList<Integer> nexts = new ArrayList<>();
            /*
                可以给第一个桶灌满水
             */
            // 可以给第一个桶灌满水
            nexts.add(5 * 10 + b);

            // 可以给第二特桶灌满水
            nexts.add(a * 10 + 3);

            //可以将第一个桶清空
            nexts.add(0 * 10 + b);

            //可以将第二个桶清空
            nexts.add(a * 10 + 0);

            /*
                a 桶中水倒进 b 桶中
                b 桶中水倒进 a 桶中
             */
            // a 桶中水倒进 b 桶中
            int x = Math.min(a, 3 - b);
            nexts.add((a - x) * 10 + b + x);

            // b 桶中水倒进 a 桶中
            int y = Math.min(b, 5 - a);
            nexts.add((a + y) * 10 + b - y);

            for (Integer next : nexts) {
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    pre[next] = cur;

                    if (next / 10 == 4 || next % 10 == 4) {
                        end = next;
                        return;
                    }
                }
            }
        }
    }

    public Iterable<Integer> result() {
        ArrayList<Integer> result = new ArrayList<>();
        if (end == -1)
            return result;
        int cur = end;
        while (cur != 0) {
            result.add(cur);
            cur = pre[cur];
        }
        result.add(0);
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        WaterPuzzle puzzle = new WaterPuzzle();
        Iterable<Integer> result = puzzle.result();

        for (Integer integer : result) {
            System.out.print(integer + ", ");
        }


    }
}
