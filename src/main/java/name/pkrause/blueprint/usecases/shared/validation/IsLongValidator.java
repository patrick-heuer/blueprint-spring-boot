package name.pkrause.blueprint.usecases.shared.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsLongValidator implements ConstraintValidator<IsLong, Long> {
    private static final Logger LOGGER = LoggerFactory.getLogger(IsLongValidator.class);

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value instanceof Long;
    }
}