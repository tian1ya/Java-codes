package designMode;

import org.omg.PortableServer.POA;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

class SignInfo {
    private String id;
    private String location;
    private String subject;
    private String postAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }
}

class SignInfoPool extends SignInfo {
    private String key;

    public SignInfoPool(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

class SignInfoFactory {

    private final static Map<String, SignInfo> pool = new HashMap<String, SignInfo>();

    public static SignInfo getSignInfo() {
        return new SignInfo();
    }

    public static SignInfo getSignInfo(String key) {
        SignInfo result = null;

        if (!pool.containsKey(key)) {
            System.out.println(key + "--- 建立对象，并放到池子中");
            result = new SignInfoPool(key);
            pool.put(key, result);
        }else {
            result = pool.get(key);
        }
        return result;
    }
}


public class XiaoYuanMode {
    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            String subject = "subject " + i;
            for (int j = 0; j < 3; j++) {
                String key = subject + " test location " + j;
                SignInfoFactory.getSignInfo(key);
            }
        }

        SignInfo signInfo = SignInfoFactory.getSignInfo("subject 1 test location 1");
        System.out.println(signInfo.hashCode());

        SignInfo signInfo2 = SignInfoFactory.getSignInfo("subject 1 test location 1");
        System.out.println(signInfo2.hashCode());


    }
}
