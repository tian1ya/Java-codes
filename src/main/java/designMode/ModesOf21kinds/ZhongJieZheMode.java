package designMode.ModesOf21kinds;

import java.util.Random;


abstract class AbstractMediator {
    // 中介中需要添加所有的环节部门
    protected  Purchase purchase;
    protected Sale sale;
    protected  Stock stock;

    public AbstractMediator() {
        this.purchase = new Purchase(this);
        this.sale = new Sale(this);
        this.stock = new Stock(this);
    }

    // 最终要的方法，处理多个对象之间的关系，统一的接口
    public abstract void execute(String str, Object ... objects);
}

class Mediator extends AbstractMediator {

    // 定义中介的行为，
    @Override
    public void execute(String str, Object... objects) {
        if (str.equalsIgnoreCase("purcase.buy")){
            this.buyComputer((Integer)objects[0]);
        }else if (str.equalsIgnoreCase("sale.sell")){
            this.sellComputer((Integer)objects[0]);
        }else if (str.equalsIgnoreCase("sale.offsell")){
            this.offSell();
        }else if (str.equalsIgnoreCase("stock.clear")) {
            this.clearStock();
        }
    }

    public void buyComputer(int number) {

        int saleStatus =super.sale.getSaleStatus();

        if (saleStatus > 80) {
            System.out.println("采购IBM电脑：" + number);
            super.stock.increase(number);
        }else {
            int buyNumber = number / 2;
            System.out.println("采购IBM电脑：" + buyNumber + " 台");
            super.stock.increase(buyNumber);
        }
    }

    public void sellComputer(int number) {

        if (super.stock.getStockNumber() < number) {
            super.purchase.buyIBMcomputer(number);
        }
    }

    public void offSell() {
        System.out.println("折价销售IBM电脑：" + super.stock.getStockNumber() + " 台");
    }

    public void clearStock() {

        super.sale.offSalse();
        super.purchase.refuseBuyIBM();
    }
}

abstract class AbstractColleague {
    // 它的出现是因为，每一个同事类中都需要有Meditator，所以提取成公共的父类
    protected AbstractMediator mediator;

    public AbstractColleague(AbstractMediator mediator) {
        this.mediator = mediator;
    }
}

class Purchase extends AbstractColleague {

    // 这是最顶级的接口，即包含了中介类，又会是具体部门类的父类

    public Purchase(AbstractMediator mediator) {
        super(mediator);
    }

    public void buyIBMcomputer(int number) {
        //需要依赖 purcase 同事的动作，所有抽到 meditator
        super.mediator.execute("purcase.buy", number);
    }

    public void refuseBuyIBM() {
        //自己的行为
        System.out.println("不在采购IBM电脑");
    }
}

class Stock extends  AbstractColleague {

    public Stock(AbstractMediator mediator) {
        super(mediator);
    }

    private static int  COMPUTER_NUMBER = 100;

    public int getStockNumber() {
        //自己的行为
        return COMPUTER_NUMBER;
    }

    public void increase(int number) {
        //自己的行为
        COMPUTER_NUMBER += number;
        System.out.println("库存数量为：" + COMPUTER_NUMBER);
    }

    public void decrease(int number) {
        //自己的行为
        COMPUTER_NUMBER -= number;
        System.out.println("库存数量为：" + COMPUTER_NUMBER);

    }
    public void clearStock() {
        //需要依赖 stock 同事的动作，所有抽到 meditator
        System.out.println("清理存货数量为：" + COMPUTER_NUMBER);
        super.mediator.execute("stock.clear");
    }
}

class Sale extends AbstractColleague {

    public Sale(AbstractMediator mediator) {
        super(mediator);
    }

    public void sellIBMComputer(int number) {
        //需要依赖 sale 同事的动作，所有抽到 meditator
        super.mediator.execute("sale.sell", number);
        System.out.println("销售IBM电脑：" + number + " 台");
    }

    public void offSalse() {
        //需要依赖 sale 同事的动作，所有抽到 meditator
        super.mediator.execute("sale.offsell");
    }

    public int getSaleStatus() {
        // 自己的行为
        Random random = new Random(System.currentTimeMillis());
        int saleStatus = random.nextInt(100);
        System.out.println("IBM 电脑的销售情况为：" + saleStatus);
        return saleStatus;
    }
}


public class ZhongJieZheMode {

    public static void main(String[] args) {
        Mediator mediator = new Mediator();
        Purchase purchase = new Purchase(mediator);
        purchase.buyIBMcomputer(100);

        Sale sale = new Sale(mediator);
        sale.sellIBMComputer(1);

        Stock stock = new Stock(mediator);
        stock.clearStock();
    }
}
