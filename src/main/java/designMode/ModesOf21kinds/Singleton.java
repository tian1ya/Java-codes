package designMode.ModesOf21kinds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Emperor {
    private static int maxNumOfEmperor = 5;

    private static List<String> nameList = new ArrayList<>();
    private static List<Emperor> emperors = new ArrayList<>();
    private static int countNumOfEmperor = 0;

    static {
        for (int i = 0; i < maxNumOfEmperor; i++) {
            emperors.add(new Emperor("皇帝:" + i));
        }
    }

    private static final Emperor emperor = new Emperor();

    private Emperor() {
    }

    private Emperor(String name) {
        nameList.add(name);
    }

    public static Emperor getInstance() {
        Random random = new Random();
        countNumOfEmperor = random.nextInt(maxNumOfEmperor);
        return emperors.get(countNumOfEmperor);
    }

    public void say() {
        System.out.println(countNumOfEmperor);
        System.out.println(nameList.get(countNumOfEmperor));
    }
}

class Singleton2 {
    private static Singleton2 singleton2 = null;

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        if (singleton2 == null) {
            singleton2 = new Singleton2();
        }
        return singleton2;
    }
}
public class Singleton {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Emperor instance = Emperor.getInstance();
            instance.say();
        }
    }
}

