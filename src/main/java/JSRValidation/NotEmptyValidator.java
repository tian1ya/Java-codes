package JSRValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyValidator implements ConstraintValidator<NotEmpty,NotEmpty.Name > {

    @Override
    public void initialize(NotEmpty constraintAnnotation) {
    }

    @Override
    public boolean isValid(NotEmpty.Name value, ConstraintValidatorContext constraintValidatorContext) {
        if (value.getCompany() == null)
            return false;
        else if(value.getCompany().length()<1)
            return false;
        else
            return true;
    }
}
