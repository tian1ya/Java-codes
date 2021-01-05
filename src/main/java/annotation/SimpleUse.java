package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface Jiecha{}

class NoBug{

    public NoBug() {
    }

    @Jiecha
    public void suanShu(){
        System.out.println("suanShu");
    }

    @Jiecha
    public void suanShu1(){
        System.out.println("suanShu1");
    }

    @Jiecha
    public void suanShu2(){
        System.out.println("suanShu2");
    }

    @Jiecha
    public void suanShu3(){
        System.out.println("suanShu3");
    }

    @Jiecha
    public void suanShu4(){
        System.out.println("suanShu4");
    }
}



public class SimpleUse {

    public static void main(String[] args) {
        NoBug noBug = new NoBug();

        Class<? extends NoBug> clazz = noBug.getClass();

        Method[] declaredMethods = clazz.getDeclaredMethods();

        for (Method m : declaredMethods) {
            if (m.isAnnotationPresent(Jiecha.class)) {
                try {
                    m.invoke(noBug, null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
