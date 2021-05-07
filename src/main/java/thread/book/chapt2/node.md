Thread 中有一个参数 stackSize
    这个参数越大，那么久代表Java 递归调用中的深度就会越大，反之则越小，这个参数的设置是和系统息息相关的
    
    在写程序的时候，一般情况下，这个参数是无需手动给定的
    
JVM 内存结构:
    程序计数器: 存放当前线程下存放的将要执行的指令、分支。循环等，这个内存是只属于线程的，线程之间不共享
    
    虚拟机栈(栈内存)：在Java 运行期间创建的，只要是存储局部变量，操作栈，动态连接等，其大小可以通过 -xss(ThreadStackSize) 配置，这个内存是只属于线程的，线程之间不共享
    
    本地方法栈: 本地方法的接口(和本地交互的一些方法接口，如文件操作)，JNI(Java native interface)，线程在执行的时候经常会碰到调用 JNI 方法的情况，如网络通信，文件操作等
    
    堆内存：是JVM 中最大的一块内存区域，是所有线程贡献的，这里存储这运行期间创建的所有对象，也是GC 重点照顾的区域，
    
    方法区： 主要存储虚拟机加载的类信息，常量，静态变量等，
    
    Java8 还有一个元空间
    
    可以粗略的计算出一个Java 进程的内存大小：堆内存 + 线程数量*栈内存
    
    在一个进程中存创建多少个线程和 堆内存 与 栈内存 关系很大，二者是一个反比例的关系