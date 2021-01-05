package JSRValidation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

// https://www.ibm.com/developerworks/cn/java/j-lo-beanvalid/index.html

@NotEmpty
public class EmployeeFieldValidation implements NotEmpty.Name{

    // 当 注解校验失败的时候 message 会作为错误消息展示
    /*
        以上的含义是： 对于字段id，在Java Bean 的实例中值不能为空
        对于每一个约束注解，对于每一个约束注解，在实际使用前都必须有相关定义

        约束注解和普通注解一样，一个典型的注解定义至少要包含以下的内容

        @Target({ })                           // 约束注解应用的目标元素类型
        @Retention()                           // 约束注解应用的时机
        @Constraint(validatedBy ={})           // 与约束注解关联的验证器
        public @interface ConstraintName{
            String message() default " ";     // 约束注解验证时的输出消息
            Class<?>[] groups() default { };  // 约束注解在验证时所属的组别
            Class<? extends Payload>[] payload() default { };
            // 约束注解的有效负载，有效负载通常用来将一些元数据信息与该约束注解相关联，常用的一种情况是用负载表示验证结果的严重程度。
        }


        约束注解定义完成后，需要同时实现与该约束注解关联的验证器。约束验证器的实现需要扩展
        JSR303 规范提供的接口 javax.validation.ConstraintValidator

        public interface ConstraintValidator<A extends Annotation, T> {
            void initialize(A constraintAnnotation);
            boolean isValid(T value, ConstraintValidatorContext context);
        }

        方法 initialize 对验证器进行实例化,它必须在验证器的实例在使用之前被调用，并保证正确初始化验证器，它的参数是约束注解

        方法 isValid 是进行约束验证的主体方法，其中 value 参数代表需要验证的实例，context 参数代表约束执行的上下文环境。

     */
    @NotNull(message = "id must not be null")
    private Integer id;

    @NotNull(message = "id must not be null")
    @Size(min = 1, max = 10, message = "name must be 1 < abd <= 10")
    private String name;

    private String company;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @PatternOfString.List({
            @PatternOfString(mustContainLetter = "CH",
                    message = "It does not belong to China"),
            @PatternOfString(mustContainLetter="MainLand",
                    message="It does not belong to MainLand")})
    private String place;

    @Override
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static void main(String[] args) {
        EmployeeFieldValidation validation = new EmployeeFieldValidation();

        validation.setId(1);
        validation.setName("ddddddddddddddddddddddddddddddddddddddddds");
        validation.setCompany("");

        validation.setPlace("dd");

        /*
            To validate a bean, we must first have a Validator object,
            which is constructed using a ValidatorFactory.
         */
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();

        /*
            validate beans
         */
        Set<ConstraintViolation<EmployeeFieldValidation>> validateRes = validator.validate(validation);

        validateRes.forEach(v -> System.out.println(v.getMessage()));

    }
}
