package designMode.ModesOf21kinds;

import java.util.ArrayList;
import java.util.List;

// 模板方法和基本方法
class Product2 {
    void doSomething(){ }
}

// 抽象建造者，
abstract class Builder {
    public abstract void setPart();
    public abstract Product2 buildPart();
}

// 具体的建造者
class ConcreteProduct extends Builder {

    private Product2 product2 = new Product2();

    @Override
    public void setPart() { }

    @Override
    public Product2 buildPart() {
        return product2;
    }
}


// 导演类，复杂安排已有模块的顺序，然后告诉建造者开始构建
// 起到封装的作用，避免高层模块深入到建造者内部的实现类。
class Director {
    private Builder builder = new ConcreteProduct();

    public Product2 getAProduct() {
        return builder.buildPart();
    }
}

public class BuilderMode2 {
}























