package designMode.ModesOf21kinds;

abstract class HummerModel {
    abstract void start();

    abstract void stop();

    abstract void alarm();

    abstract void engineBoom();

     final void run() {
        this.start();
        this.engineBoom();
        if (this.isAlarm()) {
            this.alarm();
        }
        this.stop();
    }

    protected  boolean isAlarm() {
         return true;
    }
}

class HummerH1Model extends HummerModel {

    private boolean alarmFlag = true;

    public void setAlarmFlag(boolean alarmFlag) {
        this.alarmFlag = alarmFlag;
    }

    @Override
    void start() {
        System.out.println("悍马H1 启动");
    }

    @Override
    void stop() {
        System.out.println("悍马H1 停止");
    }

    @Override
    void alarm() {
        System.out.println("悍马H1 鸣笛");
    }

    @Override
    void engineBoom() {
        System.out.println("悍马H1 引擎声音");
    }
}

class HummerH2Model extends HummerModel {

    private boolean alarmFlag = true;

    public void setAlarmFlag(boolean alarmFlag) {
        this.alarmFlag = alarmFlag;
    }

    @Override
    void start() {
        System.out.println("悍马H2 启动");
    }

    @Override
    void stop() {
        System.out.println("悍马H2 停止");
    }

    @Override
    void alarm() {
        System.out.println("悍马H2 鸣笛");
    }

    @Override
    void engineBoom() {
        System.out.println("悍马H2 引擎声音");
    }

    protected  boolean isAlarm() {
        return false;
    }
}

public class TemplateMode {

    public static void main(String[] args) {
        HummerH1Model hummerH1Model = new HummerH1Model();

//        hummerH1Model.setAlarmFlag(false);
        hummerH1Model.run();

        HummerH2Model hummerH1Mode2 = new HummerH2Model();
        hummerH1Mode2.run();
    }

}
