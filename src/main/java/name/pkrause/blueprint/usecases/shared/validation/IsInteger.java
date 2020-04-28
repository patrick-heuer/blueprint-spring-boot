package name.pkrause.blueprint.usecases.shared.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IsIntegerValidator.class})
public @interface IsInteger {
    String message() default "Value is not an Integer";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
