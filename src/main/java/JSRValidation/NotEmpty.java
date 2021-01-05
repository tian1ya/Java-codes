package JSRValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = NotEmptyValidator.class)
public @interface NotEmpty {

    String message() default "this string may be empty";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};


    interface Name {
        String getCompany();
    }
}
