package designMode.ModesOf21kinds;

interface HumanV2 {
    void getColor();
    void talk();
    void getSex();
}

abstract class AbstractWhiteHuman implements HumanV2 {
    @Override
    public void getColor() {
        System.out.println("白色皮肤");
    }

    @Override
    public void talk() {
        System.out.println("发音都是单字节的");
    }
}

abstract class AbstractBlackHuman implements HumanV2 {
    @Override
    public void getColor() {
        System.out.println("黑色皮肤");
    }

    @Override
    public void talk() {
        System.out.println("发音都是单字节的");
    }
}

abstract class AbstractYellowHuman implements HumanV2 {
    @Override
    public void getColor() {
        System.out.println("黄色皮肤");
    }

    @Override
    public void talk() {
        System.out.println("发音都是多字节的");
    }
}

class FemaleYellowHuman extends AbstractYellowHuman {

    @Override
    public void getSex() {
        System.out.println("黄色女性人种");
    }
}

class MaleYellowHuman extends AbstractYellowHuman {

    @Override
    public void getSex() {
        System.out.println("黄色男性人种");
    }
}


class MaleWhiteHuman extends AbstractWhiteHuman {

    @Override
    public void getSex() {
        System.out.println("白色男性人种");
    }
}

class FemaleWhiteHuman extends AbstractWhiteHuman {

    @Override
    public void getSex() {
        System.out.println("黄白女性人种");
    }
}

class MaleBlackHuman extends AbstractBlackHuman {

    @Override
    public void getSex() {
        System.out.println("黑色男性人种");
    }
}

class FemaleBlackHuman extends AbstractBlackHuman {

    @Override
    public void getSex() {
        System.out.println("黑白女性人种");
    }
}

interface HumanFactory {
    HumanV2 createYellowHuamn();
    HumanV2 createBlackHuamn();
    HumanV2 createWhiteHuamn();
}

class FemaleFactory implements HumanFactory {

    @Override
    public HumanV2 createYellowHuamn() {
        return new FemaleYellowHuman();
    }

    @Override
    public HumanV2 createBlackHuamn() {
        return new FemaleBlackHuman();
    }

    @Override
    public HumanV2 createWhiteHuamn() {
        return new FemaleBlackHuman();
    }
}

class MaleFactory implements HumanFactory {

    @Override
    public HumanV2 createYellowHuamn() {
        return new MaleYellowHuman();
    }

    @Override
    public HumanV2 createBlackHuamn() {
        return new MaleBlackHuman();
    }

    @Override
    public HumanV2 createWhiteHuamn() {
        return new MaleBlackHuman();
    }
}

public class InterfaceFactoryMode {
}
