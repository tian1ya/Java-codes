package designMode.kindsOfSinglonMode;

import java.util.concurrent.ConcurrentHashMap;

/*
    用于管理
    但是是非现场安全的，上了所
 */
public class ContainerMode {
    private ContainerMode() {
    }

    private static final ConcurrentHashMap<String, Object> ioc = new ConcurrentHashMap<String, Object>();

    public Object getBean(String className) {
        synchronized (ioc) {
            if (!ioc.contains(className)) {
                Object obj = null;
                try {
                    obj = Class.forName(className).newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return obj;
            } else {
                return ioc.get(className);
            }
        }
    }
}

