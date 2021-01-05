package designMode.ModesOf21kinds;

class ProductV2 {
    /*
        产品零件
     */
    private String part1;
    private String part2;

    public String getPart1() {
        return part1;
    }

    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public String getPart2() {
        return part2;
    }

    public void setPart2(String part2) {
        this.part2 = part2;
    }
}

interface BuilderV2 {
    void buildPart1();
    void buildPart2();

    ProductV2 retrieveResult();
}

class ConcreteBuilder implements BuilderV2 {
    private ProductV2 product = new ProductV2();

    // 建造零件
    @Override
    public void buildPart1() {
        product.setPart1("编号：9999");
    }

    // 建造零件
    @Override
    public void buildPart2() {
        product.setPart2("名称：建造攻城狮");
    }
    /**
     * 返回建造后成功的产品
     */
    @Override
    public ProductV2 retrieveResult() {
        return product;
    }
}

class DirectorV2 {

    // 构造着对象
    private BuilderV2 builder;

    //构造函数，给定建造者对象
    public DirectorV2(BuilderV2 builder) {
        this.builder = builder;
    }

    public void construct(){
        builder.buildPart1();
        builder.buildPart2();
    }
}
public class BuilderExample {
    public static void main(String[] args) {
        //创建具体建造者对象
        BuilderV2 builder = new ConcreteBuilder();
        //创造导演者角色，给定建造者对象
        DirectorV2 director = new DirectorV2(builder);
        //调用导演者角色，创建产品零件
        director.construct();
        //接收建造者角色产品建造结果
        ProductV2 product = builder.retrieveResult();
    }
}
