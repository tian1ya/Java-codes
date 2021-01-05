package designMode.PrototypeMode;

import dateStructure.chapt03.List;

import java.util.ArrayList;

public class ShallowCloneMode implements Prototype{
    public ShallowCloneMode() {
    }

    public ShallowCloneMode(int age, String name, ArrayList<String> hobbies) {
        this.age = age;
        this.name = name;
        this.hobbies = hobbies;
    }

    private int age;
    private String name;
    private ArrayList<String> hobbies;

    public ArrayList getHobbies() {
        return hobbies;
    }

    public void setHobbies(ArrayList hobbies) {
        this.hobbies = hobbies;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ShallowCloneMode{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", hobbies=" + String.join(",",hobbies) +
                '}';
    }


    @Override
    public Prototype clone() {
        ShallowCloneMode mode = new ShallowCloneMode();
        mode.setAge(getAge());
        mode.setName(getName());
        mode.setHobbies(getHobbies());
        return mode;
    }
}
