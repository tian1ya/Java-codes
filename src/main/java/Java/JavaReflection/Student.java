package Java.JavaReflection;

public class Student {
    public static void sayHi(){
        System.out.println("I am a student.....");

        Person person = new Person();

//        System.out.println(Person.class.getDeclaredField("age"));
//                .getAnnotatedType().getType().getTypeName());
    }

    public static void main(String[] args) {
        sayHi();
    }
}
