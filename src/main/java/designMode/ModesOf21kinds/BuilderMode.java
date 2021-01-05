package designMode.ModesOf21kinds;

import java.util.ArrayList;
import java.util.List;

abstract class CarModel {

    // 存放执行顺序
    private List<String> sequence = new ArrayList<String>();
    public void setSequence(List<String> sequence) {
        this.sequence = sequence;
    }

    abstract void start();
    abstract void stop();
    abstract void alarm();
    abstract void engineBoom();

    final void run() {
        for (int i = 0; i < sequence.size(); i++) {
                String action = sequence.get(i);
                if ("start".equals(action)){
                    this.start();
                }else if ("stop".equals(action)){
                    this.stop();
                }else if ("alarm".equals(action)){
                    this.alarm();
                }else if ("engineBoom".equals(action)){
                    this.engineBoom();
            }
        }
    }
}

class BenzModel extends CarModel {
    @Override
    void start() {
        System.out.println("奔驰 启动");
    }

    @Override
    void stop() {
        System.out.println("奔驰 停止");
    }

    @Override
    void alarm() {
        System.out.println("奔驰 鸣笛");
    }

    @Override
    void engineBoom() {
        System.out.println("奔驰 引擎声音");
    }
}

class BMWModel extends CarModel {
    @Override
    void start() {
        System.out.println("宝马 启动");
    }

    @Override
    void stop() {
        System.out.println("宝马 停止");
    }

    @Override
    void alarm() {
        System.out.println("宝马 鸣笛");
    }

    @Override
    void engineBoom() {
        System.out.println("宝马 引擎声音");
    }
}


public class BuilderMode {
    public static void main(String[] args) {

//        BenzModel benzModel = new BenzModel();

        List<String> sequence = new ArrayList<>();
        sequence.add("alarm");
        sequence.add("start");
        sequence.add("stop");

//        benzModel.setSequence(sequence);
//
//        benzModel.run();

        BenzBuilder benzBuilder = new BenzBuilder();
        benzBuilder.setSequence(sequence);

        CarModel carModel = benzBuilder.getCarModel();
        carModel.run();
    }
}

abstract class CarBuilder {
    abstract void setSequence(List<String> sequence);
    abstract CarModel getCarModel();
}

class BenzBuilder extends CarBuilder {
    private BenzModel benzModel = new BenzModel();

    @Override
    void setSequence(List<String> sequence) {
        this.benzModel.setSequence(sequence);
    }

    @Override
    CarModel getCarModel() {
        return this.benzModel;
    }
}

class BMWuilder extends CarBuilder {
    private BenzModel bmwModel = new BenzModel();

    @Override
    void setSequence(List<String> sequence) {
        this.bmwModel.setSequence(sequence);
    }

    @Override
    CarModel getCarModel() {
        return this.bmwModel;
    }
}
