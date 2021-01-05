package thread.chapt1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader {
    private final static Path DEFAULT_CLASS_DIR = Paths.get("/Users/xuxliu/Ifoods/Java/");


    // 默认的class 路径
    public MyClassLoader() {
        super();
        this.classDir = DEFAULT_CLASS_DIR;
    }

    private final Path classDir;

    // 指定父类加载器
    // 允许传入指定路径的 class 路径
    public MyClassLoader(String classDir, ClassLoader parent) {
        super(parent);
        this.classDir = Paths.get(classDir);
    }

    // 指定加载特定的目录
    public MyClassLoader(String classDir) {
        super();
        this.classDir = Paths.get(classDir);
    }

    // 重写父类的 findClass 这是非常重要的
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 读取二进制数据
        byte[] classBytes = new byte[0];
        try {
            classBytes = this.readClassBytes(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (classBytes == null || classBytes.length == 0) {
            throw new ClassNotFoundException("can not load the class");
        }

        // 调用 defineClass 方法定义class，读取class 文件
        return this.defineClass(name, classBytes, 0, classBytes.length);
    }

    // class 文件读入内存
    private byte[] readClassBytes(String name) throws ClassNotFoundException, IOException {
        // 包名分隔符专为文件路径
        String classPath = name.replace(".", "/");

        Path classFullPath = classDir.resolve(Paths.get(classPath) + ".class");

        if (!classFullPath.toFile().exists()){
            throw new ClassNotFoundException("the class " + name + " not found");
        }
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Files.copy(classFullPath, baos);
            return baos.toByteArray();
        }

    }

    @Override
    public String toString() {
        return "MyClassLoader";
    }


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        ClassLoader classLoader = MyClassLoader.class.getClassLoader();


//        MyClassLoader myClassLoader = new MyClassLoader();
//        MyClassLoader myClassLoader = new MyClassLoader("/Users/xuxliu/Ifoods/Java/", classLoader);
        MyClassLoader myClassLoader = new MyClassLoader("/Users/xuxliu/Ifoods/Java/", null);

        Class<?> aClass = myClassLoader.loadClass("thread.chapt1.HelloWorld");

        System.out.println(aClass.getClassLoader());

        Object helloWorld = aClass.newInstance();

        System.out.println(helloWorld);

        Method welcome = aClass.getMethod("welcome");
        String result = (String) welcome.invoke(helloWorld);

        System.out.println(result);
    }
}































