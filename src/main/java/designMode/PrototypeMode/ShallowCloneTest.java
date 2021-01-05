package designMode.PrototypeMode;

import java.util.ArrayList;
import java.util.Arrays;

public class ShallowCloneTest {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        ShallowCloneMode hello = new ShallowCloneMode(12, "hello", strings);
        ShallowCloneMode prototype = (ShallowCloneMode)hello.clone();

        ArrayList<String> sss = new ArrayList<>();
        sss.add("a");
        sss.add("c");

//        hello.setHobbies(sss);
        System.out.println(hello.toString());
        System.out.println(prototype);

        System.out.println("=========================");
        System.out.println(hello.getAge() == prototype.getAge()); // true
        System.out.println(hello.getName() == prototype.getName());  // true
        System.out.println(hello.getHobbies() == prototype.getHobbies()); // false
    }
}
