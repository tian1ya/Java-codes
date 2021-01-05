package designMode.sixPrinciples.DiMiJiaP;

import java.util.Random;

class Wizard {
    private Random rand = new Random(System.currentTimeMillis());

    private int first() {
        System.out.println("执行第一个方法");
        return rand.nextInt(100);
    }

    private int second() {
        System.out.println("执行第二个方法");
        return rand.nextInt(100);
    }

    private int third() {
        System.out.println("执行第三个方法");
        return rand.nextInt(100);
    }

    public void installWizard() {
        int first = first();

        if (first > 50) {
            int second = second();
            if (second > 50) {
                int third = third();
                if (third > 50) {
                    System.out.println("安装完成");
                }
            }
        }
    }
}

class InstallSoftware {
    public void installWizard(Wizard wizard) {
        wizard.installWizard();
    }
}

public class ClassDistance {
    public static void main(String[] args) {
        InstallSoftware installSoftware = new InstallSoftware();
        installSoftware.installWizard(new Wizard());
    }
}
