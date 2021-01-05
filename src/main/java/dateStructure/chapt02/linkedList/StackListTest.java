package dateStructure.chapt02.linkedList;


import dateStructure.Student;
import dateStructure.chapt02.Assert;

public class StackListTest {
    public static void main(String[] args) {
        aVoid();
    }

    public static void aVoid() {
        stackList stackList = new stackList();
        stackList.push(new Student("12", 12));
        stackList.push(new Student("13", 13));

        int size = stackList.getSize();
        Student top = (Student)stackList.top();

        Student pop = (Student) stackList.pop();
        int size1 = stackList.getSize();

        Assert.assertEqual(2, size);

        Assert.assertEqual(top.name, "13");
        Assert.assertEqual(top.age, 13);


        Assert.assertEqual(pop.age, 13);
        Assert.assertEqual(top.name, "13");

        Assert.assertEqual(((Student)stackList.top()).name, "12");
        Assert.assertEqual(((Student)stackList.top()).age, 12);

        Assert.assertEqual(size1, 1);

    }
}
