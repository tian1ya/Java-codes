package designMode.sixPrinciples.LiShiP;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

abstract class AbstractGun {
    public abstract void shoot();
}

abstract class AbstractToy extends AbstractGun {
    public abstract void shoot();
}

class ToyGun extends AbstractToy {

    @Override
    public void shoot() {
        System.out.println("biubiu...");
    }
}

class HandGun extends AbstractGun {

    @Override
    public void shoot() {
        System.out.println("手枪射击..");
    }
}

class Rifle extends AbstractGun {

    @Override
    public void shoot() {
        System.out.println("步枪射击...");
    }
}

class MachineGun extends AbstractGun {

    @Override
    public void shoot() {
        System.out.println("机枪扫射..");
    }
}



class Soldier {
    private AbstractGun gun;

    public void setGun(AbstractGun gun) {
        this.gun = gun;
    }

    public void killEnemy() {
        System.out.println("士兵开始杀敌人了");
        gun.shoot();
    }
}


class Father {
    public Collection doSomething(Map map) {
        System.out.println("father");

        return map.values();
    }
}

class Son extends Father {

    public Collection doSomething(HashMap map) {
        System.out.println("son");
        return map.values();
    }
}
public class GunShoot {
    public static void main(String[] args) {
//        Soldier soldier = new Soldier();
//        soldier.setGun(new ToyGun());
//        soldier.setGun(new HandGun());
//        soldier.setGun(new MachineGun());

//        soldier.killEnemy();

        Father father = new Father();
        father.doSomething(new HashMap());

        Son son = new Son();
        son.doSomething(new HashMap());
    }
}

