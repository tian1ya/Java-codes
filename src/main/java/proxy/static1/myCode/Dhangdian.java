package proxy.static1.myCode;

public class Dhangdian implements Store{

    private Factory factory;

    public Dhangdian(Factory factory) {
        this.factory = factory;
    }

    @Override
    public void sell() {
        reinforcementMethod();


        factory.sell();

        reinforcementMethodAfter();
    }


    private void reinforcementMethod() {
        System.out.println("reinforcementMethod... before");
    }

    private void reinforcementMethodAfter() {
        System.out.println("reinforcementMethod... before");
    }
}
