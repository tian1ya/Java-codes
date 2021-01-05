package designMode.ModesOf21kinds;

import java.util.ArrayList;
import java.util.regex.Pattern;

class AdvTemplate {
    private String advSubject = "xxx 银行国企信用卡抽奖活动";
    private String advContext = "国庆抽奖活动：只要刷卡就能送你1百万!....";

    public String getAdvSubject() {
        return advSubject;
    }

    public String getAdvContext() {
        return advContext;
    }
}

class Mail implements Cloneable{
    private String receiver;
    private String subject;
    private String application;
    private String context;
    private String tail;

    public Mail(AdvTemplate advTemplate) {
        this.context = advTemplate.getAdvContext();
        this.subject = advTemplate.getAdvContext();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }

    @Override
    protected Mail clone() {
        Mail clone = null;
        try {
            clone = (Mail) super.clone();
        } catch (CloneNotSupportedException e) {
        }
        return clone;
    }
}


public class YuanXinModel {

    public static void sendMail(Mail mail) {
        System.out.println("标题:" + mail.getSubject() + "\t收件人:" +
                mail.getReceiver() + "\t...发送成功");
    }

    public static String getRandString(int maxLength) {
        String source = "afdafafafafafgrefgrwfrw";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < maxLength; i++) {
            sb.append(source.charAt(2));
        }
        return sb.toString();
    }
    private static int MAX_COUNT = 6;

    public static void main(String[] args) {

//        int i =0;
//        Mail mail = new Mail(new AdvTemplate());
//        mail.setTail("xxx银行版权所有");
//
//        while (i < MAX_COUNT) {
//            Mail cloneMail = mail.clone();
//            mail.setApplication(getRandString(4) + " 先生(女士)");
//            mail.setReceiver(getRandString(5) + "@" + getRandString(3));
//
//            sendMail(mail);
//            i ++;
//        }

//        Thing thing = new Thing();
//        thing.setValue("ds");
//
//        Thing clone = thing.clone();
//        clone.setValue("dsddsd");
//
//        System.out.println(thing.getArrayList().toString());
//
//        System.out.println(clone.getArrayList().toString());

        String regex = "^[\\+\\-]?[\\d]*$";
        boolean b = Pattern.compile(regex).matcher("ddds").find();
        System.out.println("结果：" + b);
    }

}

class Thing implements Cloneable {
    private ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected Thing clone() {
        Thing thing = null;

        try {
            thing = (Thing) super.clone();
            thing.arrayList = (ArrayList<String>) this.arrayList.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return thing;
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public void setValue(String value) {
        this.arrayList.add(value);
    }
}

