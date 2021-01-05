package designMode.ModesOf21kinds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


interface IWomen {
    // 个人情况
    int getType();
    //请求
    String getRequest();
}

abstract class Handler {
    public static final int FATHER_LEVEL_REQUEST = 1;
    public static final int HUABAND_LEVEL_REQUEST = 2;
    public static final int SON_LEVEL_REQUEST = 3;

    // deal level，决定处理等级
    private int level = 0;

    private Handler nextHandler;

    public Handler(int level) {
        this.level = level;
    }

    public void setNext(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected abstract void response(IWomen women);

    public final void handleMessage(IWomen women) {
        // 判断是否是自己的处理级别
        if (women.getType() == this.level){
            this.response(women);
        }else if (this.nextHandler != null) {
            // 不是则由子去处理
            this.nextHandler.response(women);
        }else {
            System.out.println("no place to deal with, decided by women self");
        }
    }
}

class Father extends Handler {

    public Father() {
        super(FATHER_LEVEL_REQUEST);
    }

    @Override
    protected void response(IWomen women) {
        System.out.println("------- father make decision: request from daughter");
        System.out.println(women.getRequest());
        System.out.println("father is agree\n");
    }
}

class Husband extends Handler {

    public Husband() {
        super(HUABAND_LEVEL_REQUEST);
    }

    @Override
    protected void response(IWomen women) {
        System.out.println("------- Husband make decision: request from wife");
        System.out.println(women.getRequest());
        System.out.println("Husband is agree\n");
    }
}

class Son extends Handler {

    public Son() {
        super(SON_LEVEL_REQUEST);
    }

    @Override
    protected void response(IWomen women) {
        System.out.println("------- Son make decision: request from wife");
        System.out.println(women.getRequest());
        System.out.println("Son is agree\n");
    }
}

class Women implements IWomen {

    public Women(int type, String request) {
        this.type = type;
        switch (this.type){
            case 1:
                this.request = "daughter's request is " + request;
                break;
            case 2:
                this.request = "wife's request is " + request;
                break;
            case 3:
                this.request = "mother's request is " + request;
                break;
            default:
                this.request = request;
        }
    }

    private int type = 0;
    private String request = "";
    @Override
    public int getType() {
        return type;
    }

    @Override
    public String getRequest() {
        return request;
    }
}


public class ZerenLianMode {
    public static void main(String[] args) {

        // 在实际中，一般会有一个封装对责任链模式进行封装，也就是替代Client类。
        // 直到返回链中的第一个处理者，具体链的设置不需要高层次的模块连接
        // 这样更加的简化高层次模块的调用，减少模块直接的耦合
        // 提供系统的灵活性

        Random rand = new Random();
        List<IWomen> randList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            randList.add(new Women(rand.nextInt(3),"I want to go shooping"));
        }

        Father father = new Father();
        Husband husBand = new Husband();
        Son son = new Son();

        father.setNext(husBand);
        husBand.setNext(son);
        for (IWomen women: randList) {

            // 客户端类就不需要判断是谁去处理了，只需要请示父亲处理，不该父亲处理，那就是继续将处理
            // 任务往下传。
            father.handleMessage(women);
        }
    }
}
