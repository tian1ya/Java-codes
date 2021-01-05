package dateStructure;

public class Student {

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String name;
    public int age;

    @Override
    public boolean equals(Object obj) {
        Student other = (Student) obj;
        boolean s = this.name == other.name;
        boolean b = this.age == other.age;
        return s && b;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
