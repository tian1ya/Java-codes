package Java.JavaReflection;

public class Person implements MyInterface , MyInterface2{

    private int id;
    private String name;
    private int age;

    public String desc;

    public Person(int id) {
        this.id = id;
    }

    private Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    private void privateMethod(){
        System.out.println("private method.....");
    }

    private void privateMethod2(Long nn){
        System.out.println("private method....." + nn);
    }


    public void interfaceMethod() {
        System.out.println("interfaceMethod.......");
    }

    public static void staticMethod(){
        System.out.println("static method");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void interfaceMethod2() {
        System.out.println("interfaceMethod.....222..");
    }
}
