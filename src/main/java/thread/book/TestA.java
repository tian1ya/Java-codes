//package thread.book;
//
//import org.openjdk.jol.info.ClassLayout;
//
//public class TestA {
//
//    public static void main(String[] args) {
//        Object o = new Object();
//
//        System.out.println(ClassLayout.parseInstance(o).toPrintable());
//        // markword 标志位 00000001 表示无锁
//
//        synchronized (o) {
//            System.out.println(ClassLayout.parseInstance(o).toPrintable());
//            // markword 标志位 10000000 表示轻量锁
//        }
//    }
//}
