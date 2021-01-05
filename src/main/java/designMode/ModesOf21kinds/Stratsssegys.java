package designMode.ModesOf21kinds;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.naming.spi.DirStateFactory;

public enum  Stratsssegys {

    BackDoor() {

        @Override
        public void operatess() {
            System.out.println("BackDoor....");
        }
    },

    GivenGreenLight() {

        @Override
        public void operatess() {
            System.out.println("GivenGreenLight....");
        }
    },

    BlockEnemy() {

        @Override
        public void operatess() {
            System.out.println("BlockEnemy....");
        }
    };

    Stratsssegys() {
    }
    public abstract void operatess();
}
