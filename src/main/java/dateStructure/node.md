#### Java 集合框架
> Java 的集合大致分为Set、List、Queue、和Map 四种体系，其中Set表示无序不可重复的，
> List 代表有序、重复的集合；Map 表示具有映射关系的集合；Java5中又增加了Queue集合。

#### 集合和数组的区别
> 数组长度在初始化的时候就指定，只能保存定长的数据，而集合可以保存数量不确定的数据，同时保存具有映射
> 关系的数据
>
> 数组元素既可以是基本类型的值，也可以是对象。 集合中只能保存对象(实际上保存对象的引用变量)，
> 基本数据类型的变量要转换为包装类才能放到集合类中
>
#### Java 集合类之间的集成关系
> Java 的集合类主要有两个接口派生而出：Collection 和 Map

![avatar](./pic/collection.png) 

* ArrayList是最常用的List实现类，内部是通过数组实现的，它允许对元素进行快速随机访问。数组的缺点是每个元素之间不能有间隔，当数组大小不满足时需要增加存储能力，就要讲已经有数组的数据复制到新的存储空间中。当从ArrayList的中间位置插入或者删除元素时，需要对数组进行复制、移动、代价比较高。因此，它适合随机查找和遍历，不适合插入和删除。

* Vector与ArrayList一样，也是通过数组实现的，不同的是它支持线程的同步，即某一时刻只有一个线程能够写Vector，避免多线程同时写而引起的不一致性，但实现同步需要很高的花费，因此，访问它比访问ArrayList慢。

* LinkedList是用链表结构存储数据的，很适合数据的动态插入和删除，随机访问和遍历速度比较慢。另外，他还提供了List接口中没有定义的方法，专门用于操作表头和表尾元素，可以当作堆栈、队列和双向队列使用。

```java
/*
   AbstractCollection类中有两个重载方法
   java.util.AbstractCollection.toArray()
   java.util.AbstractCollection.toArray(T[])
   第二个在转化为数组的时候指定了类型。
   如下代码在转化的时候会出错： 
*/

List collection = new ArrayList();
collection.add("1");
collection.add("2");
collection.add("3");
collection.add("4");
String[] objects = (String[]) collection.toArray();
// ClassCastException

// 下面转化的时候就给出转化后的类型，结果既可以返回objects，也可以是 strings
String[] strings = new String[collection.size()];
String[] objects = (String[]) collection.toArray(strings);
```

在List 中有一个属性 modCount， 用于记录list 的操作次数，增删改查
在迭代的时候，iterator.next(), iterator.hasnext()等迭代的时候是不想允许
list 进行了修改，所以在每一次 .next 的时候都会去检查 modCount 这个值有没有发送变化
如果有发生变化那么久会抛出异常 ConcurrentModificationException,如下代码就会
```java
        List collection = new ArrayList();
        collection.add("1");
        collection.add("2");
        collection.add("3");
        collection.add("4");

        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            collection.remove(iterator.next());
            System.out.println(iterator.next());
        }
```

// 在查看源码class Itr implements Iterator<E>  的时候注意3个点
// 1. expectedModCount的初值为modCount
// 2. hasNext的判断条件为cursor!=size，就是当前迭代的位置不是数组的最大容量值就返回true
```java
public boolean hasNext() {
            return cursor != size();
        }
/*
这里在判断是否有下个元素的时候，只判断当前元素是否等于size() 不等于就返回 true, 那么在cursor 变化的时候，如果去操作 数组
那么就会将size 发生变化，可能会出现这种情况，当cursor 移到最后一个的时候，这个时候hasNext应该返回false，但是如果这个时候删除元素
那么size就减少，这个时候还会返回true，那么就会发生OutOfIndex 的问题
*/
// 3. next和remove操作之前都会先调用checkForComodification来检查expectedModCount和modCount是否相等如果没checkForComodification去检查expectedModCount与modCount相等，这个程序肯定会报ArrayIndexOutOfBoundsException
```

   从图中我们可以看出：

        1. List是一个接口，它继承与Collection接口，代表有序的队列。

        2. AbstractList是一个抽象类，它继承与AbstractCollection。AbstractList实现了List接口中除了size()、get(int location)之外的方法。

        3. AbstractSequentialList是一个抽象类，它继承与AbstrctList。AbstractSequentialList实现了“链表中，根据index索引值操作链表的全部方法”。

        4. ArrayList、LinkedList、Vector和Stack是List的四个实现类，其中Vector是基于JDK1.0，虽然实现了同步，但是效率低，已经不用了，Stack继承与Vector，所以不再赘述。

        5. LinkedList是个双向链表，它同样可以被当作栈、队列或双端队列来使用。
————————————————
版权声明：本文为CSDN博主「武哥聊编程」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/eson_15/article/details/51145788



* vector
vector 也是继承自AbstractList
class Vector<E>
    extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable
 
Vector由于使用了synchronized方法（线程安全）所以性能上比ArrayList要差


* stack
class Stack<E> extends Vector<E>
主要是实现了 push 和 pop 2个方法，同时这两个方法也是加了同步方法，性能也会差点

这里也是能够明白 stack 和 vector 由于加了同步锁所以是线程安全的

一般讨论集合类无非就是。这里的两种数组类型更是如此
    1底层数据结构： 维护一个数组
    2增删改查方式：
    3初始容量，扩容方式，扩容时机： 动态扩展
    4线程安全与否：vector， stack 线程安全，arrayList 不安全
    5是否允许空，是否允许重复，是否有序：不允许，会执行一个方法按时去掉空的位置
    
* LinkedList
public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable
    
AbstractSequentialList 只支持按次序访问，而不像 AbstractList 那样支持随机访问。

---

* Queue

> Map 实现类用于保存具有映射关系的数据

![avatar](./pic/map.png)