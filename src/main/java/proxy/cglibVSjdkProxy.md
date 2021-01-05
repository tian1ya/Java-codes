#### JDK和CGLIB动态代理区别
JDK动态代理
    利用拦截器(拦截器必须实现InvocationHanlder)加上反射机制生成一个实现代理接口的匿名类，
    在调用具体方法前调用InvokeHandler来处理。
    
CGLIB动态代理
    利用ASM开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。
    
何时使用JDK还是CGLIB？
    1) 如果目标对象实现了接口，默认情况下会采用JDK的动态代理实现AOP。
    2）如果目标对象实现了接口，可以强制使用CGLIB实现AOP。
    3）如果目标对象没有实现了接口，必须采用CGLIB库，Spring会自动在JDK动态代理和CGLIB之间转换。
    
JDK动态代理和CGLIB字节码生成的区别？
    1) JDK动态代理只能对实现了接口的类生成代理，而不能针对类。
    
    2）CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法，
        并覆盖其中方法实现增强，但是因为采用的是继承，所以该类或方法最好不要声明成final，
        对于final类或方法，是无法继承的。
        
Spring如何选择用JDK还是CGLIB？
    1）当Bean实现接口时，Spring就会用JDK的动态代理。
    2）当Bean没有实现接口时，Spring使用CGlib是实现。
    3）可以强制使用CGlib（在spring配置中加入<aop:aspectj-autoproxy proxy-target-class="true"/>）