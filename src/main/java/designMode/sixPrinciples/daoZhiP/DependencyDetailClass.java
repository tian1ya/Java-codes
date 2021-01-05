package designMode.sixPrinciples.daoZhiP;


interface ICar {
    public void run();
}

interface IDriver {
    public void drive(ICar car);
}

class Driver implements IDriver{
    @Override
    public void drive(ICar car) {
        car.run();
    }
}

class Benz implements ICar{

    public void run() {
        System.out.println("奔驰汽车开始运行...........");
    }
}

class Bmw implements ICar{

    public void run() {
        System.out.println("宝马汽车开始运行...........");
    }
}


public class DependencyDetailClass {

    public static void main(String[] args) {
        Driver driver = new Driver();
        Benz benz = new Benz();
        Bmw bmw = new Bmw();

        driver.drive(benz);
        driver.drive(bmw);
    }
}
