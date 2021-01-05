package someOthers;

import java.util.EnumMap;
import java.util.EnumSet;

import static someOthers.Singnal.GREEN;
import static someOthers.Singnal.RED;

interface Behaviour {
    void print();
}

enum Singnal implements Behaviour{
    GREEN("绿色", 1L), YELLOW("黄色", 2L), RED("红色", 3L);

    private String name;
    private Long index;

    Singnal(String name, Long index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return this.index + this.name;
    }

    @Override
    public void print() {
        System.out.println(this.name);
    }
}

interface Food {
    enum Chinese implements Food {
        AA,BB,CC;
    }

    enum Western implements Food {
        AAa,BBb,CCc;
    }
}

enum Demo {
    FIRST{
        @Override
        public String getInfo() {
            return "first 的行为";
        }
    },

    SECOND{
        @Override
        public String getInfo() {
            return "second 的行为";
        }
    };

    public abstract String getInfo();

}

public class JavaEnum {

    public static void main(String[] args) {
        EnumSet.allOf(Singnal.class).stream()
                .forEach(e ->
                        System.out.println(e.name() + "->" + e.ordinal()));


        EnumMap<Singnal, String> enumMap = new EnumMap<Singnal, String>(Singnal.class);
        enumMap.put(RED, "红色");
        enumMap.put(GREEN, "绿色");
        enumMap.entrySet().stream().forEach(
                enumM -> System.out.println(enumM.getKey() + ":" + enumM.getValue())
        );

    }
}
