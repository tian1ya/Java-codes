import dateStructure.chapt03.List;

import java.lang.reflect.Array;
import java.util.Arrays;

public class bubble {
    public static void main(String[] args) {

        java.util.List<Integer> list = Arrays.asList(1, 2, 3, 5, 4, 2, 7);

        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                if (list.get(i) < list.get(j)) {
                    int temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }

        list.forEach(i -> System.out.println(i));
    }

}
