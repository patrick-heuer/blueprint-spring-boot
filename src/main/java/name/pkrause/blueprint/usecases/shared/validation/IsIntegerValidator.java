package name.pkrause.blueprint.usecases.shared.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsIntegerValidator implements ConstraintValidator<IsInteger, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return value instanceof Integer;
    }
}
